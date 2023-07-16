package baekjoon_2879;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        //현재 탭과 올바른 탭의 차이를 저장하는 배열
        int[] indentGap = input(br, N);

        //편집 횟수를 구하기
        int cnt = findEditCnt(N, indentGap);

        System.out.println(cnt);
    }

    private static int[] input(BufferedReader br, int N) throws IOException{

        StringTokenizer st;
        int[] indentGap = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++)
            indentGap[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            int targetIndent = Integer.parseInt(st.nextToken());

            indentGap[i] = targetIndent - indentGap[i];
        }

        return indentGap;
    }

    private static int findEditCnt(int N, int[] indentGap) {
        int cnt = 0;

        while(true){
            //연속으로 한번에 수정할 수 있는 index를 저장
            List<Integer> editIdx = new ArrayList<>();
            Boolean isFirstPlus = null;

            for(int i = 0; i< N; i++){
                //초반의 0을 처리하기 위해서 ex) 0 0 1 -1
                if(isFirstPlus == null && indentGap[i] == 0)
                    continue;

                //초반이 아니고 0이 들어온 경우 ex) 0 0 1 0 -1
                if(isFirstPlus != null && indentGap[i] == 0)
                    break;

                if(isFirstPlus == null){
                    isFirstPlus = indentGap[i] > 0;

                    editIdx.add(i);
                }

                else{
                    if(indentGap[i]>0 && isFirstPlus)
                        editIdx.add(i);

                    else if(indentGap[i]<0 && !isFirstPlus)
                        editIdx.add(i);

                    else
                        break;
                }
            }

            //indentGap이 모두 0이다
            if(isFirstPlus == null)
                break;

            if(isFirstPlus){
                for(int index : editIdx)
                    indentGap[index]--;
            }

            else {
                for (int index : editIdx)
                    indentGap[index]++;
            }

            cnt++;
        }

        return cnt;
    }
}
