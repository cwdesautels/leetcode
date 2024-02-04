package io.github.cwdesautels.medium;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LRUCache146Test {
    private static class LRUCache {
        private final int capacity;
        private final Map<Integer, Node> values;
        private Node lru;
        private Node mru;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.values = new HashMap<>(capacity);
        }

        public int get(int key) {
            if (values.containsKey(key)) {
                final var node = values.get(key);

                remove(node);
                insert(node);

                return node.value;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            final Node node;

            if (values.containsKey(key)) {
                node = values.get(key);
                node.value = value;

                remove(node);
            } else {
                node = new Node(key, value);

                values.put(key, node);
            }

            insert(node);

            if (values.size() > capacity) {
                values.remove(lru.key);

                remove(lru);
            }
        }

        private void insert(Node node) {
            if (mru == null) {
                lru = mru = node;
            } else {
                mru.next = node;
                node.prev = mru;
                mru = node;
            }
        }

        private void remove(Node node) {
            final var next = node.next;
            final var prev = node.prev;

            if (next == null) {
                mru = prev;
            } else {
                next.prev = prev;
            }

            if (prev == null) {
                lru = next;
            } else {
                prev.next = next;
            }

            node.next = node.prev = null;
        }

        private static class Node {
            private final int key;
            private int value;
            private Node next;
            private Node prev;

            private Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        @Override
        public String toString() {
            final var buffer = new StringBuilder(128)
                    .append("LRUCache{");

            var node = lru;

            if (node != null) {
                buffer.append(node.key)
                        .append('=')
                        .append(node.value);

                node = node.next;
            }

            while (node != null) {
                buffer.append(',')
                        .append(node.key)
                        .append('=')
                        .append(node.value);

                node = node.next;
            }

            return buffer.append('}').toString();
        }
    }


    @Test
    void test1() {
        final LRUCache lRUCache = new LRUCache(2);

        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}

        assertThat(lRUCache.get(1)).isEqualTo(1);    // return 1

        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}

        assertThat(lRUCache.get(2)).isEqualTo(-1);    // returns -1 (not found)

        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}

        assertThat(lRUCache.get(1)).isEqualTo(-1);   // return -1 (not found)
        assertThat(lRUCache.get(3)).isEqualTo(3);    // return 3
        assertThat(lRUCache.get(4)).isEqualTo(4);    // return 4
    }

    @Test
    void test2() {
        final LRUCache lRUCache = new LRUCache(2);

        lRUCache.put(2, 1);
        lRUCache.put(1, 1);
        lRUCache.put(2, 3);
        lRUCache.put(4, 1);

        assertThat(lRUCache.get(1)).isEqualTo(-1);
        assertThat(lRUCache.get(2)).isEqualTo(3);
    }

    @Test
    void test3() {
        final LRUCache lRUCache = new LRUCache(3);

        lRUCache.put(1, 1);
        System.out.println(lRUCache);
        lRUCache.put(2, 2);
        System.out.println(lRUCache);
        lRUCache.put(3, 3);
        System.out.println(lRUCache);
        lRUCache.put(4, 4);
        System.out.println(lRUCache);
        assertThat(lRUCache.get(4)).isEqualTo(4);
        System.out.println(lRUCache);
        assertThat(lRUCache.get(3)).isEqualTo(3);
        System.out.println(lRUCache);
        assertThat(lRUCache.get(2)).isEqualTo(2);
        System.out.println(lRUCache);
        assertThat(lRUCache.get(1)).isEqualTo(-1);
        System.out.println(lRUCache);
        lRUCache.put(5, 5);
        System.out.println(lRUCache);
        assertThat(lRUCache.get(1)).isEqualTo(-1);
        System.out.println(lRUCache);
        assertThat(lRUCache.get(2)).isEqualTo(2);
        System.out.println(lRUCache);
        assertThat(lRUCache.get(3)).isEqualTo(3);
        System.out.println(lRUCache);
        assertThat(lRUCache.get(4)).isEqualTo(-1);
        System.out.println(lRUCache);
        assertThat(lRUCache.get(5)).isEqualTo(5);
        System.out.println(lRUCache);
    }

    @Test
    void test4() {
//        final int[][] arr =         [
//                [10],[10, 13],[3, 17],[6, 11],[10, 5],[9, 10],[13],[2, 19],[2],[3],[5, 25],[8],[
//        9, 22],[5, 5],[1, 30],[11],[9, 12],[7],[5],[8],[9],[4, 30],[9, 3],[9],[10],[10],[6, 14],[3, 1],[3],[10, 11],[8],[
//        2, 14],[1],[5],[4],[11, 4],[12, 24],[5, 18],[13],[7, 23],[8],[12],[3, 27],[2, 12],[5],[2, 9],[13, 4],[8, 18],[
//        1, 7],[6],[9, 29],[8, 21],[5],[6, 30],[1, 12],[10],[4, 15],[7, 22],[11, 26],[8, 17],[9, 29],[5],[3, 4],[11, 30],[
//        12],[4, 29],[3],[9],[6],[3, 4],[1],[10],[3, 29],[10, 28],[1, 20],[11, 13],[3],[3, 12],[3, 8],[10, 9],[3, 26],[8],[
//        7],[5],[13, 17],[2, 27],[11, 15],[12],[9, 19],[2, 15],[3, 16],[1],[12, 17],[9, 1],[6, 19],[4],[5],[5],[8, 1],[
//        11, 7],[5, 2],[9, 28],[1],[2, 2],[7, 4],[4, 22],[7, 24],[9, 26],[13, 28],[11, 26]
//        ]
    }
}