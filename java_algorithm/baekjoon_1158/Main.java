package baekjoon_1158;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        List<Integer> answer = new ArrayList<>();

        for(int i = 1; i<=N; i++)
            q.offer(i);

        while(!q.isEmpty()){
            for(int i = 0; i<K; i++){
                if(i == K-1)
                    answer.add(q.poll());
                else
                    q.offer(q.poll());
            }
        }

        System.out.print("<");
        for(int i = 0; i<N-1; i++)
            System.out.print(answer.remove(0)+", ");
        System.out.print(answer.remove(0)+">");
    }
}
