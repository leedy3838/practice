package baekjoon_24445;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[] countOrder = new int[N + 1];
        boolean[] isVisit = new boolean[N + 1];
        List<List<Integer>> l = new ArrayList<>();
        for(int i = 0; i <= N; i++)
            l.add(new ArrayList<>());

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            l.get(A).add(B);
            l.get(B).add(A);
        }

        for(int i = 1; i <= N; i++)
            l.get(i).sort(Collections.reverseOrder());

        int count = 1;

        Queue<Integer> q = new LinkedList<>();

        q.offer(R);
        isVisit[R] = true;
        countOrder[R] = count++;

        while(!q.isEmpty()){
            int V = q.poll();

            for(int nextV : l.get(V)){

                if(isVisit[nextV])
                    continue;

                q.offer(nextV);
                isVisit[nextV] = true;
                countOrder[nextV] = count++;
            }
        }

        for(int i = 1; i <= N; i++){
            System.out.println(countOrder[i]);
        }
    }
}
