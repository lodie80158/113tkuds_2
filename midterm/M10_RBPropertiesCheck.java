package midterm;

import java.util.*;

public class M10_RBPropertiesCheck {
    static class Node {
        int val;
        char color;
        Node left, right;
        Node(int v, char c) { val = v; color = c; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            String c = sc.next();
            char color = val == -1 ? 'B' : c.charAt(0); 
            nodes[i] = new Node(val, color);
        }

        for (int i = 0; i < n; i++) {
            if (nodes[i].val == -1) continue;
            int left = 2 * i + 1, right = 2 * i + 2;
            if (left < n) nodes[i].left = nodes[left].val == -1 ? null : nodes[left];
            if (right < n) nodes[i].right = nodes[right].val == -1 ? null : nodes[right];
        }

        Node root = nodes[0];

        if (root.color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        for (int i = 0; i < n; i++) {
            Node cur = nodes[i];
            if (cur == null || cur.val == -1) continue;
            if (cur.color == 'R') {
                if ((cur.left != null && cur.left.color == 'R') || (cur.right != null && cur.right.color == 'R')) {
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
            }
        }

       
        int blackHeight = getBlackHeight(root);
        if (blackHeight == -1) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        System.out.println("RB Valid");
    }

  
    private static int getBlackHeight(Node node) {
        if (node == null) return 1; // NIL 視為黑
        int leftBH = getBlackHeight(node.left);
        int rightBH = getBlackHeight(node.right);
        if (leftBH == -1 || rightBH == -1 || leftBH != rightBH) return -1;
        return leftBH + (node.color == 'B' ? 1 : 0);
    }
}

/*
 * Time Complexity: O(n)
 * - 建樹 O(n)
 * - 紅紅相鄰檢查 O(n)
 * - 黑高檢查 O(n)
 * => 總體 O(n)
 * Space Complexity: O(h) 遞迴棧深度
 */

