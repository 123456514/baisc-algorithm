package hot100;

import java.util.HashMap;
import java.util.TreeSet;


public class Main {
    public static void main(String[] args) {
        String []strs = {"bba","abb"};
        System.out.println(lowestString(strs));
    }

    /**
     * 两数之和
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return new int[]{-1,-1};
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i < nums.length;i++){
            int n = target - nums[i];
            if(map.containsKey(n) && map.get(n) != i){
                return new int[]{i,map.get(n)};
            }else{
                map.put(nums[i],i);
            }
        }
        return new int[]{-1,-1};
    }

    /**
     * 两数相加
     * @param l1
     * @param l2
     * @return
     */
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//
//    }

    /**
     * 无重复字符的最长子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        return 0;
    }

    /**
     * 寻找两个正序数组的中位数.
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0.0;
    }
    // 贪心算法
    // 给定一个由字符串组成的数组strs 必须吧所有的字符串拼接起来 返回所有 可能的拼接结果中，字典序最小的结果
    // 使用局部最优解 得到 全局最优解
    // 用一种局部最功利的标准，总是做出在当前看来是最好的选择
    // strs中所有字符串全排列，返回所有肯呢个的结果
    public static  String lowestString(String []strs){
        if(strs == null || strs.length == 0){
            return "";
        }
        TreeSet<String> ans = process(strs);
        return ans.isEmpty() ? "" : ans.first();
    }
    public static TreeSet<String> process(String []strs){
        TreeSet<String> ans = new TreeSet<>();
        if(strs.length == 0){
            ans.add("");
            return ans;
        }
        for(int i = 0;i < strs.length;i++){
            String first = strs[i];
            String []nexts = removeIndexString(strs,i);
            TreeSet<String> next = process(nexts);
            for(String cur : next){
                ans.add(first + cur);
            }
        }
        return ans;
    }

    // 删除 Index位置的字符串
    private static String[] removeIndexString(String[] strs, int index) {
        int n = strs.length;
        String []ans = new String[n - 1];
        int ansIndex = 0;
        for(int i = 0;i < n;i++){
            if(i != index){
                ans[ansIndex++] = strs[i];
            }
        }
        return ans;
    }
}
