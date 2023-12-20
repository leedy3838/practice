package baekjoon_11559;

import java.io.*;
import java.util.*;

public class Main {

    private static final int[] dR = {0, 0, 1, -1};
    private static final int[] dC = {1, -1, 0, 0};

    private final static int LAST_ROW = 12;
    private final static int LAST_COLUMN = 6;

    private static boolean isModified = false;
    private static final char[][] map = new char[LAST_ROW][LAST_COLUMN];
    private static boolean[][] isVisit;

    public static void main(String[] args) {

        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            for(int i = LAST_ROW - 1; i >= 0; i--) {
                map[i] = br.readLine().toCharArray();
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        int cnt = 0;

        while(playGame()) {
            cnt++;
            isModified = false;
        }

        System.out.println(cnt);
    }

    private static boolean playGame() {

        playPuyoPuyo();

        if(!isModified)
            return false;

        moveBlocks();

        return true;
    }

    private static void playPuyoPuyo() {

        isVisit = new boolean[LAST_ROW][LAST_COLUMN];

        for(int i = 0; i < LAST_ROW; i++) {
            for(int j = 0; j < LAST_COLUMN; j++) {
                if(!isVisit[i][j] && map[i][j] != '.') {
                    bfs(i, j);
                }
            }
        }
    }

    private static void bfs(int row, int col) {

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(row, col));
        isVisit[row][col] = true;

        List<Node> l = new ArrayList<>();
        l.add(new Node(row, col));

        char target = map[row][col];

        while(!q.isEmpty()) {
            Node node = q.poll();

            for(int i = 0; i < 4; i++) {
                int dr = node.row + dR[i];
                int dc = node.col + dC[i];

                if(dr < 0 || dc < 0 || dr >= LAST_ROW || dc >= LAST_COLUMN)
                    continue;
                if(isVisit[dr][dc])
                    continue;
                if(map[dr][dc] != target)
                    continue;

                isVisit[dr][dc] = true;
                q.offer(new Node(dr, dc));
                l.add(new Node(dr, dc));
            }
        }

        if(l.size() >= 4) {
            for (Node node : l)
                map[node.row][node.col] = '.';

            isModified = true;
        }
    }

    private static void moveBlocks() {

        for(int i = 0; i < LAST_ROW; i++) {
            for(int j = 0; j < LAST_COLUMN; j++) {

                if(map[i][j] == '.')
                    downBlock(i, j);
            }
        }
    }

    private static void downBlock(int row, int col) {

        int changeRow = row;

        for(int i = row; i < LAST_ROW; i++) {
            if(map[i][col] != '.') {
                changeRow = i;
                break;
            }
        }

        map[row][col] = map[changeRow][col];
        map[changeRow][col] = '.';
    }

    static class Node {
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
