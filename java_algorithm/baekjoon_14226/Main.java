package baekjoon_14226;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int S = Integer.parseInt(br.readLine());

        //1차원은 화면에 있는 이모티콘 수
        //2차원은 클립보드에 있는 이모티콘 수
        boolean[][] isVisit = new boolean[S * 2][S * 2];

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(1, 0, 0));
        isVisit[1][0] = true;

        int ans = 0;
        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.cnt + node.clipBoard > S * 2)
                continue;

            if(node.cnt == S){
                ans = node.time;
                break;
            }

            //이모티콘을 모두 복사해서 클립보드에 저장 - 1초
            if(!isVisit[node.cnt][node.cnt]) {
                q.offer(new Node(node.cnt, node.time + 1, node.cnt));
                isVisit[node.cnt][node.cnt] = true;
            }

            //클립보드에 있는 이모티콘을 그냥 붙여넣기 - 1초
            if(!isVisit[node.cnt + node.clipBoard][node.clipBoard]) {
                q.offer(new Node(node.cnt + node.clipBoard, node.time + 1, node.clipBoard));
                isVisit[node.cnt + node.clipBoard][node.clipBoard] = true;
            }

            //화면에 있는 이모티콘 중 하나를 삭제 - 1초
            if(node.cnt - 1 > 0 && !isVisit[node.cnt - 1][node.clipBoard]){
                q.offer(new Node(node.cnt - 1, node.time + 1, node.clipBoard));
                isVisit[node.cnt - 1][node.clipBoard] = true;
            }
        }

        System.out.println(ans);
    }
}

class Node{
    int cnt, time, clipBoard;

    public Node(int cnt, int time, int clipBoard) {
        this.cnt = cnt;
        this.time = time;
        this.clipBoard = clipBoard;
    }
}