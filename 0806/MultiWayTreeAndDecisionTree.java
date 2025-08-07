import java.util.*;

public class MultiWayTreeAndDecisionTree {
    static class MultiWayTreeNode {
        String value;
        List<MultiWayTreeNode> children;

        MultiWayTreeNode(String value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        void addChild(MultiWayTreeNode child) {
            children.add(child);
        }
    }

    static void dfs(MultiWayTreeNode node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        for (MultiWayTreeNode child : node.children) {
            dfs(child);
        }
    }

    static void bfs(MultiWayTreeNode root) {
        if (root == null) return;
        Queue<MultiWayTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            MultiWayTreeNode node = queue.poll();
            System.out.print(node.value + " ");
            queue.addAll(node.children);
        }
    }

    static int treeHeight(MultiWayTreeNode node) {
        if (node == null || node.children.isEmpty()) return 1;
        int max = 0;
        for (MultiWayTreeNode child : node.children) {
            max = Math.max(max, treeHeight(child));
        }
        return max + 1;
    }

    static void printNodeDegrees(MultiWayTreeNode node) {
        if (node == null) return;
        System.out.println("節點: " + node.value + "，度數: " + node.children.size());
        for (MultiWayTreeNode child : node.children) {
            printNodeDegrees(child);
        }
    }

    static void simpleDecisionTree(String input) {
        if (input == null) return;
        if (input.equals("大")) {
            System.out.println("太大了，往左邊猜！");
        } else if (input.equals("小")) {
            System.out.println("太小了，往右邊猜！");
        } else if (input.equals("對")) {
            System.out.println("恭喜猜對了！");
        } else {
            System.out.println("輸入無效！");
        }
    }

    public static void main(String[] args) {
        MultiWayTreeNode root = new MultiWayTreeNode("A");
        MultiWayTreeNode b = new MultiWayTreeNode("B");
        MultiWayTreeNode c = new MultiWayTreeNode("C");
        MultiWayTreeNode d = new MultiWayTreeNode("D");
        MultiWayTreeNode e = new MultiWayTreeNode("E");
        MultiWayTreeNode f = new MultiWayTreeNode("F");

        root.addChild(b);
        root.addChild(c);
        b.addChild(d);
        b.addChild(e);
        c.addChild(f);

        System.out.print("DFS: ");
        dfs(root);
        System.out.println();

        System.out.print("BFS: ");
        bfs(root);
        System.out.println();

        System.out.println("樹的高度: " + treeHeight(root));
        printNodeDegrees(root);

        System.out.println("模擬決策樹：");
        simpleDecisionTree("大");
        simpleDecisionTree("小");
        simpleDecisionTree("對");
        simpleDecisionTree("亂輸入");
    }
}
