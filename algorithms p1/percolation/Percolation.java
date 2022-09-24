/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final byte CLOSED = 0b0000;
    private static final byte OPEN = 0b0010;
    private static final byte TOP = 0b0100;
    private static final byte BOTTOM = 0b1000;
    private static final byte BOTH = 0b1110;

    private final WeightedQuickUnionUF uf;
    private final int n;
    private final byte[] meta;
    private int nOpen = 0;
    private boolean percolation = false;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n should be greater than zero");

        uf = new WeightedQuickUnionUF(n * n);
        this.n = n;
        meta = new byte[n * n / 2 + (n * n) % 2];
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col))
            return;

        nOpen++;
        int pos = convert(row, col);

        boolean isTop = row == 1, isBottom = row == n;
        if (row + 1 <= n && isOpen(row + 1, col)) {
            isTop = isTop || isConnectedTo(row + 1, col, TOP);
            isBottom = isBottom || isConnectedTo(row + 1, col, BOTTOM);
            uf.union(convert(row + 1, col), pos);
        }
        if (row - 1 >= 1 && isOpen(row - 1, col)) {
            isTop = isTop || isConnectedTo(row - 1, col, TOP);
            isBottom = isBottom || isConnectedTo(row - 1, col, BOTTOM);
            uf.union(convert(row - 1, col), pos);
        }
        if (col + 1 <= n && isOpen(row, col + 1)) {
            isTop = isTop || isConnectedTo(row, col + 1, TOP);
            isBottom = isBottom || isConnectedTo(row, col + 1, BOTTOM);
            uf.union(convert(row, col + 1), pos);
        }
        if (col - 1 >= 1 && isOpen(row, col - 1)) {
            isTop = isTop || isConnectedTo(row, col - 1, TOP);
            isBottom = isBottom || isConnectedTo(row, col - 1, BOTTOM);
            uf.union(convert(row, col - 1), pos);
        }

        byte res = OPEN;
        if (isTop) res += TOP;
        if (isBottom) res += BOTTOM;

        percolation = percolation || (res == BOTH);
        setMeta(uf.find(pos), res);
        setMeta(pos, res);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return getMeta(convert(row, col)) != CLOSED;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return isConnectedTo(row, col, TOP);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return nOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return percolation;
    }

    private int convert(int row, int col) {
        if (1 > row || row > n)
            throw new IllegalArgumentException("row should be in [1, n], got: " + row);
        if (1 > col || col > n)
            throw new IllegalArgumentException("column should be in [1, n], got: " + col);

        return col + n * (row - 1) - 1;
    }

    private boolean isConnectedTo(int row, int col, byte value) {
        if (!isOpen(row, col))
            return false;
        if ((getMeta(convert(row, col)) & value) > 0)
            return true;

        byte parentMeta = getMeta(uf.find(convert(row, col)));
        if (parentMeta == CLOSED)
            return false;
        if ((parentMeta & value) > 0) {
            setMeta(convert(row, col), parentMeta);
            return true;
        }
        return false;
    }

    private byte getMeta(int pos) {
        if (pos % 2 == 0)
            return (byte) ((meta[pos / 2] & 0b11110000) >> 4);
        return (byte) (meta[pos / 2] & 0b1111);
    }

    private void setMeta(int pos, byte val) {
        if (pos % 2 == 0)
            meta[pos / 2] = (byte) ((meta[pos / 2] & 0b1111) + (val << 4));
        else
            meta[pos / 2] = (byte) ((meta[pos / 2] & 0b11110000) + val);
    }

    // test client (optional)
    // public static void main(String[] args){}
}
