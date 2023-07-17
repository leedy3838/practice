package baekjoon_1931;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Queue<Node> q = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            q.offer(new Node(start, end));
        }

        int cnt = 0;
        int endTime = Integer.MIN_VALUE;

        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.start >= endTime){
                cnt++;
                endTime = node.end;
            }
        }

        System.out.println(cnt);
    }
}
class Node implements Comparable<Node>{
    int start, end;

    public Node(int start, int end) {
        this.start = start;
        this.end = end;
    }

    //2
    //9 9
    //1 9
    //위와 같은 케이스가 있기 때문에 끝 시간이 같으면 시작 시간 기준 오름차순으로 정렬해야 함
    @Override
    public int compareTo(Node node) {
        if(this.end == node.end)
            return this.start - node.start;
        return this.end - node.end;
    }
}