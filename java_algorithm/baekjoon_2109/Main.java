package baekjoon_2109;

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

            int val = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());

            q.offer(new Node(val, day));
        }

        int day = 0;
        int sum = 0;

        Queue<Integer> schedule = new PriorityQueue<>();
        while(!q.isEmpty()){
            Node a = q.poll();

            if(a.day == day){
                if(schedule.peek() < a.value){
                    sum -= schedule.poll();
                    sum += a.value;
                    schedule.offer(a.value);
                }
                continue;
            }

            sum += a.value;
            day++;
            schedule.offer(a.value);
        }

        System.out.println(sum);
    }
}
class Node implements Comparable<Node>{
    int value;
    int day;

    Node(int value, int day){
        this.value = value;
        this.day = day;
    }

    @Override
    public int compareTo(Node a){
        if(this.day == a.day)
            return a.value - this.value;

        return this.day - a.day;
    }
}