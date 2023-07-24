package baekjoon_2565;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<Node> l = new ArrayList<>();
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            l.add(new Node(start, end));
        }

        Collections.sort(l);

        int[] dp = new int[N];

        //새로 들어온 전기줄의 번호가 i
        for(int i = 0; i<N; i++){
            dp[i] = 1;

            //이전에 있던 전기줄의 번호가 j
            for(int j = 0; j<i; j++){
                if(l.get(i).end > l.get(j).end)
                    dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }

        int max = 0;
        for(int i = 0; i<N; i++)
            max = Math.max(max, dp[i]);

        System.out.println(N-max);
    }
}
class Node implements Comparable<Node>{
    int start, end;

    public Node(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Node node){
        return this.start - node.start;
    }
}