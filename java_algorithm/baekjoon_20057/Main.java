package baekjoon_20057;

import java.io.*;
import java.util.*;

public class Main {

    static int N, amountOfOutSand = 0;
    static int[][] map;
    static int[] dC = {0, 1, 0, -1};
    static int[] dR = {-1, 0, 1, 0};
    static int[] moveCount = {1, 1, 2, 2};
    static int[][] dCOfSand = {{-2, -1, -1, -1, 0, 1, 1, 1, 2}, {0, 1, 0, -1, 2, 1, 0, -1, 0}, {2, 1, 1, 1, 0, -1, -1, -1, -2}, {0, -1, 0, 1, -2, -1, 0, 1, 0}};
    static int[][] dROfSand = {{0, -1, 0, 1, -2, -1, 0, 1, 0}, {-2, -1, -1, -1, 0, 1, 1, 1, 2}, {0, 1, 0, -1, 2, 1, 0, -1, 0}, {2, 1, 1, 1, 0, -1, -1, -1, -2}};
    static int[] ratioOfSand = {2, 10, 7, 1, 5, 10, 7, 1, 2};

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
    }

    private static void solveProblem() {
        int curX = N / 2;
        int curY = N / 2;

        while (true) {
            boolean endFlag = false;

            for (int d = 0; d < 4; d++) {
                for (int i = 0; i < moveCount[d]; i++) {
                    int dc = curX + dC[d];
                    int dr = curY + dR[d];

                    if (dc < 0 || dr < 0 || N <= dc || N <= dr) {
                        endFlag = true;
                        break;
                    }

                    int sand = map[dc][dr];
                    map[dc][dr] = 0;
                    int spreadTotal = 0;

                    for (int s = 0; s < 9; s++) {
                        int sandX = dc + dCOfSand[d][s];
                        int sandY = dr + dROfSand[d][s];
                        int spreadAmount = (sand * ratioOfSand[s]) / 100;

                        if (sandX < 0 || sandY < 0 || N <= sandX || N <= sandY) amountOfOutSand += spreadAmount;
                        else map[sandX][sandY] += spreadAmount;

                        spreadTotal += spreadAmount;
                    }

                    int alphaX = dc + dC[d];
                    int alphaY = dr + dR[d];
                    int amountOfAlpha = sand - spreadTotal;

                    if (alphaX < 0 || alphaY < 0 || N <= alphaX || N <= alphaY) amountOfOutSand += amountOfAlpha;
                    else map[alphaX][alphaY] += amountOfAlpha;

                    curX = dc;
                    curY = dr;
                }
            }

            for (int i = 0; i < 4; i++) {
                moveCount[i] += 2;
            }

            if (endFlag) {
                break;
            }
        }

        System.out.println(amountOfOutSand);
    }
}