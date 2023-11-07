package baekjoon_1956;

import java.io.*;
import java.util.*;

public class Main {

    private static int[][] dist;

    public static void main(String[] args) {

        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int V, E;
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());

            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            dist = new int[V + 1][V + 1];

            for(int i = 1; i <= V; i++)
                Arrays.fill(dist[i], Integer.MAX_VALUE);

            for(int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());

                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int val = Integer.parseInt(st.nextToken());

                dist[A][B] = val;
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem(){

        int minDist = findMinDist();

        if(minDist == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(minDist);
    }

    private static int findMinDist(){

        for(int mid = 1; mid < dist.length; mid++){
            for(int from = 1; from < dist.length; from++){
                for(int to = 1; to < dist.length; to++){
                    if(dist[from][mid] == Integer.MAX_VALUE || dist[mid][to] == Integer.MAX_VALUE)
                        continue;

                    if(dist[from][to] > dist[from][mid] + dist[mid][to]) {
                        dist[from][to] = dist[from][mid] + dist[mid][to];
                    }
                }
            }
        }

        int minVal = Integer.MAX_VALUE;
        for(int from = 1; from < dist.length; from++){
            for(int to = 1; to < dist.length; to++){
                if(dist[from][to] != Integer.MAX_VALUE && dist[to][from] != Integer.MAX_VALUE){
                    minVal = Math.min(minVal, dist[to][from] + dist[from][to]);
                }
            }
        }

        return minVal;
    }
}