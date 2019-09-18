package spell;

import java.util.TreeMap;

public class Node implements INode  {
    private boolean isRoot;

    int count;
    private char value;
    String word;

    TreeMap<Character, Node> nodes;
    private Node parent;

    public Node(char letter, Node parent) {
        value = letter;
        isRoot = false;
        count = 0;
        this.parent = parent;
        nodes = new TreeMap<Character,Node>();
    }

    public Node(){
        isRoot = true;
        nodes = new TreeMap<Character,Node>();
    }

    public void add(String letters, String newWord){ //TODO: Test this
        if(letters.length() == 0){
            count += 1;
            word = newWord;
            return;
        }

        Character currentCharacter = letters.charAt(0);

        if(!nodes.containsKey(currentCharacter)){
            Node newNode = new Node(currentCharacter, this);
            nodes.put(currentCharacter, newNode);
        }

        String remainingLetters = letters.substring(1);

        nodes.get(currentCharacter).add(remainingLetters, newWord);
    }

    public Node find(String letters){
        if(letters.length() == 0 && count > 0){
            return this;
        }
        if(letters.length() == 0 && count == 0){
            return null;
        }

        Character currentCharacter = letters.charAt(0);

        if(!nodes.containsKey(currentCharacter)){
            return null;
        }

        String remainingLetters = letters.substring(1);

        Node returnNode = nodes.get(currentCharacter).find(remainingLetters);

        return returnNode;
    }

    public int getNodeCount(){
        if(nodes.size() == 0){
            return 1;
        }

        int total = 1;

        for(Character c : nodes.keySet()){
            total += nodes.get(c).getNodeCount();
        }

        return total;
    }

    public int getWordCount(){
        int total = 0;
        if(count > 0){
            total += 1;
        }
        for(Character c : nodes.keySet()){
            total += nodes.get(c).getWordCount();
        }

        return total;
    }

    @Override
    public int getValue() {
        return count;
    }

    public char getLetter(){
        return value;
    }

    public int getSize(){
        return nodes.size();
    }

    public TreeMap<Character,Node> getMap(){
        return nodes;
    }

    public boolean equals(Node otherNode){
        if(count != otherNode.getValue()) return false;
        if(value != otherNode.getLetter()) return false;
        if(nodes.size() != otherNode.getSize()) return false;

        TreeMap<Character,Node> otherNodes = otherNode.getMap();

        for(Character c : nodes.keySet()){
            if(!otherNodes.containsKey(c)) return false;
            if(!nodes.get(c).equals(otherNodes.get(c))) return false;
        }

        return true;
    }

    public int hashCode(){
        int hashCode = 12;
        if(count > 0){
            hashCode = (hashCode * value)/(count + 1);
        }
        for(Character c : nodes.keySet()){
            hashCode = (hashCode + (nodes.get(c).hashCode()));
        }

        hashCode = hashCode/(nodes.size() + 1);

        return hashCode;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();

        if(count > 0){
            builder.append(word);
            builder.append('\n');
        }

        for(Character c : nodes.keySet()){
            builder.append(nodes.get(c).toString());
        }

        return builder.toString();
    }

    public String getWord() {
        return word;
    }
}
