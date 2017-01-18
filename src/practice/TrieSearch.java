package practice;

import Utilities.Trie;
import Utilities.TrieNode;

/**
 * Created by ashis on 1/14/2017.
 */
public class TrieSearch {

    public static void main(String[] args) {
        Trie myTrie = new Trie();
        addWord(myTrie, "ashish");
        addWord(myTrie, "ashish manral");
        addWord(myTrie, "ashish manral is great");
        addWord(myTrie, "ashley benson");
        addWord(myTrie, "ashley benson is a lier");
        addWord(myTrie, "kinshuk");
        addWord(myTrie, "kinsh");
        testIsValid(myTrie, "divya");
        testIsValid(myTrie, "ashish");
        testIsValid(myTrie, "ashish ");
        testIsValid(myTrie, "kinsh");
        testIsValid(myTrie, "kinshu");
        testWordsWithPrefix(myTrie, "ash");
        testWordsWithPrefix(myTrie, "kinsh");
        testWordsWithPrefix(myTrie, "divya");
    }

    private static void testIsValid(Trie myTrie, String testWord) {
        System.out.println(testWord + " is " + (isValidWord(myTrie, testWord) ? "":"not ") + "a valid word.");
    }

    private static void testWordsWithPrefix(Trie myTrie, String prefix) {
        System.out.println("\nWords with prefix " + prefix + " are : ");
        wordsWithPrefix(myTrie, prefix);
    }

    private static void addWord(Trie trie, String word) {
        TrieNode root = trie.root;
        for(char c : word.toCharArray()) {
            TrieNode nextNode = root.nextNodes.get(c);
            if(nextNode == null) {
                nextNode = new TrieNode(c);
                root.nextNodes.put(c, nextNode);
            }
            root = nextNode;
        }
        root.isWord = true;
    }

    private static boolean isValidWord(Trie trie, String word) {
        TrieNode root = trie.root;
        for(char c : word.toCharArray()) {
            TrieNode nextNode = root.nextNodes.get(c);
            if(nextNode == null) {
                return false;
            }
            root = nextNode;
        }
        return root.isWord;
    }

    private static void wordsWithPrefix(Trie trie, String prefix) {
        TrieNode root = trie.root;
        for(char c : prefix.toCharArray()) {
            TrieNode nextNode = root.nextNodes.get(c);
            if(nextNode == null) {
                System.out.println("Prefix " + prefix + " does not exist.");
                return;
            }
            root = nextNode;
        }
        printWithPrefix(root, prefix);
    }

    private static void printWithPrefix(TrieNode root, String prefix) {
        if(root.isWord) {
            System.out.println(prefix);
        }

        for(TrieNode n : root.nextNodes.values()) {
            printWithPrefix(n, prefix + "" + n.data);
        }
    }
}
