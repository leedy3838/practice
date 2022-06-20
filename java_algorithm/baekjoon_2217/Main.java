package baekjoon_2217;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Integer[] valArr = new Integer[N];

        for(int i = 0; i<N; i++)
            valArr[i] = Integer.parseInt(br.readLine());

        Arrays.sort(valArr, new Comparator<>(){
            @Override
            public int compare(Integer a, Integer b){
                return b-a;
            }
        });

        int max = Integer.MIN_VALUE;

        for(int i = 0; i<N; i++)
            max = Math.max(max, (i+1)*valArr[i]);

        System.out.println(max);
    }
}
