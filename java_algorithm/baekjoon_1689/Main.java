package baekjoon_1689;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Queue<Node> q = new PriorityQueue<>();

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            q.offer(new Node(start, 1));
            q.offer(new Node(end, 0));
        }

        int answer = 0;
        int move = 0;
        int cnt = 0;

        while(!q.isEmpty()){
            Node node = q.peek();

            if(node.vertex > move){
                move++;
            }
            else{
                if(node.type == 0)
                    cnt--;
                else
                    cnt++;

                q.poll();
                answer = Math.max(answer, cnt);
            }
        }

        System.out.println(answer);
    }
}
class Node implements Comparable<Node>{
    //type이 1이면 시작, 0이면 끝
    int vertex, type;

    public Node(int start, int type) {
        this.vertex = start;
        this.type = type;
    }

    @Override
    public int compareTo(Node node) {
        if(this.vertex == node.vertex)
            return this.type - node.type;
        return this.vertex - node.vertex;
    }
}