package baekjoon_2036;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> minus = new PriorityQueue<>();
        Queue<Integer> plus = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer num1, Integer num2) {
                return num2 - num1;
            }
        });

        for(int i = 0; i<N; i++){
            int num = Integer.parseInt(br.readLine());

            if(num > 0)
                plus.offer(num);
            else
                minus.offer(num);
        }

        BigInteger sum = BigInteger.valueOf(0);

        //양수의 경우
        while(!plus.isEmpty()){
            int num1 = plus.poll();

            if(plus.isEmpty()) {
                sum = sum.add(BigInteger.valueOf(num1));
                break;
            }
            else{
                int num2 = plus.poll();

                if(num2 == 1)
                    sum = sum.add(BigInteger.valueOf(num1 + num2));
                else
                    sum = sum.add(BigInteger.valueOf((long)num1 * num2));
            }
        }

        //음수, 0의 경우
        while(!minus.isEmpty()){
            int num1 = minus.poll();

            if(minus.isEmpty()) {
                sum = sum.add(BigInteger.valueOf(num1));
                break;
            }
            else{
                int num2 = minus.poll();

                sum = sum.add(BigInteger.valueOf((long)num1 * num2));
            }
        }

        System.out.println(sum);
    }
}
