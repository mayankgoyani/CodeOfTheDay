import java.util.*;

class Main {

    // approch 1 using adjacency list and dfs
    public static boolean equationsPossible1(String[] equations) {
        // null case
        if (equations == null || equations.length == 0)
            return true;

        // adjacency list
        List<Integer>[] graph = new ArrayList[26];

        // intialize the adjacency list
        for (int i = 0; i < 26; i++) {
            graph[i] = new ArrayList<>();
        }

        // make adjacency list
        for (String edge : equations) {
            // if eqaul then
            if (edge.charAt(1) == '=') {
                char x = edge.charAt(0);
                char y = edge.charAt(3);
                // two direction edge
                // x -> y
                graph[x - 'a'].add(y - 'a');
                // y -> x
                graph[y - 'a'].add(x - 'a');
            }

        }
        int[] groups = new int[26];
        Arrays.fill(groups, -1);
        // dfs for traversing in all adjacency list
        for (int i = 0; i < 26; i++) {
            helper(i, i, groups, graph);
        }
        // check in the equation if not equal is part of
        // same group then return false;
        for (String edge : equations) {
            if (edge.charAt(1) == '!') {
                char x = edge.charAt(0);
                char y = edge.charAt(3);
                if (groups[x - 'a'] == groups[y - 'a'])
                    return false;
            }
        }
        return true;

    }

    private static void helper(int ind, int color, int[] groups, List<Integer>[] graph) {
        // base case

        // no need of base case

        // main logic
        // if not colored means not part
        // of any group
        if (groups[ind] == -1) {
            groups[ind] = color;
            for (int ne : graph[ind]) {
                helper(ne, color, groups, graph);
            }
        }

    }


    static class UnionFind {
        private int[] parent = new int[26];

        UnionFind() {
            for (int i = 0; i < 26; i++) {
                parent[i] = i;
            }
        }

        private int find(int x) {
            if (x == parent[x]) {
                return x;
            }
            parent[x] = find(parent[x]);
            return parent[x];
        }

        private void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px != py) {
                parent[px] = py;
            }
        }
    }

    public static boolean equationsPossible2(String[] equations) {
        // null case
        if (equations == null || equations.length == 0)
            return true;

        UnionFind uf = new UnionFind();

        // go over equtions
        // and if it is equal then
        // make union
        for (String edge : equations) {
            if (edge.charAt(1) == '=') {
                int x = edge.charAt(0) - 'a';
                int y = edge.charAt(3) - 'a';
                uf.union(x, y);
            }
        }

        // go over all edge and if it is not equal
        // and of same group return false;
        for (String edge : equations) {
            if (edge.charAt(1) == '!') {
                int x = edge.charAt(0) - 'a';
                int y = edge.charAt(3) - 'a';
                if (uf.find(x) == uf.find(y))
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] equations = new String[] { "a==b", "c==a", "c==d", "e==f", "b==g", "a!=g" };
        System.out.println(equationsPossible1(equations));
        System.out.println(equationsPossible2(equations));

    }
}