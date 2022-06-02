package baekjoon_16953;

import java.util.*;
import java.io.*;

public class Main{ 
    static int B;
    static boolean get;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int cnt = bfs(A);

        if(get)
            System.out.println(cnt);
        else
            System.out.println(-1);
    }

    static int bfs(int start){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, 0));
        Node a = q.peek();

        while(!q.isEmpty()){
            a = q.poll();

            if(a.a == B){
                get = true;
                break;
            }

            if((long)a.a*2<=B)
                q.offer(new Node(a.a*2, a.cnt+1));
            if((long)a.a*10+1<=B)
                q.offer(new Node(a.a*10+1, a.cnt+1));

        }

        return a.cnt+1;
    }
}
class Node{
    int a, cnt;

    Node(int a, int cnt){
        this.a = a;
        this.cnt = cnt;
    }
}
