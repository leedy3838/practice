package baekjoon_20665;

import java.io.*;
import java.util.*;

public class Main {

    private static int P;
    private static final List<int[]> users = new ArrayList<>();
    private static int[] seatStatus;

    public static void main(String[] args) {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());

            seatStatus = new int[N + 1];

            while (T-- > 0) {
                st = new StringTokenizer(br.readLine());

                int startTime = convertTimeToMinute(st.nextToken());
                int endTime = convertTimeToMinute(st.nextToken());

                users.add(new int[] {startTime, endTime});
            }

            users.sort((arr1, arr2) -> {
                if (arr1[0] == arr2[0])
                    return arr1[1] - arr2[1];
                return arr1[0] - arr2[0];
            });
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static int convertTimeToMinute(String time) {
        int hour = Integer.parseInt(time.substring(0, 2)) * 60;
        int minute = Integer.parseInt(time.substring(2));

        return hour + minute;
    }

    private static void solveProblem() {

        int totalPossibleTime = 12 * 60;    //모든 시간이 가능할 때

        while (!users.isEmpty()) {
            int[] user = users.remove(0);
            int startTime = user[0];
            int endTime = user[1];

            clearMembers(startTime);
            int seatNum = findSeat();
            seatStatus[seatNum] = endTime;

            if (seatNum == P) {
                totalPossibleTime -= endTime - startTime;
            }
        }

        System.out.println(totalPossibleTime);
    }

    private static int findSeat() {

        int pos = 0;
        int maxDist = 0;

        for (int i = 1; i <= seatStatus.length - 1; i++) {
            if (seatStatus[i] == 0) {
                int dist = getDist(i);

                if (maxDist < dist) {
                    maxDist = dist;
                    pos = i;
                }
            }
        }

        return pos;
    }

    private static int getDist(int index) {

        int minDist = Integer.MAX_VALUE;

        for (int i = 1; i <= seatStatus.length - 1; i++) {
            if (i == index)
                continue;

            if (seatStatus[i] != 0 && Math.abs(i - index) < minDist) {
                minDist = Math.abs(i - index);
            }
        }

        return minDist;
    }

    //이용 가능 시간이 지난 멤버들 제거
    private static void clearMembers(int startTime) {
        for (int i = 1; i < seatStatus.length; i++) {
            if (seatStatus[i] <= startTime)
                seatStatus[i] = 0;
        }
    }
}
