package baekjoon_10026;

import java.io.*;
import java.util.*;

public class Main {
    //인접 행렬
    static char[][] array;
    static boolean[][] visited;
    //bfs를 이용하여 상하좌우로 이동할 수 있게 준비
    static int[] dR = {1, -1, 0, 0};
    static int[] dC = {0, 0, 1, -1};
    //입력받는 행렬의 크기
    static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        array = new char[N][N];

        //입력값을 받아 array에 저장
        for(int i = 0; i<N; i++){
            String[] input = br.readLine().split("");

            for(int j = 0; j<N; j++)
                array[i][j] = input[j].charAt(0);
        }
        //몇 개의 블록으로 나눌 수 있는지 확인
        int RGBColorCount = 0;
        int RBColorCount = 0;

        //RGB로 몇 개의 블록이 나오는지
        visited = new boolean[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(visited[i][j])
                    continue;

                RGBFind(i, j);
                RGBColorCount++;
            }
        }

        // RG/B의 그룹으로 몇 개의 블록이 나오는지
        visited = new boolean[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(visited[i][j])
                    continue;

                RBFind(i, j);
                RBColorCount++;
            }
        }

        System.out.println(RGBColorCount+" "+RBColorCount);
    }

    static void RGBFind(int row, int col){
        char color = array[row][col];
        Queue<Node> q = new LinkedList<>();

        q.offer(new Node(row, col));
        visited[row][col] = true;

        while(!q.isEmpty()){
            Node a = q.poll();

            for(int i = 0; i<4; i++){
                int dr = a.row + dR[i];
                int dc = a.col + dC[i];

                if(dr<0 || dc<0 || dr>=N || dc>=N)
                    continue;
                if(visited[dr][dc])
                    continue;

                if(array[dr][dc] == color) {
                    q.offer(new Node(dr, dc));
                    visited[dr][dc] = true;
                }
            }
        }
    }
    static void RBFind(int row, int col){
        char color = array[row][col];
        Queue<Node> q = new LinkedList<>();

        q.offer(new Node(row, col));
        visited[row][col] = true;

        while(!q.isEmpty()){
            Node a = q.poll();

            for(int i = 0; i<4; i++){
                int dr = a.row + dR[i];
                int dc = a.col + dC[i];

                if(dr<0 || dc<0 || dr>=N || dc>=N)
                    continue;
                if(visited[dr][dc])
                    continue;

                //R과 G는 같은 블록으로 취급
                if(color == 'R' || color == 'G'){
                    if(array[dr][dc] == 'R' || array[dr][dc] == 'G'){
                        q.offer(new Node(dr, dc));
                        visited[dr][dc] = true;
                    }
                }
                else {
                    if(color == array[dr][dc]) {
                        q.offer(new Node(dr, dc));
                        visited[dr][dc] = true;
                    }
                }
            }
        }
    }
}
class Node{
    int row, col;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }
}