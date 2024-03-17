package Tree;
/**
 * 二叉树系列
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TreeNode root = createTree();
//        System.out.println(func1(root));
//        System.out.println(zigzagLevelOrder(root));
//        System.out.println(isCBT2(root));
        System.out.println(lowestAccent2(root, new TreeNode(2), new TreeNode(3)).val);
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

    /**
     * N 叉树的层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0;i < size;i++){
                Node top = queue.poll();
                list.add(top.val);
                if(top.children != null){
                    for(Node node : top.children){
                        queue.offer(node);
                    }
                }
            }
            res.add(list);
        }
        return res;
    }
    //  判断一棵树是否是完全二叉树
    // 1. 左满 右满 左高 = 右高
    // 2. 左完 右满 左高 = 右高 + 1
    // 3. 左满 右满 左高 = 右高 + 1
    // 4. 左满 右完全 左高 = 右高
    // 满 和 高度  完全   信息体
    public static boolean isCBT2(TreeNode head){
        return process(head).isCBT;
    }
    public static class Info{
        public boolean isFull;
        public boolean isCBT;
        public int height;
        public Info(boolean full,boolean cbt,int h){
            isFull = full;
            isCBT = cbt;
            height = h;
        }
    }
    public static Info process(TreeNode x){
        if(x == null){
            return new Info(true,true,0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isCBT = false;
        // 1
        if(leftInfo.isFull && rightInfo.isFull && rightInfo.height == leftInfo.height){
            isCBT = true;
        }
        // 2
        if(leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1){
            isCBT = true;
        }
        // 3
        if(leftInfo.isFull && rightInfo.isFull && rightInfo.height + 1 == leftInfo.height){
            isCBT = true;
        }
        if(leftInfo.isFull && rightInfo.isCBT && rightInfo.height  == leftInfo.height){
            isCBT = true;
        }
        // 4
        return new Info(isFull,isCBT,height);
    }
    // 给顶一颗二叉树的头节点 head 和另外两个节点 a 和b
    // 返回 a 和 b 的最低公共祖先
    // 是否发现  a
    // 是否发现  b
    // 汇聚点 和 x 有关
        // 左 a 右 b  各有一个   1
        // x 本身就是 a b 中的一个节点 2 3
    // 汇聚点 和 x 无关
        // 左有答案
        // 右有答案
        // a b 不全
    public static TreeNode lowestAccent2(TreeNode head,TreeNode a,TreeNode b){
        return process(head, a, b).ans;
    }
    public static class Info2{
        public boolean findA;
        public boolean findB;
        public TreeNode ans;
        public Info2(boolean fA,boolean fB,TreeNode an){
            findA = fA;
            findB = fB;
            ans = an;
        }
    }
    public static Info2 process(TreeNode x,TreeNode a,TreeNode b){
        if(x == null){
            return new Info2(false,false,null);
        }
        Info2 leftInfo = process(x.left,a,b);
        Info2 rightInfo = process(x.right,a,b);
        boolean findA = (x == a) || leftInfo.findA || rightInfo.findA;
        boolean findB = (x == b) || leftInfo.findB || rightInfo.findB;
        TreeNode ans = null;
        // 左树找到答案
        if(leftInfo.ans != null){
            ans = leftInfo.ans;
        }else if(rightInfo.ans != null){
            ans = rightInfo.ans;
        }else{
            // 考虑最汇聚
            if(findA && findB){
                ans = x;
            }
        }
        return new Info2(findA,findB,ans);
    }





}