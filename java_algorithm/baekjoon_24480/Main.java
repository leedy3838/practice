package baekjoon_24480;

import java.io.*;
import java.util.*;

public class Main {

    private static final List<List<Integer>> l = new ArrayList<>();
    private static boolean[] isVisit;
    private static int[] visitOrder;
    private static int visitCnt = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        isVisit = new boolean[N + 1];
        visitOrder = new int[N + 1];
        for(int i = 0; i <= N; i++)
            l.add(new ArrayList<>());

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            l.get(A).add(B);
            l.get(B).add(A);
        }

        for(int i = 1; i <= N; i++) {
            l.get(i).sort(Collections.reverseOrder());
        }

        isVisit[R] = true;
        visitOrder[R] = visitCnt++;
        dfs(R);

        for(int i = 1; i <= N; i++){
            System.out.println(visitOrder[i]);
        }
    }

    private static void dfs(int V){

        for(int nextV : l.get(V)){

            if(isVisit[nextV])
                continue;

            isVisit[nextV] = true;
            visitOrder[nextV] = visitCnt++;
            dfs(nextV);
        }
    }
}
