public class Trie {
    private Node head;

    public Trie() {
        this.head = new Node();
    }

    public void insert(String word) {
        Node headCpy = head;

        for (int i = 0; i < word.length(); i++) {
            Node nextNode = null;
            char currentChar = word.charAt(i);

            if (headCpy.childs.containsKey(currentChar)) {
                nextNode = headCpy.childs.get(currentChar);
            } else {
                headCpy.childs.put(currentChar, new Node());
                nextNode = headCpy.childs.get(currentChar);
            }

            headCpy = nextNode;
        }

        headCpy.isWord = true;
    }

    public boolean contains(String word) {
        Node headCpy = this.head;

        for (int i = 0; i < word.length(); i++) {
            Node nextNode = null;
            char currentChar = word.charAt(i);

            if (headCpy.childs.containsKey(currentChar)) {
                nextNode = headCpy.childs.get(currentChar);
            } else {
                return false;
            }

            headCpy = nextNode;
        }

        return headCpy.isWord;
    }
}
