import java.util.*;

import static java.lang.Math.min;
import static java.util.Arrays.asList;

public class Ex3082 {
    private enum Action {
        FillA(">A"), FillB(">B"), PourA("A>"), PourB("B>"), aToB("A>B"), bToA("B>A");
        private final String s;

        Action(String s) {
            this.s = s;
        }

        @Override
        public String toString() {
            return s;
        }
    }

    private static class State {
        public static short A, B;

        State(Action action) {
            switch (action) {
                case FillA:
                    this.a = A;
                    this.b = 0;
                    break;
                case FillB:
                    this.a = 0;
                    this.b = B;
                    break;
                default:
                    throw new IllegalArgumentException();
            }

            this.action = action;
            this.prev = null;
            this.length = 1;
        }

        State(Action action, State prev) {
            int diff;
            switch (action) {
                case FillA:
                    a = A;
                    b = prev.b;
                    break;
                case FillB:
                    a = prev.a;
                    b = B;
                    break;
                case PourA:
                    a = 0;
                    b = prev.b;
                    break;
                case PourB:
                    a = prev.a;
                    b = 0;
                    break;
                case aToB:
                    diff = min(prev.a, B - prev.b);
                    a = (short) (prev.a - diff);
                    b = (short) (prev.b + diff);
                    break;
                case bToA:
                    diff = min(prev.b, A - prev.a);
                    a = (short) (prev.a + diff);
                    b = (short) (prev.b - diff);
                    break;
                default:
                    throw new IllegalArgumentException();
            }

            this.action = action;
            this.prev = prev;
            this.length = prev.length + 1;
        }

        final short a;
        final short b;
        final Action action;
        final State prev;
        final int length;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return a == state.a && b == state.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        short A = scanner.nextShort(), B = scanner.nextShort(), N = scanner.nextShort();
        State.A = A;
        State.B = B;

        Map<State, State> history = new HashMap<>();
        Queue<State> queue = new LinkedList<>(asList(
                new State(Action.FillA),
                new State(Action.FillB)
        )) {
            @Override
            public boolean add(State state) {
                State oldState = history.getOrDefault(state, null);

                if (oldState != null && oldState.length <= state.length) {
                    return false;
                }

                if (state.length > 100_000)
                    return false;
                history.put(state, state);
                return super.add(state);
            }
        };
        while (!queue.isEmpty()) {
            State s = queue.poll();
            if (s.a == N || s.b == N) {
                StringBuilder sb = new StringBuilder(s.action.toString());
                s = s.prev;
                while (s != null) {
                    sb.insert(0, s.action + System.lineSeparator());
                    s = s.prev;
                }
                System.out.println(sb);
                return;
            }

            switch (s.action) {
                case FillA:
                    if (s.b != B) {
                        queue.add(new State(Action.FillB, s));
                        queue.add(new State(Action.aToB, s));
                    }
                    if (s.b != 0)
                        queue.add(new State(Action.PourB, s));
                    break;
                case FillB:
                    if (s.a != A) {
                        queue.add(new State(Action.FillA, s));
                        queue.add(new State(Action.bToA, s));
                    }
                    if (s.a != 0)
                        queue.add(new State(Action.PourA, s));
                    break;
                case PourA:
                    if (s.b != B)
                        queue.add(new State(Action.FillB, s));
                    if (s.b != 0)
                        queue.add(new State(Action.bToA, s));
                case PourB:
                    if (s.a != A)
                        queue.add(new State(Action.FillA, s));
                    if (s.a != 0)
                        queue.add(new State(Action.aToB, s));
                    break;
                case aToB:
                    queue.add(new State(Action.FillA, s));
                    queue.add(new State(Action.PourB, s));
                    queue.add(new State(Action.bToA, s));
                    if (s.b != B)
                        queue.add(new State(Action.FillB, s));
                    if (s.a != 0)
                        queue.add(new State(Action.PourA, s));
                    break;
                case bToA:
                    queue.add(new State(Action.FillB, s));
                    queue.add(new State(Action.PourA, s));
                    queue.add(new State(Action.aToB, s));
                    if (s.a != A)
                        queue.add(new State(Action.FillA, s));
                    if (s.b != 0)
                        queue.add(new State(Action.PourB, s));
                    break;
            }
        }
        System.out.println("Impossible");
    }
}
