package baekjoon_1759;

import java.util.*;
import java.io.*;

public class Main {
    static char[] charArr;
    static int L, C;
    static char[] answer;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        charArr = new char[C];
        answer = new char[L];
        visited = new boolean[C];

        String[] input = br.readLine().split(" ");
        for(int i = 0; i<C; i++)
            charArr[i] = input[i].charAt(0);

        Arrays.sort(charArr);
        dfs(0, 0);
    }
    static void dfs(int idx, int cnt){
        if(cnt == L){
            boolean check = check();
            if(!check)
                return;

            for(int i = 0; i<L; i++)
                System.out.print(answer[i]);
            System.out.println();

            return;
        }

        for(int i = idx; i<C; i++){
            if(!visited[i]){
                visited[i] = true;
                answer[cnt] = charArr[i];
                dfs(i, cnt+1);
                visited[i] = false;
            }
        }
    }

    static boolean check(){
        int aeiou = 0;
        for(int i = 0; i<L; i++){
            switch(answer[i]){
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    aeiou++;
            }
        }

        if(aeiou>0 && L-aeiou>1)
            return true;

        return false;
    }
}
