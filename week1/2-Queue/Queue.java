import java.util.LinkedList;

class Queue<T> {

    private LinkedList list;

    public Queue() {
        this.list = new LinkedList();
    }

    // Adds value to the end of the Queue.
    // Complexity: O(1)
    public void push(T value) {
        this.list.add(value);
    }

    // Returns value from the front of the Queue and removes it.
    // Complexity: O(1)
    public T pop() {
        T first = this.peek();
        this.list.remove(0);
        return first;
    }

    // Returns value from the front of the Queue without removing it.
    // Complexity: O(1)
    public T peek() {
        return (T) this.list.get(0);
    }

    // Returns the number of elements in the Queue.
    // Complexity: O(1)
    public int size() {
        return this.list.size();
    }
}