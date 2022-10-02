// approch 1 
// TC: O(k^n)
// SC: O(n)

// approch 1 
// TC: O(nkt)
// SC: O(nt)
class Main {


    // approch 1 using recursion
    private static int result;
    private static int mod;

    public static int numRollsToTarget1(int n, int k, int target) {
        // null case
        if (target == 0)
            return 0;
        mod = 1000000007;
        result = 0;
        // helper function
        helper(n, k, target, 0, 0);
        return result;
    }

    // helper function
    private static void helper(int n, int k, int target, int sum, int selected) {
        // base case
        if (selected == n) {
            if (sum == target) {
                result++;
            }
            return;
        }
        // main logic
        // for loop based recursion
        for (int i = 1; i <= k; i++) {
            helper(n, k, target, sum + i, selected + 1);
        }
    }
    // approch 2 using dp matrix
    public static  int numRollsToTarget2(int n, int k, int target) {
        // null case
        if (target == 0)
            return 0;
        int mod = 1000000007;

        // dp matrix
        int[][] dp = new int[n + 1][target + 1];

        // handle base case
        // if we have one dice we can make 1 possible ways to reach
        // ith target
        for (int i = 1; i <= target && i <= k; i++) {
            dp[1][i] = 1;
        }
        // i -> number of dices
        // j -> target till now
        // l -> 1 -> k faces
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= target; j++) {
                // current sum at ith dice and
                // target
                int sum = 0;
                for (int l = 1; l <= k; l++) {
                    // we got a target from the selecting one face
                    // and getting remaining from the i-1 th dice
                    if (j - l >= 0) {
                        sum = (sum + dp[i - 1][j - l]) % mod;
                    }
                }
                dp[i][j] = sum % mod;
            }
        }
        return dp[n][target];
    }
    public static void main(String[] args) {
        int n = 5;
        int k = 6;
        int target = 20;
        System.out.println(numRollsToTarget1(n, k, target));
        System.out.println(numRollsToTarget2(n, k, target));

    }
}