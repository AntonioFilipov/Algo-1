public class Vector<T> {
    
    private T[] list;
    private static final int INCREASEMENT_SIZE=2;
    private static final int INITIAL_CAPACITY=4;
    private int capacity;
    private int size;

    public Vector(){
        this.list = (T[])new Object[this.INITIAL_CAPACITY];
        this.capacity = this.INITIAL_CAPACITY;
        this.size=0;
    }
    
    public Vector(int capacity){
        this.list = (T[])new Object[capacity];
        this.capacity=capacity;
        this.size=0;
    }
    
    private void extend(){
        if (this.capacity == this.size) {
            this.capacity*=this.INCREASEMENT_SIZE;;
            T[] copyArray = (T[])new Object[this.capacity];
            for (int i = 0; i < this.list.length; i++) {
                copyArray[i]=this.list[i];
            }
            this.list = copyArray;
        }
    }
    // Adds value at a specific index in the Vector.
    // Complexity: O(n)
    public void insert(int index, T value) {
        extend();
        for (int i = this.list.length-1; i >index; i--) {
            this.list[i] = this.list[i-1];
        }
        this.list[index] = value;
        this.size++;
    }

    // Adds value to the end of the Vector.
    // Complexity: O(1)
    public void add(T value) {
        extend();
        this.list[size]=value;
        this.size++;
    }

    // Returns value at a specific index in the Vector
    // Complexity: O(1)
    public T get(int index) {
        return this.list[index];
    }

//    // Removes element at the specific index
//    // Complexity: O(n)
    public void remove(int index) {
        for (int i = index; i < this.list.length-1; i++) {
            this.list[i]=this.list[i+1];
        }
        this.size--;
    }

//    // Removes element at the last index
//    // Complexity: O(1)
    public T pop() {
        return this.list[this.size-1];
    }

//    // Returns the number of elements in the Vector.
//    // Complexity: O(1)
    public int size() {
        return this.size;
    }

//    // Returns the total capacity of the Vector.
//    // Complexity: O(1)
    public int capacity() {
        return this.capacity;
    }
}