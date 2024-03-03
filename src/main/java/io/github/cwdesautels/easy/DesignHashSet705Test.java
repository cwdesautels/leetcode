package io.github.cwdesautels.easy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Design a HashSet without using any built-in hash table libraries.
 * <p>
 * Implement MyHashSet class:
 * <p>
 * void add(key) Inserts the value key into the HashSet.
 * bool contains(key) Returns whether the value key exists in the HashSet or not.
 * void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * Output
 * [null, null, null, true, false, null, true, null, false]
 * <p>
 * Explanation
 * MyHashSet myHashSet = new MyHashSet();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // return True
 * myHashSet.contains(3); // return False, (not found)
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // return True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // return False, (already removed)
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= key <= 106
 * At most 104 calls will be made to add, remove, and contains.
 */
public class DesignHashSet705Test {
    private static class MyHashSet {

        private Node[] table;
        private int size;

        public MyHashSet() {
            this.table = new Node[16];
            this.size = 0;
        }

        public void add(int key) {
            if (size > this.table.length / 2) {
                expand();
            }

            final var index = hash(key);
            var head = this.table[index];

            for (Node i = head; i != null; i = i.next) {
                if (i.key == key) {
                    return;
                }
            }

            final var node = new Node(key);

            node.next = head;

            if (head != null) {
                head.prev = node;
            }

            this.table[index] = node;

            size++;
        }

        public void remove(int key) {
            final var index = hash(key);
            var head = this.table[index];

            for (Node i = head; i != null; i = i.next) {
                if (i.key == key) {
                    final var prev = i.prev;
                    final var next = i.next;

                    if (next == null && prev == null) {
                        table[index] = null;
                    }

                    if (next != null) {
                        next.prev = prev;
                    }

                    if (prev != null) {
                        prev.next = next;
                    } else {
                        table[index] = next;
                    }

                    size--;

                    return;

                }
            }
        }

        public boolean contains(int key) {
            var head = this.table[hash(key)];

            for (Node i = head; i != null; i = i.next) {
                if (i.key == key) {
                    return true;
                }
            }

            return false;
        }

        private int hash(int key) {
            return key % this.table.length;
        }

        private void expand() {
            final var expanded = new Node[table.length * 2];
            final var original = table;

            this.table = expanded;

            for (int i = original.length - 1; i >= 0; i--) {
                var node = original[i];

                while (node != null) {
                    add(node.key);
                    node = node.next;
                }
            }
        }

        private static class Node {
            private final int key;
            private Node next;
            private Node prev;

            private Node(int key) {
                this.key = key;
            }

            @Override
            public String toString() {
                final var buffer = new StringBuilder(128)
                        .append('[')
                        .append(key);
                var node = next;

                while (node != null) {
                    buffer.append(',').append(node.key);
                    node = node.next;
                }

                return buffer.append(']').toString();
            }
        }

        @Override
        public String toString() {
            return "MyHashSet{" + Arrays.toString(table) + '}';
        }
    }

    @Test
    void test1() {
        final var myHashSet = new MyHashSet();

        myHashSet.add(1);      // set = [1]
        myHashSet.add(2);      // set = [1, 2]
        assertThat(myHashSet.contains(1)).isTrue(); // return True
        assertThat(myHashSet.contains(3)).isFalse(); // return False, (not found)
        myHashSet.add(2);      // set = [1, 2]
        assertThat(myHashSet.contains(2)).isTrue(); // return True
        myHashSet.remove(2);   // set = [1]
        assertThat(myHashSet.contains(2)).isFalse(); // return False, (already removed
    }

    @Test
    void test2() {
        final var myHashSet = new MyHashSet();

        myHashSet.add(58);
        assertThat(myHashSet.contains(0)).isFalse();
        myHashSet.add(14);
        assertThat(myHashSet.contains(58)).isTrue();
        myHashSet.remove(91);
        myHashSet.add(6);
        assertThat(myHashSet.contains(58)).isTrue();
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        "[\"MyHashSet\",\"remove\",\"add\",\"remove\",\"add\",\"add\",\"remove\",\"add\",\"contains\",\"add\",\"contains\",\"add\",\"contains\",\"remove\",\"remove\",\"contains\",\"add\",\"add\",\"add\",\"add\",\"contains\",\"remove\",\"contains\",\"contains\",\"remove\",\"add\",\"contains\",\"add\",\"add\",\"contains\",\"add\",\"add\",\"add\",\"add\",\"add\",\"contains\",\"add\",\"add\",\"remove\",\"add\",\"contains\",\"add\",\"add\",\"add\",\"add\",\"remove\",\"add\",\"contains\",\"add\",\"add\",\"add\",\"add\",\"remove\",\"remove\",\"contains\",\"contains\",\"add\",\"contains\",\"contains\",\"add\",\"contains\",\"remove\",\"contains\",\"contains\",\"remove\",\"add\",\"add\",\"add\",\"contains\",\"add\",\"contains\",\"add\",\"add\",\"add\",\"add\",\"add\",\"contains\",\"contains\",\"remove\",\"contains\",\"add\",\"remove\",\"add\",\"add\",\"add\",\"contains\",\"add\",\"add\",\"add\",\"add\",\"add\",\"add\",\"remove\",\"add\",\"contains\",\"add\",\"add\",\"add\",\"contains\",\"add\",\"add\"]",
                        "[[],[1],[9],[24],[53],[84],[90],[34],[9],[39],[84],[18],[9],[2],[34],[18],[68],[93],[62],[24],[76],[15],[88],[46],[32],[56],[62],[68],[99],[9],[69],[74],[55],[91],[21],[83],[43],[47],[91],[17],[16],[3],[2],[29],[15],[59],[54],[2],[86],[18],[30],[69],[66],[15],[76],[55],[1],[87],[52],[61],[84],[23],[61],[19],[99],[33],[63],[72],[45],[92],[72],[7],[29],[74],[0],[97],[63],[92],[85],[0],[83],[17],[32],[35],[91],[80],[95],[75],[18],[18],[38],[43],[14],[40],[46],[25],[42],[99],[47],[57],[25]]",
                        "[null,null,null,null,null,null,null,null,true,null,true,null,true,null,null,true,null,null,null,null,false,null,false,false,null,null,true,null,null,true,null,null,null,null,null,false,null,null,null,null,false,null,null,null,null,null,null,true,null,null,null,null,null,null,false,true,null,false,false,null,true,null,true,false,null,null,null,null,false,null,true,null,null,null,null,null,true,true,null,true,null,null,null,null,null,false,null,null,null,null,null,null,null,null,false,null,null,null,true,null,null]"
                ),
                Arguments.of(
                        "[\"MyHashSet\",\"add\",\"contains\",\"add\",\"contains\",\"remove\",\"add\",\"contains\",\"add\",\"add\",\"add\",\"add\",\"add\",\"add\",\"contains\",\"add\",\"add\",\"add\",\"contains\",\"remove\",\"contains\",\"contains\",\"add\",\"remove\",\"add\",\"remove\",\"add\",\"remove\",\"add\",\"contains\",\"add\",\"add\",\"contains\",\"add\",\"add\",\"add\",\"add\",\"remove\",\"contains\",\"add\",\"contains\",\"add\",\"add\",\"add\",\"remove\",\"remove\",\"add\",\"contains\",\"add\",\"add\",\"contains\",\"remove\",\"add\",\"contains\",\"add\",\"remove\",\"remove\",\"add\",\"contains\",\"add\",\"contains\",\"contains\",\"add\",\"add\",\"remove\",\"remove\",\"add\",\"remove\",\"add\",\"add\",\"add\",\"add\",\"add\",\"add\",\"remove\",\"remove\",\"add\",\"remove\",\"add\",\"add\",\"add\",\"add\",\"contains\",\"add\",\"remove\",\"remove\",\"remove\",\"remove\",\"add\",\"add\",\"add\",\"add\",\"contains\",\"add\",\"add\",\"add\",\"add\",\"add\",\"add\",\"add\",\"add\"]",
                        "[[],[58],[0],[14],[58],[91],[6],[58],[66],[51],[16],[40],[52],[48],[40],[42],[85],[36],[16],[0],[43],[6],[3],[25],[99],[66],[60],[58],[97],[3],[35],[65],[40],[41],[10],[37],[65],[37],[40],[28],[60],[30],[63],[76],[90],[3],[43],[81],[61],[39],[75],[10],[55],[92],[71],[2],[20],[7],[55],[88],[39],[97],[44],[1],[51],[89],[37],[19],[3],[13],[11],[68],[18],[17],[41],[87],[48],[43],[68],[80],[35],[2],[17],[71],[90],[83],[42],[88],[16],[37],[33],[66],[59],[6],[79],[77],[14],[69],[36],[21],[40]]",
                        "[null,null,false,null,true,null,null,true,null,null,null,null,null,null,true,null,null,null,true,null,false,true,null,null,null,null,null,null,null,true,null,null,true,null,null,null,null,null,true,null,true,null,null,null,null,null,null,false,null,null,false,null,null,false,null,null,null,null,true,null,true,true,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,true,null,null,null,null,null,null,null,null,null,false,null,null,null,null,null,null,null,null]"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(String rawCommands, String rawParameters, String rawExpected) {
        final var commands = Arrays.stream(rawCommands.split("[\\[\\]\",]"))
                .filter(not(String::isEmpty))
                .skip(1)
                .toList();
        final var parameters = Arrays.stream(rawParameters.split("],\\[|^\\[\\[|]]$"))
                .filter(not(String::isEmpty))
                .toList();
        final var expected = Arrays.stream(rawExpected.split("[\\[\\],]"))
                .filter(not(String::isEmpty))
                .skip(1)
                .toList();

        assertThat(commands.size()).isEqualTo(parameters.size());
        assertThat(expected.size()).isEqualTo(parameters.size());

        final var actual = new MyHashSet();

        for (int i = 0; i < commands.size(); i++) {
            switch (commands.get(i)) {
                case "add" -> {
                    System.out.println("Add: " + parameters.get(i));
                    actual.add(parseInt(parameters.get(i)));
                    //System.out.println(actual);
                }
                case "contains" -> {
                    System.out.println("Contains: " + parameters.get(i));
                    assertThat(actual.contains(parseInt(parameters.get(i))))
                            .as("index: " + i +
                                    ", commands: " + commands.get(i) +
                                    ", parameters: " + parameters.get(i) +
                                    ", expected: " + expected.get(i))
                            .isEqualTo(parseBoolean(expected.get(i)));
                }
                case "remove" -> {
                    System.out.println("Remove: " + parameters.get(i));
                    actual.remove(parseInt(parameters.get(i)));
                }
                default -> throw new IllegalArgumentException();
            }
        }
    }
}