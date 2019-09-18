package spell;

import java.io.File;

public class Trie implements ITrie {
    Node root;

    public Trie(){
        root = new Node();
    }

    @Override
    public void add(String word) {
        String lowerCaseWord = word.toLowerCase();


        root.add(lowerCaseWord);
    }

    @Override
    public INode find(String word) {
        String lowerCaseWord = word.toLowerCase();
        Node returnNode = root.find(lowerCaseWord);
        return returnNode;
    }

    @Override
    public int getWordCount() {
        return root.getWordCount();
    }

    @Override
    public int getNodeCount() {
        return root.getNodeCount();
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Trie)) return false;
        Trie otherTrie = (Trie) o;
        Node otherRoot = otherTrie.getRoot();

        return root.equals(otherRoot);
    }

    @Override
    public int hashCode(){
        int hash = root.hashCode();

        return hash;
    }

    public Node getRoot(){
        return root;
    }
}
