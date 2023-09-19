package baekjoon_15649;

import java.io.*;
import java.util.*;

public class Main {

    static int M, N;
    static boolean[] isVisit;
    static int[] numList;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {

        firstSetting();
        dfs();
    }

    static void firstSetting(){

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            isVisit = new boolean[N+1];
            numList = new int[M+1];

        } catch (IOException e){
            e.printStackTrace(System.out);
        }
    }

    static void dfs(){
        dfs(1);
        System.out.println(sb);
    }

    static void dfs(int depth){
        if(depth > M){
            for(int i = 1; i <= M; i++){
                sb.append(numList[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 1; i <= N; i++){
            if(isVisit[i])
                continue;

            numList[depth] = i;
            isVisit[i] = true;

            dfs(depth + 1);

            isVisit[i] = false;
        }
    }
}
