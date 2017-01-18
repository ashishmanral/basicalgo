package practice;

import java.util.*;

/**
 * Created by ashis on 1/1/2017.
 */
public class GraphTraversal {

    static class GraphNode {
        char c;
        boolean visited;
        List<GraphNode> adjacentNodes = new ArrayList<>();

        GraphNode(char c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return String.valueOf(c);
        }

        @Override
        public boolean equals(Object n) {
            return ((GraphNode)n).c == c;
        }
    }

    static class QueueNode<T> {
        T node;
        QueueNode<T> next;

        QueueNode(T node) {
            this.node = node;
        }
    }

    static class MyQueue<T> {

        QueueNode<T> head;
        QueueNode<T> tail;

        void enqueue(T node) {
            if(head == null) {
                head = tail = new QueueNode(node);
                return;
            }
            tail.next = new QueueNode(node);
            tail = tail.next;
        }

        T dequeue() {
            if(head == null) {
                return null;
            }

            T node = head.node;
            head = head.next;

            return node;
        }

        boolean isEmpty() {
            return head == null;
        }

        void printQueue() {
            System.out.print("Queue : ");
            QueueNode<T> iter = head;

            while(iter!=null) {
                System.out.print(iter.node);
                iter = iter.next;
            }
            System.out.print("\n");
        }

    }

    public static void main(String[] args) {
        System.out.println("Breadth First Traversal : ");
        breadthFirstTraversal(createGraph());
        System.out.println("\nBFS Z says node " + (breadthFirstSearch(createGraph(), new GraphNode('Z')) ? "":"not ") + "found");
        System.out.println("BFS C says node " + (breadthFirstSearch(createGraph(), new GraphNode('C')) ? "":"not ") + "found");

        System.out.println("Depth First Traversal With Recursion : ");
        depthFirstTraversalUsingRecursion(createGraph());
        System.out.println("\nRecursive DFS Z says node " + (depthFirstSearchUsingRecursion(createGraph(), new GraphNode('Z')) ? "":"not ") + "found");
        System.out.println("Recursive DFS C says node " + (depthFirstSearchUsingRecursion(createGraph(), new GraphNode('C')) ? "":"not ") + "found");

        System.out.println("Depth First Traversal Without Recursion : ");
        depthFirstTraversal(createGraph());
        System.out.println("\nDFS Z says node " + (depthFirstSearch(createGraph(), new GraphNode('Z')) ? "":"not ") + "found");
        System.out.println("DFS C says node " + (depthFirstSearch(createGraph(), new GraphNode('C')) ? "":"not ") + "found");
    }

    private static GraphNode createGraph() {
        GraphNode a = new GraphNode('A');
        GraphNode b = new GraphNode('B');
        GraphNode c = new GraphNode('C');
        GraphNode d = new GraphNode('D');
        GraphNode e = new GraphNode('E');
        GraphNode f = new GraphNode('F');
        GraphNode g = new GraphNode('G');
        GraphNode h = new GraphNode('H');
        GraphNode s = new GraphNode('S');

        a.adjacentNodes.add(b);
        a.adjacentNodes.add(s);
        b.adjacentNodes.add(a);
        c.adjacentNodes.add(s);
        c.adjacentNodes.add(d);
        c.adjacentNodes.add(e);
        c.adjacentNodes.add(f);
        d.adjacentNodes.add(c);
        e.adjacentNodes.add(c);
        e.adjacentNodes.add(h);
        f.adjacentNodes.add(c);
        f.adjacentNodes.add(g);
        g.adjacentNodes.add(s);
        g.adjacentNodes.add(f);
        g.adjacentNodes.add(h);
        h.adjacentNodes.add(g);
        h.adjacentNodes.add(e);
        s.adjacentNodes.add(a);
        s.adjacentNodes.add(c);
        s.adjacentNodes.add(g);

        return a;
    }

    private static void breadthFirstTraversal(GraphNode head) {
        MyQueue<GraphNode> queue = new MyQueue<>();

        queue.enqueue(head);
        head.visited = true;

        while(!queue.isEmpty()) {
            GraphNode node = queue.dequeue();
            System.out.print(node.c + " ");
            for(GraphNode n : node.adjacentNodes) {
                if(!n.visited) {
                    n.visited = true;
                    queue.enqueue(n);
                }
            }
        }
    }

    private static void depthFirstTraversal(GraphNode head) {
        Stack<GraphNode> stack = new Stack<>();

        stack.push(head);
        head.visited = true;
        System.out.print(head.c + " ");

        while(!stack.isEmpty()) {
            GraphNode node = stack.peek();
            boolean allVisited = true;
            for(GraphNode n : node.adjacentNodes) {
                if(!n.visited) {
                    System.out.print(n.c + " ");
                    n.visited = true;
                    stack.push(n);
                    allVisited = false;
                    break;
                }
            }
            if(allVisited) {
                stack.pop();
            }
        }
    }

    private static boolean depthFirstSearch(GraphNode head, GraphNode target) {
        Stack<GraphNode> stack = new Stack<>();

        if(head.equals(target)) {
            return true;
        }

        stack.push(head);
        head.visited = true;

        while(!stack.isEmpty()) {
            GraphNode node = stack.peek();
            boolean allVisited = true;
            for(GraphNode n : node.adjacentNodes) {
                if(!n.visited) {
                    if(n.equals(target)) return true;
                    n.visited = true;
                    stack.push(n);
                    allVisited = false;
                    break;
                }
            }
            if(allVisited) {
                stack.pop();
            }
        }

        return false;
    }

    private static void depthFirstTraversalUsingRecursion(GraphNode head) {

        head.visited = true;
        System.out.print(head.c + " ");

        for(GraphNode n : head.adjacentNodes) {
            if(!n.visited) {
                depthFirstTraversalUsingRecursion(n);
            }
        }
    }

    private static boolean depthFirstSearchUsingRecursion(GraphNode head, GraphNode target) {
        if(head.equals(target)) {
            return true;
        }

        head.visited = true;

        for(GraphNode n : head.adjacentNodes) {
            if(!n.visited) {
                boolean found = depthFirstSearchUsingRecursion(n, target);
                if(found) return true;
            }
        }

        return false;
    }

    private static boolean breadthFirstSearch(GraphNode head, GraphNode target) {
        MyQueue<GraphNode> queue = new MyQueue<>();

        queue.enqueue(head);
        head.visited = true;

        while(!queue.isEmpty()) {
            GraphNode node = queue.dequeue();
            if(node.equals(target)) {
                return true;
            }
            for(GraphNode n : node.adjacentNodes) {
                if(!n.visited) {
                    n.visited = true;
                    queue.enqueue(n);
                }
            }
        }

        return false;
    }

}
