package baekjoon_2461;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] indexList;

    public static void main(String[] args) {

        firstSetting();
        System.out.println(findVal());
    }

    private static void firstSetting(){

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            indexList = new int[N];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());

                for(int j = 0; j < M; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }

            for(int i = 0; i < N; i++)
                Arrays.sort(map[i]);
        } catch (IOException e){
            e.printStackTrace(System.out);
        }
    }

    private static int findVal(){

        int sum = Integer.MAX_VALUE;

        while(true){
            int minVal = Integer.MAX_VALUE;
            int minIdx = -1;
            int maxVal = -1;

            for(int i = 0; i < N; i++){
                int index = indexList[i];
                int val = map[i][index];

                if(val <= minVal){
                    minVal = val;
                    minIdx = i;
                }
                if(val >= maxVal){
                    maxVal = val;
                }
            }

            sum = Math.min(sum, maxVal - minVal);
            indexList[minIdx]++;

            if(indexList[minIdx] == M)
                break;
        }

        return sum;
    }
}
