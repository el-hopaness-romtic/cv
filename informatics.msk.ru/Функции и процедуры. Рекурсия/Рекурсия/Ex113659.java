import java.util.*;

public class Ex113659 {
    private static class Node {
        final List<Node> children = new LinkedList<>();
        final int id;

        Node(int id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        Map<Integer, Node> nodes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int k = scanner.nextInt(), v = scanner.nextInt();
            if (!nodes.containsKey(k))
                nodes.put(k, new Node(k));
            if (v != 0) {
                if (!nodes.containsKey(v))
                    nodes.put(v, new Node(v));

                nodes.get(v).children.add(nodes.get(k));
            }
        }
        int k = scanner.nextInt();

        System.out.println(countSubtreeNodes(nodes.get(k)));
    }


    static int countSubtreeNodes(Node n) {
        if (n.children.isEmpty()) return 1;
        return 1 + n.children.stream().mapToInt(Ex113659::countSubtreeNodes).sum();
    }
}
