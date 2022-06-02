package baekjoon_1629;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        long A = Long.parseLong(input[0]);
        long B = Long.parseLong(input[1]);
        long C = Long.parseLong(input[2]);

        System.out.println(pow(A % C, B, C));
    }

    private static long pow(long a, long b, long c) {

        if(b == 0)
            return 1;

        if(b == 1)
            return a;

        long temp = pow(a, b / 2, c) % c;

        // b가 짝수일 때
        if(b % 2 == 0)
            return (temp * temp) % c;

        // b가 홀수일 때
        else
            return (((temp * temp) % c) * a) % c;

    }
} 
