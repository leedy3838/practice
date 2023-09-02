package baekjoon_16946;

import java.io.*;
import java.util.*;

public class Main {

    static int[] dR = {0, 0, 1, -1};
    static int[] dC = {1, -1, 0, 0};
    static int N, M;
    static int[][] map, printMap, key;
    static boolean[][] isVisit;
    static Map<Integer, Integer> set = new HashMap<>();

    public static void main(String[] args) throws IOException{

        makeMap();
        makeKeyArr();
        makePrintMap();
        printMap();
    }

    private static void makeMap() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        printMap = new int[N][M];
        key = new int[N][M];
        isVisit = new boolean[N][M];

        for(int i = 0; i < N; i++){
            String[] input = br.readLine().split("");

            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
    }

    private static void makeKeyArr(){

        int keyNum = 1;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 1)
                    continue;
                if(isVisit[i][j])
                    continue;

                makeGroup(i, j, keyNum++);
            }
        }
    }

    private static void makeGroup(int row, int col, int keyNum){

        Queue<Node> q = new LinkedList<>();

        int count = 1;
        q.offer(new Node(row, col));
        key[row][col] = keyNum;
        isVisit[row][col] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            for(int i = 0; i < 4; i++){
                int dr = node.row + dR[i];
                int dc = node.col + dC[i];

                if(dr < 0 || dc < 0 || dr >= N || dc >= M)
                    continue;
                if(isVisit[dr][dc])
                    continue;
                if(map[dr][dc] == 1)
                    continue;

                count++;
                q.offer(new Node(dr, dc));
                key[dr][dc] = keyNum;
                isVisit[dr][dc] = true;
            }
        }

        set.put(keyNum, count);
    }

    private static void makePrintMap() {

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 0)
                    continue;

                printMap[i][j] = findNum(i, j);
            }
        }
    }

    private static int findNum(int row, int col){

        int count = 1;
        List<Integer> keyNumList = new ArrayList<>();

        for(int i = 0 ; i < 4; i++){
            int dr = row + dR[i];
            int dc = col + dC[i];

            if(dr < 0 || dc < 0 || dr >= N || dc >= M)
                continue;
            if(map[dr][dc] == 1)
                continue;

            int keyNum = key[dr][dc];

            if(keyNumList.contains(keyNum))
                continue;

            count += set.get(keyNum);
            keyNumList.add(keyNum);
        }

        return count % 10;
    }

    private static void printMap(){

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                sb.append(printMap[i][j]);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static class Node{
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
