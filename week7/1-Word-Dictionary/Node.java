import java.util.HashMap;

public class Node {

    public boolean isWord;
    public HashMap<Character, Node> childs;

    public Node() {
        this.isWord = false;
        this.childs = new HashMap<Character, Node>();
    }
}
