package baekjoon_1987;

import java.io.*;
import java.util.*;

public class Main {
    static int R, C, max = 1;
    static String[][] map;
    static boolean[][] visited;
    static Set<String> s = new HashSet<>();
    static int[] dC = {1, -1, 0, 0};
    static int[] dR = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new String[R][C];
        visited = new boolean[R][C];

        for(int i = 0; i<R; i++){
            map[i] = br.readLine().split("");
        }

        s.add(map[0][0]);
        visited[0][0] = true;

        dfs(0, 0);

        System.out.println(max);
    }

    static void dfs(int row, int col){
        for(int i = 0; i<4; i++){
            int dr = row + dR[i];
            int dc = col + dC[i];

            if(dr<0||dc<0||dr>=R||dc>=C)
                continue;
            if(visited[dr][dc])
                continue;
            if(s.contains(map[dr][dc]))
                continue;

            s.add(map[dr][dc]);
            visited[dr][dc] = true;
            max = Math.max(max, s.size());

            dfs(dr, dc);

            s.remove(map[dr][dc]);
            visited[dr][dc] = false;
        }
    }
}
