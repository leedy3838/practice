package baekjoon_13335;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Queue<Node> truck = new LinkedList<>();
        int[] location = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            int weight = Integer.parseInt(st.nextToken());
            truck.offer(new Node(i, weight));
        }

        Queue<Node> bridge = new LinkedList<>();
        int count = 0;
        int sum = 0;

        while(!truck.isEmpty()){
            Node a = truck.peek();
            int idx = a.index;
            int weight = a.weight;

            if(!bridge.isEmpty()){
                int start = bridge.peek().index;

                for(int i = start; i<idx; i++)
                    location[i]--;

                if(location[start] == 0) {
                    sum -= bridge.poll().weight;
                }
            }

            if(sum + weight<=L){
                bridge.offer(truck.poll());

                sum += weight;
                location[idx] = W;
            }

            count++;
        }

        System.out.println(count+W);
    }
}
class Node{
    int index, weight;

    Node(int index, int weight){
        this.index = index;
        this.weight = weight;
    }
}