package baekjoon_11918;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        //현재 가로등이 비추는 오른쪽 끝
        int exLightR = Integer.MIN_VALUE;
        //비상등과 가로등이 모두 비추는 오른쪽 끝
        int dupR = Integer.MIN_VALUE;

        long length = 0;

        st = new StringTokenizer(br.readLine());

        List<Integer> l = new ArrayList<>();
        for(int i = 0; i<N; i++)
            l.add(Integer.parseInt(st.nextToken()));

        Collections.sort(l);

        for(int i = 0; i<N; i++){
            int light = l.get(i);

            int lightL = light-L;
            int lightR = light+L;

            //초기 설정
            if(exLightR == Integer.MIN_VALUE){
                exLightR = lightR;

                continue;
            }

            //같은 영역을 비추는 가로등이 여러 개임
            if(lightL < exLightR){
                if(lightL < dupR)
                    length += exLightR - dupR;
                else
                    length += exLightR - lightL;

                dupR = exLightR;
            }

            exLightR = lightR;
        }

        System.out.println(length);
    }
}
