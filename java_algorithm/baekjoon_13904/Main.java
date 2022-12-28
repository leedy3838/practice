package baekjoon_13904;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Queue<Node> q = new PriorityQueue<>();

        while(N-->0){
            st = new StringTokenizer(br.readLine());

            int day = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            q.offer(new Node(day, weight));
        }

        int day = 0;
        int sum = 0;
        Queue<Integer> input = new PriorityQueue<>();

        while(!q.isEmpty()){
            Node a = q.poll();

            if(day >= a.day){
                int minVal = input.peek();

                if(minVal < a.weight){
                    sum -= input.poll();
                    sum += a.weight;
                    input.offer(a.weight);
                }
                continue;
            }

            day++;
            sum += a.weight;
            input.offer(a.weight);
        }

        System.out.println(sum);
    }
}
class Node implements Comparable<Node>{
    int day, weight;

    Node(int day, int weight){
        this.day = day;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node a){
        if(this.day == a.day)
            return a.weight - this.weight;
        return this.day - a.day;
    }
}