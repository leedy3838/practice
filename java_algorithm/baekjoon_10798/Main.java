package baekjoon_10798;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[][] input = new String[5][];

        for(int i = 0; i < 5; i++) {
            input[i] = br.readLine().split("");
        }

        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 5; j++) {
                if(input[j].length <= i)
                    continue;

                if(input[j][i] != null)
                    System.out.print(input[j][i]);
            }
        }
    }
}
