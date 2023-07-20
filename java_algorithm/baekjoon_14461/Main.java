package baekjoon_14461;

import java.io.*;
import java.util.*;

public class Main {
    static int[] dR = {0, 0, 1, -1};
    static int[] dC = {1, -1, 0, 0};
    static int N, T;
    static int[][] map;
    static boolean[][] isVisit;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        isVisit = new boolean[N][N];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j<N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(findTime());
    }

    static int findTime(){
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(0, 0, 0));

        while(!q.isEmpty()){
            Node vertex = q.poll();

            //q에 넣을 때 return하지 않는 이유는 만약 (2, 4)까지의 거리와 (4, 3)까지의 거리가 같을 때
            //(2, 4)가 먼저 실행되어 (4, 4)로 가면 시간이 더 걸리기 때문
            //-> row 먼저도 애매, col 먼저도 애매
            if(vertex.row == N-1 && vertex.col == N-1)
                return vertex.time;
            for(int i = 0; i<4; i++){
                int dr1 = vertex.row + dR[i];
                int dc1 = vertex.col + dC[i];

                if(dr1<0||dc1<0||dr1>=N||dc1>=N)
                    continue;
                if(dr1==N-1 && dc1==N-1){
                    q.offer(new Node(dr1, dc1, vertex.time+T));
                    continue;
                }

                for(int j = 0; j<4; j++){
                    int dr2 = dr1 + dR[j];
                    int dc2 = dc1 + dC[j];

                    if(dr2<0||dc2<0||dr2>=N||dc2>=N)
                        continue;
                    //이전 방문 정점 방문 X
                    if(dr2==dr1 && dc2==dc1)
                        continue;
                    if(dr2==N-1 && dc2==N-1){
                        q.offer(new Node(dr2, dc2, vertex.time+T*2));
                        continue;
                    }

                    for(int k = 0; k<4; k++){
                        int dr3 = dr2 + dR[k];
                        int dc3 = dc2 + dC[k];

                        if(dr3<0||dc3<0||dr3>=N||dc3>=N)
                            continue;
                        if(dr3==dr2 && dc3==dc2)
                            continue;
                        if(dr3==dr1 && dc3==dc1)
                            continue;
                        //3번 움직이면 풀을 먹어야 하기 때문에 여기서는 위에서와 같은 조건문 X

                        if(!isVisit[dr3][dc3]) {
                            q.offer(new Node(dr3, dc3, map[dr3][dc3] + vertex.time+T*3));
                            isVisit[dr3][dc3] = true;
                        }
                    }
                }
            }
        }

        return -1;
    }

    static class Node implements Comparable<Node>{
        int row, col, time;

        public Node(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }

        //시간의 오름차순으로 정렬
        @Override
        public int compareTo(Node node) {
            return this.time - node.time;
        }
    }
}
