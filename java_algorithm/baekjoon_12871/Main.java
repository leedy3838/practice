package baekjoon_12871;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        solveProblem();
    }

    private static void solveProblem() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();

        int lcm = lcm(s.length(), t.length());

        String tempS = s;
        while (tempS.length() < lcm) {
            tempS = tempS.concat(s);
        }

        String tempT = t;
        while (tempT.length() < lcm) {
            tempT = tempT.concat(t);
        }

        if (tempS.equals(tempT)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static int gcd(int s, int t) {
        if (t == 0) {
            return s;
        }
        return gcd(t, s % t);
    }

    private static int lcm(int s, int t) {
        return s * t / gcd(s, t);
    }
}
