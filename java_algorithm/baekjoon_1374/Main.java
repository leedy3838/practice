package baekjoon_1374;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Queue<Node> q = new PriorityQueue<>();
        Queue<Integer> answer = new PriorityQueue<>();

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            q.offer(new Node(num, start, end));
        }

        while (!q.isEmpty()){
            Node node = q.poll();

            if(answer.isEmpty()) {
                answer.offer(node.end);
                continue;
            }

            if(answer.peek() <= node.start){
                answer.poll();
                answer.offer(node.end);
            }
            else
                answer.offer(node.end);
        }

        System.out.println(answer.size());
    }
}
class Node implements Comparable<Node>{
    int num, start, end;
    Node(int num, int start, int end){
        this.num = num;
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Node a){
        if(this.start == a.start)
            return this.end - a.end;
        return this.start - a.start;
    }
}