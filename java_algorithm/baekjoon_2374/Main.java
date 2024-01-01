package baekjoon_2374;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        solveProblem();
    }
    private static void solveProblem() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> s = new Stack<>();

        long cnt = 0;
        int max = 0;

        while (N-- > 0) {
            int num = Integer.parseInt(br.readLine());
            max = Math.max(max, num);

            if (s.isEmpty()) {                  //스택이 비었을 때
                s.push(num);
            } else {
                if (s.peek() < num) {           //스택이 비지 않고 들어온 값이 스택의 최대값보다 클 때 - 차이값 더하기
                    cnt += num - s.pop();
                } else if (s.peek() > num) {    //스택이 비지 않고 들어온 값이 스택의 최대값보다 작을 때 - 더 작은 값만 스택에 push
                    s.pop();
                }

                s.push(num);
            }
        }

        cnt += max - s.pop();                   //마지막으로 스택에 남아 있는 값을 최대값만큼 쌓아 올리기

        System.out.println(cnt);
    }
}
