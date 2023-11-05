package baekjoon_13913;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, K;
    private static final int[] map = new int[100_001];

    public static void main(String[] args) {

        firstSetting();
        solveProblem();
    }

    private static void firstSetting(){

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
        } catch(IOException e){
            e.printStackTrace(System.out);
        }

        Arrays.fill(map, -1);
        map[N] = 1;
        map[K] = 1;
    }

    private static void solveProblem(){

        bfs();
        printAns();
    }

    private static void bfs(){

        Queue<Integer> q = new LinkedList<>();
        q.offer(N);

        while(!q.isEmpty()){
            int vertex = q.poll();

            for(int i = 0; i < 3; i++){
                int nextV;

                if (i == 0)  nextV = vertex * 2;
                else if (i == 1) nextV = vertex + 1;
                else nextV = vertex - 1;

                if(nextV == K){
                    map[K] = vertex;
                    return;
                }

                if(nextV < 0 || nextV > 100_000)
                    continue;
                if(map[nextV] != -1)
                    continue;

                map[nextV] = vertex;
                q.offer(nextV);
            }
        }
    }

    private static void printAns(){

        int cnt = 0;

        int v = K;
        List<Integer> ans = new ArrayList<>();
        while(v != N){
            ans.add(v);
            v = map[v];
            cnt++;
        }
        ans.add(N);

        StringBuilder sb = new StringBuilder();

        sb.append(cnt).append("\n");
        for(int i = ans.size() - 1; i >= 0; i--)
            sb.append(ans.get(i)).append(" ");

        System.out.println(sb);
    }
}
