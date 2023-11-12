package baekjoon_5622;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("");

        int time = 0;
        for(String alpha : input) {
            int alphaChar = alpha.charAt(0);

            if(alphaChar >= 'A' && alphaChar <= 'C')
                time += 3;
            else if(alphaChar >= 'D' && alphaChar <= 'F')
                time += 4;
            else if(alphaChar >= 'G' && alphaChar <= 'I')
                time += 5;
            else if(alphaChar >= 'J' && alphaChar <= 'L')
                time += 6;
            else if(alphaChar >= 'M' && alphaChar <= 'O')
                time += 7;
            else if(alphaChar >= 'P' && alphaChar <= 'S')
                time += 8;
            else if(alphaChar >= 'T' && alphaChar <= 'V')
                time += 9;
            else if(alphaChar >= 'W' && alphaChar <= 'Z')
                time += 10;
        }

        System.out.println(time);
    }
}
