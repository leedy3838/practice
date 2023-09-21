package baekjoon_10775;

import java.io.*;
import java.util.*;

public class Main {

    static int G, P;
    static int[] parentNum;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        parentNum = new int[G+1];
        for(int i = 1; i <=G; i++)
            parentNum[i] = i;

        int cnt = 0;
        while(P-- > 0){
            int num = Integer.parseInt(br.readLine());

            if(find(num) == 0)
                break;

            cnt++;
            union(find(num), find(num) - 1);
        }

        System.out.println(cnt);
    }

    static void union(int a, int b){
        int aP = find(a);
        int bP = find(b);

        parentNum[aP] = bP;
    }

    static int find(int x){
        if(parentNum[x] == x)
            return x;
        else
            return parentNum[x] = find(parentNum[x]);
    }
}
