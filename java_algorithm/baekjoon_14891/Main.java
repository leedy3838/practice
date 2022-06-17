package baekjoon_14891;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<List<Integer>> LL = new ArrayList<>();
        for(int i = 0; i<4; i++){
            String[] input = br.readLine().split("");
            LL.add(new LinkedList<>());

            for(int j = 0; j<8; j++)
                LL.get(i).add(Integer.parseInt(input[j]));
        }

        int K = Integer.parseInt(br.readLine());

        while(K-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken()) -1;
            int dir = Integer.parseInt(st.nextToken());

            Queue<Node> q = new LinkedList<>();
            q.offer(new Node(num, dir));
            boolean[] visited = new boolean[4];
            visited[num] = true;

            while(!q.isEmpty()){
                Node a = q.poll();

                int right = a.num + 1;
                int left = a.num - 1;
                int nextDir = a.dir == -1 ? 1 : -1;

                if(right<4 && !visited[right]){
                    if(LL.get(a.num).get(2) != LL.get(right).get(6)) {
                        q.offer(new Node(right, nextDir));
                        visited[right] = true;
                    }
                }

                if(left>=0 && !visited[left]){
                    if(LL.get(a.num).get(6) != LL.get(left).get(2)){
                        q.offer(new Node(left, nextDir));
                        visited[left] = true;
                    }
                }

                // 시계 방향 회전이니 마지막 수를 빼와서 처음에 삽입
                if(a.dir == 1)
                    LL.get(a.num).add(0, LL.get(a.num).remove(7));
                // 반시계 방향 회전이니 처음 수를 빼서 마지막에 삽입
                else
                    LL.get(a.num).add(LL.get(a.num).remove(0));
            }
        }
        int sum = 0;
        for(int i = 0; i<4; i++){
            if(LL.get(i).get(0) == 1)
                sum += Math.pow(2, i);
        }

        System.out.println(sum);
    }
}
class Node{
    int num, dir;

    Node(int num, int dir){
        this.num = num;
        this.dir = dir;
    }
}