package baekjoon_13424;

import java.io.*;
import java.util.*;

public class Main {
    public static int[][] map;
    public static List<List<Node>> path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int loop = Integer.parseInt(br.readLine());

        while(loop-->0){
            st = new StringTokenizer(br.readLine());

            int roomNum = Integer.parseInt(st.nextToken());
            int pathNum = Integer.parseInt(st.nextToken());

            map = new int[roomNum+1][roomNum+1];
            path = new ArrayList<>();
            for(int i = 0; i<=roomNum; i++)
                path.add(new ArrayList<>());

            //간선의 정보를 받아서 리스트에 저장
            for(int i = 0; i<pathNum; i++){
                st = new StringTokenizer(br.readLine());

                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int val = Integer.parseInt(st.nextToken());

                path.get(A).add(new Node(B, val));
                path.get(B).add(new Node(A, val));
            }

            for(int i = 1; i<=roomNum; i++)
                dijkstra(i, roomNum);

            int peopleNum = Integer.parseInt(br.readLine());
            int[] peopleList = new int[peopleNum];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<peopleNum; i++)
                peopleList[i] = Integer.parseInt(st.nextToken());

            int minVal = Integer.MAX_VALUE;
            int roomNumber = -1;
            for(int i = 1; i<=roomNum; i++){
                int sum = 0;

                for(int j = 0; j<peopleNum; j++)
                    sum += map[peopleList[j]][i];

                if(minVal > sum){
                    roomNumber = i;
                    minVal = sum;
                }
            }

            sb.append(roomNumber).append("\n");
        }

        System.out.print(sb);
    }

    static void dijkstra(int startVertex, int roomNum){
        Queue<Node> q = new PriorityQueue<>();
        boolean[] visited = new boolean[roomNum+1];
        int[] rootFind = new int[roomNum+1];

        for(int i = 1; i<rootFind.length; i++)
            rootFind[i] = Integer.MAX_VALUE;

        visited[startVertex] = true;
        rootFind[startVertex] = 0;

        for(Node i : path.get(startVertex))
            q.add(i);

        while(!q.isEmpty()){
            Node v = q.poll();

            int nextV = v.to;
            int val = v.val;

            if(visited[nextV])
                continue;

            rootFind[nextV] = val;
            visited[nextV] = true;

            //시작 정점에서 해당 정점까지 가는 길이를 의미
            for(Node i : path.get(nextV))
                q.offer(new Node(i.to, val + i.val));
        }

        for(int i = 1; i <= roomNum; i++)
            map[startVertex][i] = rootFind[i];
    }
}
class Node implements Comparable<Node>{
    int to, val;

    Node(int to, int val){
        this.to = to;
        this.val = val;
    }

    @Override
    public int compareTo(Node a){
        return this.val - a.val;
    }
}
