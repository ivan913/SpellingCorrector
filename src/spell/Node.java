package spell;

public class Node implements INode  {
    boolean isRoot;
    int count;
    String letters;

    char value;

    Node[] nodes = new Node[26];

    public Node(char letter, String word) {
        value = letter;
        letters = word;
        isRoot = false;
        count = 0;
    }

    @Override
    public int getValue() {
        return value;
    }
}
