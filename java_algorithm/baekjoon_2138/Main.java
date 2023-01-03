package baekjoon_2138;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String inputS = br.readLine();
        String targetS = br.readLine();

        int[] inputFirst = new int[N];
        int[] inputNotFirst = new int[N];
        int[] target = new int[N];

        for(int i = 0; i<N; i++){
            inputFirst[i] = inputS.charAt(i) - '0';
            inputNotFirst[i] = inputS.charAt(i) - '0';
            target[i] = targetS.charAt(i) - '0';
        }

        int cntFirst = 1, cntNotFirst = 0;
        inputFirst[0] = 1 - inputFirst[0];
        inputFirst[1] = 1 - inputFirst[1];

        for(int i = 1; i<N; i++){
            if(inputFirst[i-1] != target[i-1]){
                inputFirst[i-1] = 1 - inputFirst[i-1];
                inputFirst[i] = 1 - inputFirst[i];
                if(i != N-1)
                    inputFirst[i+1] = 1 - inputFirst[i+1];

                cntFirst++;
            }

            if(inputNotFirst[i-1] != target[i-1]){
                inputNotFirst[i-1] = 1 - inputNotFirst[i-1];
                inputNotFirst[i] = 1 - inputNotFirst[i];
                if(i != N-1)
                    inputNotFirst[i+1] = 1 - inputNotFirst[i+1];

                cntNotFirst++;
            }
        }

        boolean flagFirst = true, flagNotFirst = true;

        if(inputFirst[N-1] != target[N-1])
            flagFirst = false;
        if(inputNotFirst[N-1] != target[N-1])
            flagNotFirst = false;

        if(flagFirst && flagNotFirst)
            System.out.println(Math.min(cntFirst, cntNotFirst));
        else if(flagFirst)
            System.out.println(cntFirst);
        else if(flagNotFirst)
            System.out.println(cntNotFirst);
        else
            System.out.println(-1);
    }
}