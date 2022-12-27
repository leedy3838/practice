package baekjoon_13164;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if(N == K){
            System.out.println(0);
            return;
        }

        Queue<Integer> q = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        while(st.hasMoreTokens()){
            int B = Integer.parseInt(st.nextToken());

            q.offer(B-A);
            A = B;
        }

        int getNum = N-K;
        int sum = 0;
        while(getNum-->0)
            sum += q.poll();

        System.out.println(sum);
    }
}