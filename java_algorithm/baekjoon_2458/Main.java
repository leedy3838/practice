package baekjoon_2458;

import java.io.*;
import java.util.*;

public class Main {
    //자신보다 키가 큰 사람들의 번호를 저장
    static List<List<Integer>> tallList = new ArrayList<>();
    //자신보다 키가 작은 사람들의 번호를 저장
    static List<List<Integer>> shortList = new ArrayList<>();
    static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i = 0; i<=N; i++){
            shortList.add(new ArrayList<>());
            tallList.add(new ArrayList<>());
        }

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int shortP = Integer.parseInt(st.nextToken());
            int tallP = Integer.parseInt(st.nextToken());

            tallList.get(shortP).add(tallP);
            shortList.get(tallP).add(shortP);
        }

        int count = 0;
        for(int i = 1; i<=N; i++){
            //자신보다 확실히 크거나 작은 사람의 수
            int sum = 0;

            sum += shortBfs(i);
            sum += tallBfs(i);

            if(sum == N-1)
                count++;
        }

        System.out.println(count);
    }

    static int shortBfs(int v){
        int count = 0;
        boolean[] visited = new boolean[N+1];

        Queue<Integer> q = new LinkedList<>();
        q.offer(v);

        while(!q.isEmpty()){
            int person = q.poll();


            for(int nextP : shortList.get(person)){
                //같은 사람을 여러 번 방문하지 않게 함
                if(visited[nextP])
                    continue;

                q.offer(nextP);
                count++;
                visited[nextP] = true;
            }
        }

        return count;
    }

    static int tallBfs(int v){
        int count = 0;
        boolean[] visited = new boolean[N+1];

        Queue<Integer> q = new LinkedList<>();
        q.offer(v);

        while(!q.isEmpty()){
            int person = q.poll();


            for(int nextP : tallList.get(person)){
                //같은 사람을 여러 번 방문하지 않게 함
                if(visited[nextP])
                    continue;

                q.offer(nextP);
                count++;
                visited[nextP] = true;
            }
        }

        return count;
    }
}