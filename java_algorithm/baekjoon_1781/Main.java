package baekjoon_1781;

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
            int cupRamen = Integer.parseInt(st.nextToken());

            q.offer(new Node(day, cupRamen));
        }

        Queue<Integer> cupRamenGet = new PriorityQueue<>();
        int dayCnt = 0;
        int sum = 0;

        while(!q.isEmpty()){
            Node a = q.poll();

            if(a.day == dayCnt){
                int minVal = cupRamenGet.peek();

                if(a.cupRamen > minVal){
                    sum -= cupRamenGet.poll();
                    sum += a.cupRamen;
                    cupRamenGet.offer(a.cupRamen);
                }
            }
            else{
                sum += a.cupRamen;
                dayCnt++;
                cupRamenGet.offer(a.cupRamen);
            }
        }

        System.out.println(sum);
    }
}
class Node implements Comparable<Node> {
    int day, cupRamen;

    Node(int day, int cupRamen){
        this.day = day;
        this.cupRamen = cupRamen;
    }

    @Override
    public int compareTo(Node node) {
        if(this.day == node.day)
            return node.cupRamen - this.cupRamen;
        return this.day - node.day;
    }
}