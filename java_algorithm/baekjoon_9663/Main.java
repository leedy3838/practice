package baekjoon_9663;

import java.io.*;

public class Main{
    static int cnt = 0;
    static int N;
    static int[] queen;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        queen = new int[N];

        dfs(0);

        System.out.println(cnt);
    }

    static void dfs(int depth){
        if(depth == N){
            cnt++;
            return;
        } 

        for(int i = 0; i<N; i++){
            queen[depth] = i;

            if(canPut(depth))
                dfs(depth+1);
        }
    }

    static boolean canPut(int depth){
        for(int i = 0; i<depth; i++){
            if(queen[depth] == queen[i])
                return false;

            else if(Math.abs(depth-i) == Math.abs(queen[depth] - queen[i]))
                return false;
        }
        return true;
    }
}
