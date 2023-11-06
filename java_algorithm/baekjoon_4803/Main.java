package baekjoon_4803;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();

    private static List<List<Integer>> l;
    private static boolean[] isVisit;

    public static void main(String[] args) {

        solveProblem();
    }

    private static void solveProblem() {

        int iterCnt = 0;
        while (true) {
            int n = 0, m = 0;
            iterCnt++;

            try {
                StringTokenizer st = new StringTokenizer(br.readLine());
                n = Integer.parseInt(st.nextToken());
                m = Integer.parseInt(st.nextToken());
            } catch (IOException e){
                e.printStackTrace(System.out);
            }

            if (n == 0 && m == 0) {
                break;
            }

            firstSetting(n, m);
            int treeCnt = dfs(n);

            switch (treeCnt){
                case 0:
                    sb.append(String.format("Case %d: No trees.\n", iterCnt));
                    break;
                case 1:
                    sb.append(String.format("Case %d: There is one tree.\n", iterCnt));
                    break;
                default:
                    sb.append(String.format("Case %d: A forest of %d trees.\n", iterCnt, treeCnt));
            }
        }

        System.out.print(sb);
    }

    private static void firstSetting (int n, int m){

        l = new ArrayList<>();
        for(int i = 0; i <= n; i++)
            l.add(new ArrayList<>());

        isVisit = new boolean[n + 1];

        try {
            for(int i = 0; i < m; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());

                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                l.get(A).add(B);
                l.get(B).add(A);
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static int dfs(int n) {

        int cnt = 0;
        for(int i = 1; i <= n; i++){
            if(isVisit[i])
                continue;

            isVisit[i] = true;
            if(dfs(0, i))
                cnt++;
        }

        return cnt;
    }

    //해당 정점에서 트리가 생성이 가능하면 true return
    private static boolean dfs(int exV, int V) {

        boolean result = true;

        for(int nextV : l.get(V)){
            if(nextV == exV)
                continue;
            //그래프인 경우
            if(isVisit[nextV])
                return false;

            isVisit[nextV] = true;
            result = result && dfs(V, nextV);
        }

        return result;
    }
}
