import java.util.ArrayList;

public class HashMap {

    private ArrayList<ArrayList<Pair>> data;
    private int modul = 31;
    private int totalElement = 0;

    private final double Load_Factor = 0.75;
    private double threshold = 0;

    public HashMap() {
        this.data = new ArrayList<ArrayList<Pair>>();
        this.updateThreshold();

        for (int i = 0; i < this.modul; i++) {
            this.data.add(new ArrayList<Pair>());
        }
    }

    public void add(Integer number) {
        int numberHash = getHashCodeInt(number);

        if (this.contains(number)) {
            for (int i = 0; i < this.data.get(numberHash).size(); i++) {
                Pair currentElement = this.data.get(numberHash).get(i);

                if (currentElement.key == number) {
                    currentElement.value++;
                    break;
                }
            }
        } else {

            this.data.get(numberHash).add(new Pair(number, 1));
            this.totalElement++;

            if (this.totalElement >= this.threshold) {
                resizeTheData();
            }
        }
    }

    public void remove(Integer number) {
        int numberHash = getHashCodeInt(number);

        int currentListLenght = this.data.get(numberHash).size();
        for (int i = 0; i < currentListLenght; i++) {
            if (this.data.get(numberHash).get(i).key == number) {
                this.data.get(numberHash).remove(i);
                this.totalElement--;
                break;
            }
        }
    }

    public boolean contains(Integer number) {

        int probablyIndexList = getHashCodeInt(number);
        for (int i = 0; i < this.data.get(probablyIndexList).size(); i++) {
            if (this.data.get(probablyIndexList).get(i).key == number) {
                return true;
            }
        }

        return false;
    }

    public int getValue(Integer number) {
        int count = 0;

        int probablyIndexList = getHashCodeInt(number);
        for (int i = 0; i < this.data.get(probablyIndexList).size(); i++) {
            if (this.data.get(probablyIndexList).get(i).key == number) {
                return this.data.get(probablyIndexList).get(i).value;
            }
        }

        return count;
    }

    private void resizeTheData() {
        ArrayList<ArrayList<Pair>> newList = new ArrayList<ArrayList<Pair>>();

        // Resize it double
        int newSize = this.data.size() * 2;
        this.modul *= 2;
        this.updateThreshold();

        for (int i = 0; i < newSize; i++) {
            newList.add(new ArrayList<Pair>());
        }

        for (int i = 0; i < this.data.size(); i++) {
            for (int j = 0; j < this.data.get(i).size(); j++) {
                Pair currentElement = this.data.get(i).get(j);
                newList.get(getHashCodeInt(currentElement.key)).add(new Pair(currentElement.key, currentElement.value));
            }
        }

        this.data = newList;
    }

    private void updateThreshold() {
        this.threshold = this.modul * this.Load_Factor;
    }

    private int getHashCodeInt(Integer number) {

        int hashCode = Math.abs(number % this.modul);
        return hashCode;
    }
}
