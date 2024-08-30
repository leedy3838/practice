package baekjoon_15685;

import java.io.*;
import java.util.*;

public class Main {

    //우, 상, 좌, 하
    private static final int[] dX = new int[]{1, 0, -1, 0};
    private static final int[] dY = new int[]{0, -1, 0, 1};

    private static final boolean[][] map = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        solveProblem();
    }

    private static void solveProblem() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            List<Integer> directionList = findDirections(d, g);

            drawMap(x, y, directionList);
        }

        System.out.println(countSquare());
    }

    private static List<Integer> findDirections(int d, int g) {
        List<Integer> directionList = new ArrayList<>();
        directionList.add(d);

        while (g-- > 0) {
            for (int i = directionList.size() - 1; i >= 0; i--) {
                int direction = directionList.get(i);
                directionList.add((direction + 1)  % 4);
            }
        }

        return  directionList;
    }

    private static void drawMap(int x, int y, List<Integer> directionList) {
        map[y][x] = true;

        for (int direction : directionList) {
            x += dX[direction];
            y += dY[direction];

            map[y][x] = true;
        }
    }

    private static int countSquare() {
        int cnt = 0;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1])
                    cnt++;
            }
        }

        return cnt;
    }
}
