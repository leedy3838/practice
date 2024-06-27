package baekjoon_21608;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static String[] data;

    private static final int[] dR = new int[]{0, 0, 1, -1};
    private static final int[] dC = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        data = new String[N * N];
        for (int i = 0; i < N * N; i++) {
            data[i] = br.readLine();
        }
    }

    private static void solveProblem() {
        StringTokenizer st;
        int[][] map = new int[N][N];
        List<Set<Integer>> friendList = new ArrayList<>();
        for (int i = 0; i <= N * N; i++) {
            friendList.add(new HashSet<>());
        }

        for (int i = 0; i < N * N; i++) {
            Set<Integer> friendSet = new HashSet<>();
            Queue<Node> findSeat = new PriorityQueue<>();

            st = new StringTokenizer(data[i]);

            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                int friend = Integer.parseInt(st.nextToken());

                friendList.get(num).add(friend);
                friendSet.add(friend);
            }

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (map[row][col] != 0) {
                        continue;
                    }

                    int friendCnt = 0;
                    int emptyCnt = 0;

                    for (int loop = 0; loop < 4; loop++) {
                        int dr = row + dR[loop];
                        int dc = col + dC[loop];

                        if (dr < 0 || dc < 0 || dr >= N || dc >= N)
                            continue;

                        if (friendSet.contains(map[dr][dc]))
                            friendCnt++;
                        if (map[dr][dc] == 0)
                            emptyCnt++;
                    }

                    findSeat.add(new Node(row, col, friendCnt, emptyCnt));
                }
            }

            Node seat = findSeat.poll();
            map[seat.row][seat.col] = num;
        }

        int sum = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int friendCnt = 0;

                for (int loop = 0; loop < 4; loop++) {
                    int dr = row + dR[loop];
                    int dc = col + dC[loop];

                    if (dr < 0 || dc < 0 || dr >= N || dc >= N)
                        continue;

                    if (friendList.get(map[row][col]).contains(map[dr][dc]))
                        friendCnt++;
                }

                if (friendCnt == 1) {
                    sum += 1;
                } else if (friendCnt == 2) {
                    sum += 10;
                } else if (friendCnt == 3) {
                    sum += 100;
                } else if (friendCnt == 4) {
                    sum += 1000;
                }
            }
        }

        System.out.println(sum);
    }

    private static class Node implements Comparable<Node> {
        int row, col, nearFriend, nearEmpty;

        public Node(int row, int col, int nearFriend, int nearEmpty) {
            this.row = row;
            this.col = col;
            this.nearFriend = nearFriend;
            this.nearEmpty = nearEmpty;
        }

        //주변 친구 많은 순 -> 빈 땅 많은 순 -> row 작은 순 -> col 작은 순
        public int compareTo(Node node) {
            if (this.nearFriend == node.nearFriend) {
                if (this.nearEmpty == node.nearEmpty) {
                    if (this.row == node.row) {
                        return this.col - node.col;
                    }
                    return this.row - node.row;
                }
                return node.nearEmpty - this.nearEmpty;
            }
            return node.nearFriend - this.nearFriend;
        }
    }
}
