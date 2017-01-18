package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ashis on 12/26/2016.
 */
public class BST {

    private static class Tree {
        Integer value;
        Tree left;
        Tree right;

        Tree(Integer value) {
            this.value = value;
        }
    }

    private static Tree root = null;
    private static int bstNodeHop = 0;

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        for(int i=1;i<=100;++i) {
            arr.add(i);
        }
        Collections.shuffle(arr);
        insertInBST(arr);

        validateBST(root);
        System.out.println("");

        for(int i = 1; i<=200 ; ++i) {
            System.out.println("Number " + i + (searchBST(root, i) ? "" : " not") + " found with " + bstNodeHop + " hops.");
            bstNodeHop = 0;
        }

    }

    private static void insertInBST(List<Integer> arr) {
        root = new Tree(arr.get(0));
        for(int i = 1 ; i < arr.size(); ++i) {
            insertNode(root, arr.get(i));
        }
    }

    private static void insertNode(Tree root, int value) {
        if(root.value > value) {
            if(root.left == null) {
                root.left = new Tree(value);
                return;
            }
            insertNode(root.left, value);
        } else {
            if(root.right == null) {
                root.right = new Tree(value);
                return;
            }
            insertNode(root.right, value);
        }
    }

    private static void validateBST(Tree node) {
        if(node == null) return;

        validateBST(node.left);
        System.out.print(node.value + " ");
        validateBST(node.right);
    }

    private static boolean searchBST(Tree node, int value) {
        ++bstNodeHop;
        if(node == null) return false;

        if(node.value == value) {
            return true;
        } else if(node.value < value) {
            return searchBST(node.right, value);
        } else {
            return searchBST(node.left, value);
        }
    }
}
