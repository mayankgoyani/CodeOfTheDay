import java.util.*;

class Main {

    // approch 1 using heap
    public static List<Integer> findClosestElements1(int[] arr, int k, int x) {
        // null case
        if (arr == null || arr.length == 0)
            return new ArrayList<>();

        // result list
        List<Integer> result = new ArrayList<>();
        // custom priority Queue
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            // get closer to a and b
            int ca = Math.abs(a - x);
            int cb = Math.abs(b - x);
            if (ca == cb) {
                return b - a;
            }
            return cb - ca;
        });

        for (int a : arr) {
            pq.add(a);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        while (!pq.isEmpty()) {
            result.add(0, pq.poll());
        }
        Collections.sort(result);
        // go over arr and add it inside the pq
        return result;
    }
    // approch 2 using binary search
    public static List<Integer> findClosestElements2(int[] arr, int k, int x) {
        // null case
        if (arr == null || arr.length == 0)
            return new ArrayList<>();
        // result list
        List<Integer> result = new ArrayList<>();
        int n = arr.length;
        int l = 0;
        int r = n - k;

        // find first min diff
        while (l < r) {
            // mid
            int mid = l + (r - l) / 2;
            int diffL = x - arr[mid];
            int diffR = arr[mid + k] - x;
            // if left side diff is greater
            // element smallest diff will be on the right side
            if (diffL > diffR) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        // we found smallest diff
        // then get next k - 1 smallest diff
        for (int i = l; i < l + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5};
        int k = 4;
        int x =3;
        System.out.println(findClosestElements1(arr, k, x));
        System.out.println(findClosestElements2(arr, k, x));

    }
}