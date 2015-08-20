import java.util.ArrayList;

public class RMQBinaryIndexedTree {

    public int[] data;

    public RMQBinaryIndexedTree(ArrayList<Integer> inputData, int leafsCount) {
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
            this.data[i] = Math.max(this.data[2 * i + 1], this.data[2 * i + 2]);
        }
    }

    private void fillFirstLayer(ArrayList<Integer> inputData, int totalRequiredNodes) {

        for (int i = 0; i < inputData.size(); i++) {
            this.data[this.data.length / 2 + i] = inputData.get(i);
        }
    }

    public void set(int index, int amount) {

        int changegChildIndex = this.data.length / 2 + index;
        this.data[changegChildIndex] = amount;

        while (changegChildIndex != 0) {

            // update the value of the parent
            int parentIndex = (changegChildIndex - 1) / 2;
            int minNumber = Math.max(this.data[2 * parentIndex + 1], this.data[2 * parentIndex + 2]);

            if (this.data[parentIndex] == minNumber) {
                break;
            }

            this.data[parentIndex] = Math.max(this.data[2 * parentIndex + 1], this.data[2 * parentIndex + 2]);
            changegChildIndex = parentIndex;
        }
    }

    public int max(int start, int end) {

        int biggestNumber = Integer.MIN_VALUE;

        start = this.data.length / 2 + start;
        end = this.data.length / 2 + end;

        int currentNumber = Math.max(this.data[start], this.data[end]);

        biggestNumber = biggestNumber < currentNumber ? currentNumber : biggestNumber;

        while (true) {
            if (!isRightChild(start)) {
                start = (start - 1) / 2;
            } else {
                start = (start - 1) / 2 + 1;
            }

            if (isRightChild(end)) {
                end = (end - 1) / 2;
            } else {
                end = (end - 1) / 2 - 1;
            }

            if (start > end) {
                break;
            } else {
                currentNumber = Math.max(this.data[start], this.data[end]);
                biggestNumber = biggestNumber < currentNumber ? currentNumber : biggestNumber;

                if (start == end) {
                    break;
                }
            }
        }

        return biggestNumber;
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