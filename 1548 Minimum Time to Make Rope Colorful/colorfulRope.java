class Main {
    // approch 1 greedy
    public static int minCost(String colors, int[] neededTime) {
        // null case
        if (colors == null || colors.length() == 0)
            return 0;

        int n = colors.length();
        int result = 0;
        // get the window where consecutive balloons are
        // of same color
        int l = 0;
        int r = 1;
        while (r < n) {
            // move right pointer for consecutive balloons
            while (r < n && colors.charAt(l) == colors.charAt(r)) {
                r++;
            }
            // check if window size is greater then 1 then
            if (l != r - 1) {
                // sum all neededTime of that window
                int sum = 0;
                for (int i = l; i < r; i++) {
                    sum += neededTime[i];
                }
                // get maximum window Out of it
                int max = -1;
                for (int i = l; i < r; i++) {
                    max = Math.max(neededTime[i], max);
                }
                // remove all the balloons except maximum needed time
                // balloon
                result += (sum - max);
                // update the left pointer
                l = r - 1;
            }
            // move left and right pointer
            l++;
            r++;
        }
        // return result
        return result;
    }

    public static void main(String[] args) {
        String s = "abaaac";
        int[] neededTime = new int[] { 1, 2, 3, 4, 6, 5 };
        System.out.println(minCost(s, neededTime));
    }
}