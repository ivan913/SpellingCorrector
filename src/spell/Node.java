package spell;

import java.util.TreeMap;

public class Node implements INode  {
    private boolean isRoot;

    int count;
    private char value;

    TreeMap<Character, Node> nodes;
    private Node parent;

    public Node(char letter, Node parent) {
        value = letter;
        isRoot = false;
        count = 0;
        this.parent = parent;
    }

    public Node(){
        isRoot = true;
    }

    public void add(String letters){ //TODO: Test this
        if(letters.length() == 0){
            count += 1;
            return;
        }

        Character currentCharacter = letters.charAt(0);

        if(!nodes.containsKey(currentCharacter)){
            Node newNode = new Node(currentCharacter, this);
        }

        String remainingLetters = letters.substring(1);

        nodes.get(currentCharacter).add(remainingLetters);

    }

    public Node find(String letters){
        if(letters.length() == 0 && count > 0){
            return this;
        }

        Character currentCharacter = letters.charAt(0);

        if(!nodes.containsKey(currentCharacter)){
            return null;
        }

        String remainingLetters = letters.substring(1);

        Node returnNode = nodes.get(currentCharacter).find(remainingLetters);

        return returnNode;
    }

    @Override
    public int getValue() {
        return count;
    }
}
