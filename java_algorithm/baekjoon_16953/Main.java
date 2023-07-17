package baekjoon_16953;

import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        System.out.println(bfs(A, B));
    }

    static int bfs(int start, int target){
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start, 1));

        while(!q.isEmpty()){
            Node v = q.poll();

            if(v.val == target)
                return v.cnt;

            if((long)v.val*2<=target)
                q.offer(new Node(v.val*2, v.cnt+1));
            if((long)v.val*10+1<=target)
                q.offer(new Node(v.val*10+1, v.cnt+1));
        }

        return -1;
    }
}
class Node implements Comparable<Node>{
    int val, cnt;

    Node(int val, int cnt){
        this.val = val;
        this.cnt = cnt;
    }

    public int compareTo(Node a){
        return a.val - this.val;
    }
}
