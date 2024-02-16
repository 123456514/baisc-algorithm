import sun.reflect.generics.tree.Tree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TreeNode root = createTree();
//        System.out.println(func1(root));
        System.out.println(zigzagLevelOrder(root));
    }

    // 创建一棵二叉树
    public static TreeNode createTree() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        return node1;
    }

    // 二叉树层序遍历
    public static List<List<Integer>> func1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size > 0) {
                TreeNode top = queue.poll();
                list.add(top.val);
                if (top.left != null) {
                    queue.offer(top.left);
                }
                if (top.right != null) {
                    queue.offer(top.right);
                }
                size--;
            }
            res.add(list);
        }
        return res;
    }

    /**
     * 二叉树的 锯齿状层序遍历  就是添加一个标记 然后这里用的是linkedList 中的 addLast addFirst
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
             for(int i = 0;i < size;i++){
                 TreeNode top = queue.poll();
                 if(flag){
                     list.addLast(top.val);
                 }else{
                     list.addFirst(top.val);
                 }
                 if(top.left != null){
                     queue.offer(top.left);
                 }
                 if(top.right != null){
                     queue.offer(top.right);
                 }
             }
             flag = !flag;
             res.add(list);
        }
        return res;
    }
}