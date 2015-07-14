package week3;

import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;

class Heap<T> {

    private ArrayList<T> arr;
    private Comparator<T> comparator;

    public Heap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.arr = new ArrayList<T>();
    }

    public void push(T value) {
        this.arr.add(value);
        this.siftUp(this.arr.size() - 1);
    }

    public T pop() {
        T head = this.peek();

        swap(0, this.arr.size() - 1);
        this.arr.remove(this.arr.size() - 1);
        this.siftDown(0, this.arr.size());

        return head;
    }

    public T peek() {
        return this.arr.get(0);
    }

    private void swap(int i, int j) {
        T temp = this.arr.get(i);
        this.arr.set(i, this.arr.get(j));
        this.arr.set(j, temp);
    }

    public void heapify() {

        for (int i = this.getSize() / 2 - 1; i >= 0; i--) {
            this.siftDown(i, this.arr.size());
        }
    }

    private void siftUp(int index) {

        while (index > 0) {
            T currentParent = this.arr.get((index - 1) / 2);

            if (this.comparator.compare(this.arr.get(index), currentParent) > 0) {
                swap(index, (index - 1) / 2);
                siftUp((index - 1) / 2);
            } else {
                break;
            }
        }
    }

    private void siftDown(int index, int size) {

        while (index <= size / 2 - 1) {
            T currentParent = this.arr.get(index);

            int leftChildIndex = 2 * index + 1;
            leftChildIndex = leftChildIndex >= size ? -1 : leftChildIndex;

            int rightChildIndex = 2 * index + 2;
            rightChildIndex = rightChildIndex >= size ? -1 : rightChildIndex;

            int maxChildIndex = index;

            if (leftChildIndex != -1 && rightChildIndex != -1) {
                maxChildIndex = this.comparator.compare(this.arr.get(leftChildIndex), this.arr.get(rightChildIndex)) > 0 ? leftChildIndex
                        : rightChildIndex;
            } else if (leftChildIndex != -1) {
                maxChildIndex = leftChildIndex;
            } else if (rightChildIndex != -1) {
                maxChildIndex = rightChildIndex;
            }

            if (this.comparator.compare(this.arr.get(maxChildIndex), currentParent) > 0) {
                swap(index, maxChildIndex);
                siftDown(maxChildIndex, size);
            } else {
                break;
            }
        }
    }

    public int getSize() {
        return this.arr.size();
    }
}

public class OnlineMedian {

    private Heap<Integer> minHeap;
    private Heap<Integer> maxHeap;
    private Integer median;
    private int balance = 0;

    private StringBuilder result = new StringBuilder();

    public OnlineMedian() {
        this.minHeap = new Heap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        this.maxHeap = new Heap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
    }

    // inserts the number and returns the median
    public void insert(Integer number) {

        if (median == null) {
            minHeap.push(number);
            balance = 1;
            median = minHeap.peek();
        } else {

            switch (balance) {
                case 1:
                    if (number <= median) { // median stays the same
                        maxHeap.push(number);
                    } else {
                        minHeap.push(number);
                        maxHeap.push(minHeap.pop());
                        median = minHeap.peek();
                    }
                    balance = 0;
                    break;
                case 0:
                    if (number < median) {
                        maxHeap.push(number);
                        balance = -1;
                        median = maxHeap.peek();
                    } else {
                        minHeap.push(number);
                        balance = 1;
                        median = minHeap.peek();
                    }
                    break;
                case -1:
                    if (number <= median) {
                        maxHeap.push(number);
                        minHeap.push(maxHeap.pop());
                    } else { // median stays the same
                        minHeap.push(number);
                    }
                    balance = 0;
                    median = minHeap.peek();
                    break;
            }
        }

        result.append(this.median + "\n");
    }

    public static void main(String[] args) {
        OnlineMedian m = new OnlineMedian();

        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        for (int i = 0; i < n; i++) {
            m.insert(s.nextInt());
        }

        System.out.println(m.result.substring(0, m.result.length() - 1));
    }
}