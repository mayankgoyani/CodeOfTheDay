// approch 1
// TC: O(mn^2)
// SC: O(1)

// approch 2
// TC: O(mn^2)
// SC: O(m)

// approch 3
// TC: O(mn)
// SC: O(mn)

import java.util.*;

class Main {


    // approch 1 brute force try out all
    // the possibilities
    public int findLength1(int[] nums1, int[] nums2) {
        // null case
        if (nums1 == null || nums2 == null || nums1.length == 0
                || nums2.length == 0)
            return 0;

        int n1 = nums1.length;
        int n2 = nums2.length;
        int result = 0;

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                // if there is a match
                // increase the window
                int k = i;
                int l = j;
                while (k < n1 && l < n2 && nums2[l] == nums1[k]) {
                    // System.out.println(nums2[j] + " " +nums1[k]);
                    k++;
                    l++;
                }
                // System.out.println(k+"----"+ i);
                result = Math.max(result, k - i);
            }
        }
        return result;
    }

    // approch 2 using hashtable
    public static int findLength2(int[] nums1, int[] nums2) {
        // null case
        if (nums1 == null || nums2 == null || nums1.length == 0
                || nums2.length == 0)
            return 0;
        // map for all indexex of occurences

        Map<Integer, List<Integer>> map = new HashMap<>();
        int n1 = nums1.length;
        int n2 = nums2.length;
        int result = 0;
        for (int i = 0; i < n2; i++) {
            if (!map.containsKey(nums2[i])) {
                map.put(nums2[i], new ArrayList<>());
            }
            map.get(nums2[i]).add(i);
        }

        for (int i = 0; i < n1; i++) {
            if (map.containsKey(nums1[i])) {
                for (int j : map.get(nums1[i])) {
                    int k = i;
                    while (j < n2 && k < n1 && nums2[j] == nums1[k]) {
                        j++;
                        k++;
                    }
                    result = Math.max(result, k - i);
                }
            }
        }
        // System.out.println(map);
        return result;

    }
    // approch 3 using dp
    public static int findLength3(int[] nums1, int[] nums2) {
        // null case
        if (nums1 == null || nums2 == null || nums1.length == 0
                || nums2.length == 0)
            return 0;

        int n1 = nums1.length;
        int n2 = nums2.length;
        int result = 0;
        // dp matrix
        int[][] dp = new int[n2 + 1][n1 + 1];

        // when we find matching element
        // we get previous matching length
        // from diagonally
        for (int i = 1; i <= n2; i++) {
            for (int j = 1; j <= n1; j++) {
                if (nums1[j - 1] == nums2[i - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,2,1,5,4,7,3,2,1,4};
        int[] nums2 = new int[] {1,3,2,1,4,7};
        System.out.println(findLength3(nums1, nums2));
    }
}