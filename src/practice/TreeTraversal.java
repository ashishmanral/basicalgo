package practice;

import Utilities.MyTree;
import Utilities.TreeNode;

/**
 * Created by ashis on 1/14/2017.
 */
public class TreeTraversal {

    public static void main(String[] args) {
        TreeNode<Integer> root = getRoot();
        System.out.println("In Order Traversal : ");
        inOrder(root);
        System.out.println("\nPre Order Traversal : ");
        preOrder(root);
        System.out.println("\nPost Order Traversal : ");
        postOrder(root);
    }

    private static TreeNode<Integer> getRoot() {
        MyTree<Integer> tree = new MyTree<>();
        TreeNode<Integer> node1 = new TreeNode<>(10);
        TreeNode<Integer> node2 = new TreeNode<>(20);
        TreeNode<Integer> node3 = new TreeNode<>(30);
        TreeNode<Integer> node4 = new TreeNode<>(40);
        TreeNode<Integer> node5 = new TreeNode<>(50);
        TreeNode<Integer> node6 = new TreeNode<>(60);
        TreeNode<Integer> node7 = new TreeNode<>(70);

        tree.root = node4;
        node4.left = node2;
        node2.left = node1;
        node2.right = node3;
        node4.right = node6;
        node6.left = node5;
        node6.right = node7;

        return tree.root;
    }

    public static void inOrder(TreeNode<Integer> root) {
        if(root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    private static void preOrder(TreeNode<Integer> root) {
        if(root != null) {
            System.out.print(root.data + " ");
            inOrder(root.left);
            inOrder(root.right);
        }
    }

    private static void postOrder(TreeNode<Integer> root) {
        if(root != null) {
            inOrder(root.left);
            inOrder(root.right);
            System.out.print(root.data + " ");
        }
    }
}
