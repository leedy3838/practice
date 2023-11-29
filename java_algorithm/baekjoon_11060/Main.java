package baekjoon_11060;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int[] jumpNum, dist;
    public static void main(String[] args) throws IOException {

        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        try {
            N = Integer.parseInt(br.readLine());
            jumpNum = new int[N + 1];
            dist = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++)
                jumpNum[i] = Integer.parseInt(st.nextToken());
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        if(N == 1) {
            System.out.println(0);
            return;
        }

        Queue<Integer> q = new LinkedList<>();
        dist[1] = 0;
        q.offer(1);

        while(!q.isEmpty()) {
            int V = q.poll();

            for(int i = 1; i <= jumpNum[V]; i++) {
                int nextV = V + i;

                if(nextV > N)
                    break;
                if(dist[nextV] != 0)
                    continue;

                dist[nextV] = dist[V] + 1;
                q.offer(nextV);
            }
        }

        if(dist[N] == 0)
            System.out.println(-1);
        else
            System.out.println(dist[N]);
    }
}
