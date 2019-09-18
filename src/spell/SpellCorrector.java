package spell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SpellCorrector implements ISpellCorrector {
    Trie trie;

    public SpellCorrector(){
        trie = new Trie();
    }

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        FileReader reader = new FileReader(dictionaryFileName);
        Scanner scanner = new Scanner(reader);

        while(scanner.hasNext()){
            String word = scanner.next();
            trie.add(word);
        }

        scanner.close();
    }

    @Override
    public String suggestSimilarWord(String inputWord) {


        return null;
    }
}
