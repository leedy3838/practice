package baekjoon_1715;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Queue<Integer> q = new PriorityQueue<>();
        for(int i = 0; i<N; i++)
            q.offer(Integer.parseInt(br.readLine()));

        int sum = 0;

        if(N == 1){
            System.out.println(0);
            return;
        }

        while(true){
            int a = q.poll();
            int b = q.poll();

            sum += a+b;

            if(q.isEmpty())
                break;

            q.offer(a+b);
        }

        System.out.println(sum);
    }
}
