import java.util.LinkedList;
import java.util.Queue;


class Stack<T> {

    private Queue<T> qe;
    
    public Stack(){
        this.qe = new LinkedList<T>();
    }
    
    // Adds value to the end of the Stack.
    // Complexity: O(1)
    public void push(T value) {
        this.qe.add(value);
    }

    // Returns value from the end of the Stack and removes it.
    // Complexity: O(n)
    public T pop() {
        Queue<T> copy = new LinkedList<T>(this.qe);
        this.qe.clear();
        T last=copy.peek();
        T current = copy.peek();
        while (copy.size() != 0) {
            if (copy.size() == 1) {
                last = copy.poll();
                break;
            }
            current = copy.poll();
            this.qe.add(current);
        }

        return last;
    }

    // Returns value from the end of the Stack without removing it.
    // Complexity: O(n)
    public T peek() {
        Queue<T> copy = new LinkedList<T>(this.qe);
        this.qe.clear();
        T last=copy.peek();
        T current = copy.peek();
        while (copy.size() != 0) {
            if (copy.size() == 1) {
                last = copy.peek();
            }
            current = copy.poll();
            this.qe.add(current);
        }
        System.out.println(this.qe);
        return last;
    }

    // Returns the number of elements in the Stack.
    // Complexity: O(1)
    public int size() {
        return this.qe.size();
    }
}