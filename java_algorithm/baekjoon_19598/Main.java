package baekjoon_19598;

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

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            q.offer(new Node(start, end));
        }

        while(!q.isEmpty()){
            Node a = q.poll();

            if(answer.isEmpty())
                answer.offer(a.end);
            else{
                if(answer.peek() <= a.start){
                    answer.poll();
                    answer.offer(a.end);
                }
                else{
                    answer.offer(a.end);
                }
            }
        }

        System.out.println(answer.size());
    }
}
class Node implements Comparable<Node>{
    int start, end;

    public Node(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Node node) {
        if(this.start == node.start)
            return this.end - node.end;
        return this.start - node.start;
    }
}
