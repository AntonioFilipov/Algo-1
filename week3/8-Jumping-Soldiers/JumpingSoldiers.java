import java.util.ArrayList;
import java.util.Scanner;

class BinaryIndexedTree {

    public int[] data;

    public BinaryIndexedTree(ArrayList<Integer> inputData, int leafsCount) {
        int usedNodes = leafsCount * 2 - 1;
        int totalNodesRequired = 1;

        while (usedNodes > totalNodesRequired) {
            totalNodesRequired *= 2;
        }

        // make complete binary tree
        this.data = new int[totalNodesRequired - 1];

        this.buildBIT(inputData, totalNodesRequired);
    }

    private void buildBIT(ArrayList<Integer> inputData, int totalRequiredNodes) {
        this.fillFirstLayer(inputData, totalRequiredNodes);

        for (int i = this.data.length / 2 - 1; i >= 0; i--) {

            // fill each node with the minimum child value
            this.data[i] = this.data[2 * i + 1] + this.data[2 * i + 2];
        }
    }

    private void fillFirstLayer(ArrayList<Integer> inputData, int totalRequiredNodes) {

        for (int i = 0; i < inputData.size(); i++) {
            this.data[this.data.length / 2 + i] = inputData.get(i);
        }
    }

    public void add(int index, int amount) {

        int changegChildIndex = this.data.length / 2 + index;
        this.data[changegChildIndex] += amount;

        while (changegChildIndex != 0) {

            // update the value of the parent
            int parentIndex = (changegChildIndex - 1) / 2;
            this.data[parentIndex] = this.data[2 * parentIndex + 1] + this.data[2 * parentIndex + 2];
            changegChildIndex = parentIndex;
        }
    }

    public void remove(int index, int amount) {

        int changegChildIndex = this.data.length / 2 + index;
        this.data[changegChildIndex] -= amount;

        if (this.data[changegChildIndex] < 0) {
            this.data[changegChildIndex] = 0;
        }

        while (changegChildIndex != 0) {

            // update the value of the parent
            int parentIndex = (changegChildIndex - 1) / 2;
            this.data[parentIndex] = this.data[2 * parentIndex + 1] + this.data[2 * parentIndex + 2];
            changegChildIndex = parentIndex;
        }
    }

    public int count(int upperBound) {

        if (upperBound == 366) {
            return this.data[0];
        }

        int rangeCount = 0;

        // select the upperBound + 1 index
        int childIndex = this.data.length / 2 + upperBound;

        while (childIndex != 0) {
            if (isRightChild(childIndex)) {
                rangeCount += this.data[childIndex - 1];
            }

            childIndex = (childIndex - 1) / 2;
        }

        return rangeCount;
    }

    private boolean isRightChild(int childIndex) {
        int parentIndex = (childIndex - 1) / 2;

        if (2 * parentIndex + 2 == childIndex) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        output.append("[");
        for (int i = 0; i < this.data.length; i++) {
            output.append(this.data[i]);

            if (i + 1 != this.data.length) {
                output.append(", ");
            }
        }
        output.append("]");

        return output.toString();
    }
}

public class JumpingSoldiers {

    private static final int MAX_VALUE = 10000;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int cols = s.nextInt();
        int rows = s.nextInt();

        BinaryIndexedTree tree;

        int maxSize = Integer.MIN_VALUE;
        int minCount = Integer.MAX_VALUE;
        int rowIndexWithBiggestSum = 0;

        for (int i = 0; i < rows; i++) {
            tree = new BinaryIndexedTree(new ArrayList<Integer>(), MAX_VALUE);

            int currentCount = 0;
            int currentSize = 0;

            for (int j = 0; j < cols; j++) {
                int number = s.nextInt();
                tree.add(number, 1);
                int numbersBiggerBefore = tree.data[0] - tree.count(number + 1);

                currentSize += number;
                currentCount += numbersBiggerBefore;
            }

            if (currentCount > maxSize) {
                maxSize = currentCount;
                rowIndexWithBiggestSum = i + 1;
            } else if (currentCount == maxSize) {
                if (currentSize < minCount) {
                    rowIndexWithBiggestSum = i + 1;
                }
            }
        }

        System.out.println(rowIndexWithBiggestSum);
    }
}