package programmers_석유_시추;

import java.util.*;

class Solution {
    private static final int[] dR = new int[]{0, 0, -1, 1};
    private static final int[] dC = new int[]{1, -1, 0, 0};

    private static int rowNum, colNum, groupCnt = 1;
    private int[][] group;
    private final Map<Integer, Integer> map = new HashMap<>();

    public int solution(int[][] land) {
        int answer = 0;

        rowNum = land.length;
        colNum = land[0].length;

        group = new int[rowNum][colNum];

        for (int col = 0; col < colNum; col++) {        //각각의 열
            int maxVal = 0;
            Set<Integer> set = new HashSet<>();

            for (int row = 0; row < rowNum; row++) {    //각각의 행
                if (land[row][col] == 1) {  //석유 존재
                    if (group[row][col] == 0) { //기존 그룹 존재 X
                        maxVal += bfs(row, col, land);
                        set.add(group[row][col]);
                    } else {                    //기존 그룹 존재
                        if (!set.contains(group[row][col])) {
                            maxVal += map.get(group[row][col]);
                            set.add(group[row][col]);
                        }
                    }
                }
            }

            answer = Math.max(answer, maxVal);
        }

        return answer;
    }

    private int bfs(int row, int col, int[][] land) {
        int count = 0;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(row, col));
        group[row][col] = groupCnt;
        count++;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int dr = node.row + dR[i];
                int dc = node.col + dC[i];

                if (dr < 0 || dc < 0 || dr >= rowNum || dc >= colNum) {
                    continue;
                }

                if (land[dr][dc] == 1 && group[dr][dc] == 0) {
                    count++;
                    q.add(new Node(dr, dc));
                    group[dr][dc] = groupCnt;
                }
            }
        }

        map.put(groupCnt++, count);

        return count;
    }
}

class Node {
    int row, col;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }
}