package io.github.cwdesautels.medium;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * Implement the MinStack class:
 * <p>
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * <p>
 * You must implement a solution with O(1) time complexity for each function.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * Output
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -231 <= val <= 231 - 1
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 * At most 3 * 104 calls will be made to push, pop, top, and getMin.
 */
public class MinStack155Test {

    private static class MinStack {
        private Node head;

        public MinStack() {
            this.head = null;
        }

        public void push(int val) {
            if (head != null) {
                head = new Node(val, Math.min(val, head.min), head);
            } else {
                head = new Node(val, val, null);
            }
        }

        public void pop() {
            if (head != null) {
                head = head.prev;
            }
        }

        public int top() {
            return head == null ? 0 : head.value;
        }

        public int getMin() {
            return head == null ? 0 : head.min;
        }

        private record Node(int value, int min, Node prev) {
        }
    }
}