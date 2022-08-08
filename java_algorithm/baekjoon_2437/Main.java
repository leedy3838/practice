package baekjoon_2437;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] intArr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++)
            intArr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(intArr);

        if(intArr[0]>1){
            System.out.println(1);
            return;
        }

        int ans = intArr[0];
        for(int i = 1; i<N; i++){
            if(ans+1<intArr[i]){
                ans += 1;
                break;
            }

            ans += intArr[i];

            if(i == N-1)
                ans++;
        }

        System.out.println(ans);
    }
}
