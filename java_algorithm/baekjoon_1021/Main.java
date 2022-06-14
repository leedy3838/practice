package baekjoon_1021;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int cnt = 0;

        LinkedList<Integer> dq = new LinkedList<>();
        for(int  i = 1; i<=N; i++)
            dq.addLast(i);

        int[] find = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<M; i++)
            find[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i<M; i++){
            if(dq.getFirst() == find[i]){
                dq.removeFirst();
                continue;
            }

            // 2번 연산을 이용했을 때 필요한 연산의 수
            int findIdx = dq.indexOf(find[i]);
            // 3번 연산을 이용했을 때 필요한 연산의 수
            int backCnt = dq.size() - findIdx;

            // 2번 연산 실행
            if(findIdx < backCnt){
                while(dq.getFirst() != find[i]){
                    dq.addLast(dq.removeFirst());
                    cnt++;
                }
            }

            // 3번 연산 실행
            else{
                while(dq.getFirst() != find[i]){
                    dq.addFirst(dq.removeLast());
                    cnt++;
                }
            }

            dq.removeFirst();
        }

        System.out.println(cnt);
    }
}
