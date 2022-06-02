package baekjoon_14889;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main{
    static int[][] stat;
    static int minNum = Integer.MAX_VALUE;
    static boolean[] visited;
    static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        stat = new int[N][N];
        visited = new boolean[N];

        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j<N; j++)
                stat[i][j] = Integer.parseInt(st.nextToken());
        }

        find(0, 0);

        System.out.println(minNum);
    } 

    static void find(int depth, int count){
        if(count == N/2){
            int aTeamSum = 0;
            int bTeamSum = 0;

            for(int i = 0; i<N-1; i++){
                for(int j = i+1; j<N; j++){
                    if(visited[i] && visited[j]) {
                        aTeamSum += stat[i][j];
                        aTeamSum += stat[j][i];
                    }
                    if(!visited[i] && !visited[j]) {
                        bTeamSum += stat[i][j];
                        bTeamSum += stat[j][i];
                    }
                }
            }

            int val = Math.abs(aTeamSum - bTeamSum);

            if(val == 0) {
                System.out.println(val);
                System.exit(0);
            }
            minNum = Math.min(minNum, val);
            return;
        }

        for(int i = depth; i<N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                find(i + 1, count + 1);
                visited[i] = false;
            }
        }
    }
}
