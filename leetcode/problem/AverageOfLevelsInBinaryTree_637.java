package problem;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 *
 *
 * Given a non-empty binary tree, return the average value of the nodes on each level
 * in the form of an array.
 * Example 1:
 * Input:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Output: [3, 14.5, 11]
 * Explanation:
 * The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11.
 * Hence return [3, 14.5, 11].
 * Note:
 * The range of node's value is in the range of 32-bit signed integer.
 * }
 */
public class AverageOfLevelsInBinaryTree_637 {


    // 经典的BFS
    // Broad First Search
    class Solution {
        public List<Double> averageOfLevels(TreeNode root) {
            // we don`t know how deep the tree is.
            List<Double> result = new ArrayList<>();
            // we need a FIFO queue to store the node
            Queue<TreeNode> queue = new LinkedList<>();
            if (root == null) {
                return result;
            }
            queue.add(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                double sum = 0.0;
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.remove();
                    sum += node.val;
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
                result.add(sum / size);
            }
            return result;
        }

    }

     public static void main(String[] args) {
        System.out.println(1<<31);
        TreeNode tree = new TreeNode(2147483647);
        TreeNode node1 = new TreeNode(2147483647);
        TreeNode node2 = new TreeNode(2147483647);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(8);

        tree.left = node1;
        tree.right = node2;

//        node2.left = node3;
//        node2.right = node4;

        AverageOfLevelsInBinaryTree_637 test = new AverageOfLevelsInBinaryTree_637();
        Solution solution = test.new Solution();
         System.out.println(Arrays.toString(solution.averageOfLevels(tree).toArray()));

    }
}
