package baekjoon_1766;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] parentNum;
    static int[] answer;
    static List<List<Integer>> l = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parentNum = new int[N+1];
        answer = new int[N+1];
        for(int i = 0; i<=N; i++)
            l.add(new ArrayList<>());

        while(M-->0){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            l.get(A).add(B);
            parentNum[B]++;
        }

        topologicalSort();

        for(int i = 1; i<=N; i++)
            System.out.print(answer[i]+" ");
        System.out.println();
    }

    static void topologicalSort(){
        Queue<Integer> q = new PriorityQueue<>();
        int idx = 1;

        for(int i = 1; i<=N; i++)
            if(parentNum[i] == 0)
                q.offer(i);

        while(!q.isEmpty()){
            int num = q.poll();

            answer[idx++] = num;

            for(int i = 0; i<l.get(num).size(); i++){
                int nextNum = l.get(num).get(i);

                parentNum[nextNum]--;

                if(parentNum[nextNum] == 0)
                    q.offer(nextNum);
            }
        }
    }
}
