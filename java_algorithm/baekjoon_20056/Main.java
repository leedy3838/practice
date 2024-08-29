package baekjoon_20056;

import java.io.*;
import java.util.*;

public class Main {

    private static final int[] dR = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] dC = new int[]{0, 1, 1, 1, 0, -1, -1, -1};

    private static int N, M, K;
    private static final Queue<FireBall> fireBallQueue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fireBallQueue.offer(new FireBall(r, c, m, s, d));
        }
    }

    private static void solveProblem() {
        while (K-- > 0) {
            Queue<FireBall> tmpQ = new PriorityQueue<>();

            while (!fireBallQueue.isEmpty()) {
                tmpQ.offer(moveFireBall(fireBallQueue.poll()));
            }

            while (!tmpQ.isEmpty()) {
                FireBall fireBall = tmpQ.poll();

                int mSum = fireBall.m;
                int sSum = fireBall.s;
                boolean isOdd = fireBall.d % 2 == 1;
                boolean isSameDir = true;
                int cnt = 1;

                while (!tmpQ.isEmpty() && fireBall.r == tmpQ.peek().r && fireBall.c == tmpQ.peek().c) {
                    fireBall = tmpQ.poll();

                    mSum += fireBall.m;
                    sSum += fireBall.s;
                    cnt++;

                    if (!(isOdd == (fireBall.d % 2 == 1))) {
                        isSameDir = false;
                    }
                }

                if (mSum > fireBall.m) {
                    if (mSum / 5 > 0) {
                        if (isSameDir) {
                            for (int i = 0; i <= 6; i += 2) {
                                fireBallQueue.offer(new FireBall(fireBall.r, fireBall.c, mSum / 5, sSum / cnt, i));
                            }
                        } else {
                            for (int i = 1; i <= 7; i += 2) {
                                fireBallQueue.offer(new FireBall(fireBall.r, fireBall.c, mSum / 5, sSum / cnt, i));
                            }
                        }
                    }
                } else {
                    fireBallQueue.offer(fireBall);
                }
            }
        }

        int ans = 0;
        while (!fireBallQueue.isEmpty()) {
            ans += fireBallQueue.poll().m;
        }
        System.out.println(ans);
    }

    private static FireBall moveFireBall(FireBall fireBall) {
        int dr = (fireBall.r + dR[fireBall.d] * fireBall.s) % N;
        int dc = (fireBall.c + dC[fireBall.d] * fireBall.s) % N;

        while (dr <= 0) {
            dr += N;
        }
        while (dc <= 0) {
            dc += N;
        }

        return new FireBall(dr, dc, fireBall.m, fireBall.s, fireBall.d);
    }

    private static class FireBall implements Comparable<FireBall> {
        int r, c, m, s, d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        public int compareTo(FireBall fireBall) {
            if (this.r == fireBall.r) {
                return this.c - fireBall.c;
            }
            return this.r - fireBall.r;
        }
    }
}
