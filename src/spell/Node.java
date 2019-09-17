package spell;

public class Node implements INode  {
    boolean isRoot;
    int count;
    String letters;
    char value;

    Node[] children;

    Node parent;

    public Node(char letter, String word) {
        value = letter;
        letters = word;
        isRoot = false;
        count = 0;
        children = new Node[26];
    }

    @Override
    public int getValue() {
        return value;
    }
}
