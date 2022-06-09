package baekjoon_11725;

import java.util.*;
import java.io.*;

public class Main {
    static List<List<Integer>> l;
    static int[] answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        answer = new int[N+1];

        l = new ArrayList<>();
        for(int i = 0; i<=N; i++)
            l.add(new ArrayList<>());

        for(int i = 0; i<N - 1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            l.get(from).add(to);
            l.get(to).add(from);
        }

        bfs();

        for(int i = 2; i<=N; i++)
            System.out.println(answer[i]);

    }

    static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        while(!q.isEmpty()){
            int num = q.poll();

            for(int i = 0; i<l.get(num).size(); i++){
                int next = l.get(num).get(i);

                if(answer[next] == 0){
                    answer[next] = num;
                    q.offer(next);
                }
            }
        }
    }
}
