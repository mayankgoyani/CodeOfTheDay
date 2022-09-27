// TC: O(n)
// SC: O(n)

class Main {
    // approch 1 using counting forces
    // seen on solution
    public static String pushDominoes(String dominoes) {
        // null case
        if (dominoes == null || dominoes.length() == 0)
            return "";

        // convert into the char array
        char[] ch = dominoes.toCharArray();

        int n = ch.length;

        // count left force and right forces
        int[] left = new int[n];
        int[] right = new int[n];

        // force decreases as how far domino is
        int force = 0;
        for (int i = 0; i < n; i++) {
            // from the left if domino falling on the right force
            // will be full
            if (ch[i] == 'R')
                force = n;
            // if any domino fall on left force
            // become 0 as per given in question
            else if (ch[i] == 'L')
                force = 0;
            // else force decays by 1
            else
                force = Math.max(force - 1, 0);
            left[i] = force;
        }
        force = 0;
        for (int i = n - 1; i >= 0; i--) {
            // from right side if domino falling on the left force
            // will be full
            if (ch[i] == 'L')
                force = n;
            // if any domino fall on left force
            // become 0 as per given in question
            else if (ch[i] == 'R')
                force = 0;
            // else force decays by 1
            else
                force = Math.max(force - 1, 0);
            right[i] = force;
        }
        // if there is more force from the left current domino fall on left
        // if there is more force from the right current domino fall on right
        for (int i = 0; i < n; i++) {
            if (ch[i] == '.') {
                if (left[i] > right[i]) {
                    ch[i] = 'R';
                } else if (left[i] < right[i]) {
                    ch[i] = 'L';
                }
            }
        }
        return new String(ch);
    }
    public static void main(String[] args) {
        String dominoes = ".L.R...LR..L..";
        System.out.println(pushDominoes(dominoes));

    }
}