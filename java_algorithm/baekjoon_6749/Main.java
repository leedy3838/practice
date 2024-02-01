package baekjoon_6749;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int young = Integer.parseInt(br.readLine());
        int mid = Integer.parseInt(br.readLine());

        System.out.println(mid + (mid - young));
    }
}
