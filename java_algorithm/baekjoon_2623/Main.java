package baekjoon_2623;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<List<Integer>> l = new ArrayList<>();
    static int[] parentNum;
    static int[] answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parentNum = new int[N+1];
        answer = new int[N];

        for(int i = 0; i<=N; i++)
            l.add(new ArrayList<>());

        while(M-->0){
            st = new StringTokenizer(br.readLine());

            int cnt = Integer.parseInt(st.nextToken());
            int exNum = Integer.parseInt(st.nextToken());

            for(int i = 1; i<cnt; i++){
                int num = Integer.parseInt(st.nextToken());

                l.get(exNum).add(num);
                parentNum[num]++;

                exNum = num;
            }
        }

        findOrder();

        for(int i = 0; i<N; i++) {
            if (answer[i] == 0) {
                System.out.println(0);
                return;
            }
        }

        for(int i = 0 ; i<N; i++)
            System.out.println(answer[i]);
    }

    static void findOrder() {
        Queue<Integer> q = new LinkedList<>();
        int answerIdx = 0;

        for (int i = 1; i <= N; i++)
            if(parentNum[i] == 0) {
                q.offer(i);
                answer[answerIdx++] = i;
            }

        while(!q.isEmpty()){
            int num = q.poll();

            for(int i = 0; i<l.get(num).size(); i++){
                int nextNum = l.get(num).get(i);

                parentNum[nextNum]--;
                if(parentNum[nextNum] == 0){
                    q.offer(nextNum);
                    answer[answerIdx++] = nextNum;
                }
            }
        }
    }
}
