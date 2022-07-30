package baekjoon_9470;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int K, M, P;

            K = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());

            int[][] count = new int[M+1][M+1];
            int [] parentNum = new int[M+1];
            List<List<Integer>> l = new ArrayList<>();
            for(int i =0; i<=M; i++)
                l.add(new ArrayList<>());

            for(int i =0; i<P; i++){
                st = new StringTokenizer(br.readLine());

                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                l.get(A).add(B);
                parentNum[B]++;
            }
            Queue<Integer> q = new LinkedList<>();

            for(int i=1; i<=M; i++)
                if(parentNum[i]==0) {
                    q.add(i);
                    count[i][1] = 1;
                }

            int result =0;
            while(!q.isEmpty()){
                int nodeIndex = q.poll();
                int ci = findMax(count[nodeIndex], M);

                if(count[nodeIndex][ci]>=2){
                    ci++;
                    count[nodeIndex][ci]=1;   // 순서가 i인 강이 2개 이상일 때 i+1을 순서로
                }
                result = Math.max(ci, result);

                for(int in: l.get(nodeIndex)){
                    count[in][ci]++;
                    if(--parentNum[in]==0){
                        q.add(in);
                    }
                }

            }
            System.out.println(K+" "+result);
        }
    }
    static int findMax(int[] arr, int m){
        int max = 0;
        for(int i = m; i>0; i--){
            if(arr[i]>0){
                max=i;
                break;
            }
        }
        return max;
    }
}