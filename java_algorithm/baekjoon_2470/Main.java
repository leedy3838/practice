package baekjoon_2470;

import java.io.*;
import java.util.*;

public class Main {

    private final static List<Integer> list = new ArrayList<>();
    private static int firstVal = 0;
    private static int secondVal = 0;

    public static void main(String[] args) {

        firstSetting();
        findSolution();
        printAnswer();
    }

    private static void firstSetting(){

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            int N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                list.add(Integer.parseInt(st.nextToken()));
            }

            Collections.sort(list);
        } catch(IOException e){
            e.printStackTrace(System.out);
        }
    }

    private static void findSolution(){

        int left = 0;
        int right = list.size() - 1;
        int minVal = list.get(left);
        int maxVal = list.get(right);
        int minSum = minVal + maxVal;
        int sum = minSum;

        while(left < right){
            if(sum < 0){
                left++;
                sum = list.get(left) + list.get(right);

                if(left == right)
                    break;
                if(Math.abs(minSum) > Math.abs(sum)){
                    minVal = list.get(left);
                    maxVal = list.get(right);
                    minSum = list.get(left) + list.get(right);
                }
            }
            else if(sum > 0){
                right--;
                sum = list.get(left) + list.get(right);

                if(left == right)
                    break;
                if(Math.abs(minSum) > Math.abs(sum)){
                    minVal = list.get(left);
                    maxVal = list.get(right);
                    minSum = list.get(left) + list.get(right);
                }
            }
            else{
                break;
            }
        }

        firstVal = minVal;
        secondVal = maxVal;
    }

    private static void printAnswer(){
        System.out.println(firstVal + " " + secondVal);
    }
}
