package spell;

import java.io.File;

public class Trie implements ITrie {
    int wordCount;
    int nodeCount;

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
        return null;
    }

    @Override
    public int getWordCount() {
        return 0;
    }

    @Override
    public int getNodeCount() {
        return 0;
    }
}
