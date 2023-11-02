package baekjoon_12015;

import java.util.*;
import java.io.*;

public class Main {

    private static int[] intArr;
    private static int arrLen;

    public static void main(String[] args) throws IOException{

        solveProblem();
    }

    private static void solveProblem(){

        firstSetting();
        int len = findMaxLen();
        printAns(len);
    }

    private static void firstSetting(){

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            arrLen = Integer.parseInt(br.readLine());
            intArr = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (IOException e){
            e.printStackTrace(System.out);
        }
    }

    private static int findMaxLen(){

        int[] tmpArr = new int[arrLen];
        tmpArr[0] = intArr[0];
        int nowIdx = 0;

        for(int i = 0; i < arrLen; i++){

            if (intArr[i] > tmpArr[nowIdx]){
                tmpArr[++nowIdx] = intArr[i];
            } else if (intArr[i] < tmpArr[nowIdx]){
                int idx = binarySearchIdx(tmpArr, nowIdx, intArr[i]);
                tmpArr[idx] = intArr[i];
            }
        }

        return nowIdx + 1;
    }

    //lower bound
    private static int binarySearchIdx(int[] tmpArr, int lastIdx, int target){

        int left = 0;
        int right = lastIdx;

        while(left < right){
            int mid  = left + (right - left) / 2;

            if(tmpArr[mid] < target){
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    private static void printAns(int len){
        System.out.println(len);
    }
}
