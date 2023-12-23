package baekjoon_25381;

import java.io.*;
import java.util.*;

//ArrayList를 쓰면 시간 초과가 나오고 LinkedList를 쓰면 정답 처리 됨
//get 보다는 remove, add의 횟수가 많아서 그럼
public class Main {

    private static final List<Integer> A = new LinkedList<>();
    private static final List<Integer> B = new LinkedList<>();
    private static final List<Integer> C = new LinkedList<>();

    public static void main(String[] args) {

        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input = br.readLine();

            for(int i = 0; i < input.length(); i++) {
                switch (input.charAt(i)) {
                    case 'A':
                        A.add(i);
                        break;
                    case 'B':
                        B.add(i);
                        break;
                    case 'C':
                        C.add(i);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        int cnt = 0;

        while(!B.isEmpty() && !C.isEmpty()) {
            int BIdx = B.get(0);

            //C가 B보다 뒤에 있으면 B와 C를 같이 제거
            //C가 B보다 앞에 있으면 C만 제거
            if(BIdx < C.remove(0)) {
                cnt++;
                B.remove(0);
            }
        }

        while(!A.isEmpty() && !B.isEmpty()) {
            int AIdx = A.get(0);

            //B가 A보다 뒤에 있으면 A와 B를 같이 제거
            //B가 A보다 앞에 있으면 B만 제거
            if(AIdx < B.remove(0)) {
                cnt++;
                A.remove(0);
            }
        }

        System.out.println(cnt);
    }
}
