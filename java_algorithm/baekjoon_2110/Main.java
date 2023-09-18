package baekjoon_2110;

import java.io.*;
import java.util.*;

public class Main {

    static int N, C;
    static int[] map;

    public static void main(String[] args){

        firstSetting();
        binarySearch();
    }

    static void firstSetting(){

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N];

            for(int i = 0; i < N; i++){
                map[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(map);
        } catch (IOException e){
            e.printStackTrace(System.out);
        }
    }

    static void binarySearch(){

        int left = 1;
        int right = map[N-1] - map[0];

        while(left <= right){
            int mid = left + (right-left)/2;
            int num = putRouter(left, right);

            if(num >= C){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }

        System.out.println(left - 1);
    }

    static int putRouter(int left, int right){

        int mid = left + (right-left)/2;
        int exV = map[0];

        int num = 1;
        for (int vertex : map) {
            if(vertex - exV >= mid){
                exV = vertex;
                num++;
            }
        }

        return num;
    }
}
