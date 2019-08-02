package problem;

import java.util.LinkedList;
import java.util.Queue;

public class SameTree_100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // deep first seach
        // DFS

        Queue<TreeNode> queueP = new LinkedList();
        Queue<TreeNode> queueQ = new LinkedList();
        queueP.add(p);
        queueQ.add(q);
        while (!queueP.isEmpty()) {
            TreeNode nodep = queueP.poll();
            TreeNode nodeq = queueQ.poll();

            // nodep equals to nodeq
            if (nodep == nodeq)
                continue;

            if (nodep == null || nodeq == null) {
                return false;
            }

            if (nodep.val != nodeq.val) {
                return false;
            }

            // nodep and nodeq is not null
            queueP.add(nodep.left);
            queueP.add(nodep.right);
            queueQ.add(nodeq.left);
            queueQ.add(nodeq.right);
        }

        return true;
    }

    public static void main(String[] args) {
        TreeNode q = new TreeNode(3);
        TreeNode p = new TreeNode(4);
        System.out.println(new SameTree_100().isSameTree(p,q));
    }
}
