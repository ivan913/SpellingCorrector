package spell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

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
        Node foundNode = (Node) trie.find(inputWord);
        if (foundNode != null) return foundNode.getWord();

        ArrayList<String> combinedList = findFirstSimilarWord(inputWord);

        TreeSet<String> stringSet = new TreeSet<String>();

        if(combinedList.size() > 1){
            int most = getMost(combinedList);

            for(int i = 0; i < combinedList.size(); i++){
                String currentWord = combinedList.get(i);
                Node currentNode = (Node) trie.find(currentWord);
                int currentValue = currentNode.getValue();
                if (currentValue == most) stringSet.add(currentWord);
            }

            return stringSet.first();
        }
        else if(combinedList.size() == 1){
            return combinedList.get(0);
        }

        ArrayList<String> secondCombinedList = findSecondSimilarWord(inputWord);

        TreeSet<String> secondStringSet = new TreeSet<String>();

        if(secondCombinedList.size() > 1){
            int most = getMost(secondCombinedList);

            for(int i = 0; i < secondCombinedList.size(); i++){
                String currentWord = secondCombinedList.get(i);
                Node currentNode = (Node) trie.find(currentWord);
                if (currentNode.getValue() == most) secondStringSet.add(currentWord);
            }

            return secondStringSet.first();
        }
        else if(secondCombinedList.size() == 1){
            return secondCombinedList.get(0);
        }

        return null;
    }

    public ArrayList<String> findFirstSimilarWord(String inputWord){
        ArrayList<String> deleteList = delete(inputWord);
        ArrayList<String> transposeList = transpose(inputWord);
        ArrayList<String> alterList = alter(inputWord);
        ArrayList<String> insertList = insert(inputWord);

        ArrayList<String> combinedList = new ArrayList<String>();

        combinedList.addAll(deleteList);
        combinedList.addAll(transposeList);
        combinedList.addAll(alterList);
        combinedList.addAll(insertList);

        return combinedList;
    }

    public ArrayList<String> findSecondSimilarWord(String inputWord){
        ArrayList<String> deleteList = secondDelete(inputWord);
        ArrayList<String> transposeList = secondTranspose(inputWord);
        ArrayList<String> alterList = secondAlter(inputWord);
        ArrayList<String> insertList = secondInsert(inputWord);

        ArrayList<String> combinedList = new ArrayList<String>();

        combinedList.addAll(deleteList);
        combinedList.addAll(transposeList);
        combinedList.addAll(alterList);
        combinedList.addAll(insertList);

        return combinedList;
    }

    public ArrayList<String> secondDelete(String inputWord){
        StringBuilder builder;
        ArrayList<String> stringList = new ArrayList<String>();

        for(int i = 0; i < inputWord.length(); i += 1){
            builder = new StringBuilder(inputWord);
            builder.deleteCharAt(i);
            String testString = builder.toString();
            stringList.addAll(findFirstSimilarWord(testString));
        }

        return stringList;
    }

    public ArrayList<String> secondTranspose(String inputWord){
        StringBuilder builder;
        ArrayList<String> stringList = new ArrayList<String>();

        for(int i = 0; i < inputWord.length()-1; i += 1){
            builder = new StringBuilder(inputWord);
            Character first = builder.charAt(i);
            Character second = builder.charAt(i+1);

            builder.setCharAt(i, second);
            builder.setCharAt(i+1, first);

            String testString = builder.toString();

            stringList.addAll(findFirstSimilarWord(testString));
        }

        return stringList;
    }

    public ArrayList<String> secondAlter(String inputWord){
        StringBuilder builder;
        ArrayList<String> stringList = new ArrayList<String>();

        for(int i = 0; i < inputWord.length(); i += 1){

            for(char newChar = 97; newChar <= 122; newChar++){
                builder = new StringBuilder(inputWord);
                builder.setCharAt(i, newChar);
                String testString = builder.toString();

                stringList.addAll(findFirstSimilarWord(testString));
            }

        }
        return stringList;
    }

    public ArrayList<String> secondInsert(String inputWord){
        StringBuilder builder;
        ArrayList<String> stringList = new ArrayList<String>();

        for(int i = 0; i <= inputWord.length(); i += 1){

            for(char newChar = 97; newChar <= 122; newChar++){
                builder = new StringBuilder(inputWord);
                builder.insert(i, newChar);
                String testString = builder.toString();
                ArrayList<String> newList = findFirstSimilarWord(testString);
                stringList.addAll(newList);
            }

        }

        return stringList;
    }

    public ArrayList<String> delete(String inputWord){
        StringBuilder builder;
        ArrayList<String> stringList = new ArrayList<String>();

        for(int i = 0; i < inputWord.length(); i += 1){
            builder = new StringBuilder(inputWord);
            builder.deleteCharAt(i);
            String testString = builder.toString();
            Node foundNode = (Node) trie.find(testString);

            if(foundNode != null){
                stringList.add(testString);
            }

        }

        return stringList;
    }



    public ArrayList<String> transpose(String inputWord){
        StringBuilder builder;
        ArrayList<String> stringList = new ArrayList<String>();

        for(int i = 0; i < inputWord.length()-1; i += 1){
            builder = new StringBuilder(inputWord);
            Character first = builder.charAt(i);
            Character second = builder.charAt(i+1);

            builder.setCharAt(i, second);
            builder.setCharAt(i+1, first);

            String testString = builder.toString();

            Node foundNode = (Node) trie.find(testString);

            if(foundNode != null){
                stringList.add(testString);
            }
        }

        return stringList;
    }

    public ArrayList<String> alter(String inputWord){
        StringBuilder builder;
        ArrayList<String> stringList = new ArrayList<String>();

        for(int i = 0; i < inputWord.length(); i += 1){

            for(char newChar = 97; newChar <= 122; newChar++){
                builder = new StringBuilder(inputWord);
                builder.setCharAt(i, newChar);
                String testString = builder.toString();

                Node foundNode = (Node) trie.find(testString);

                if(foundNode != null){
                    stringList.add(testString);
                }

            }

        }

        return stringList;
    }

    public ArrayList<String> insert(String inputWord){
        StringBuilder builder;
        ArrayList<String> stringList = new ArrayList<String>();

        for(int i = 0; i <= inputWord.length(); i += 1){

            for(char newChar = 97; newChar <= 122; newChar++){
                builder = new StringBuilder(inputWord);
                builder.insert(i, newChar);
                String testString = builder.toString();

                Node foundNode = (Node) trie.find(testString);

                if(foundNode != null){
                    stringList.add(testString);
                }
            }

        }

        return stringList;
    }

    public int getMost(ArrayList<String> stringList){
        int most = 0;

        for(int i = 0; i < stringList.size(); i += 1){
            Node currentNode = (Node) trie.find(stringList.get(i));
            int currentValue = currentNode.getValue();
            if(currentValue > most){
                most = currentValue;
            }
        }

        return most;
    }
}
