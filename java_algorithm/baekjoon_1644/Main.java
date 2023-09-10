package baekjoon_1644;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args ) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if(N < 2){
            System.out.println(0);
            return;
        }

        boolean[] isNotPrime = findPrime(N);

        List<Integer> l = new ArrayList<>();
        for(int i = 2; i <= N; i++)
            if(!isNotPrime[i])
                l.add(i);

        int cnt = 0;
        int sum = l.get(0);
        int left = 0, right = 0;
        while(left < l.size()){

            if(sum < N){
                if(right + 1 < l.size())
                    sum += l.get(++right);
                else
                    break;
            }
            else if(sum > N){
                sum -= l.get(left++);
            }
            else{
                cnt++;
                if(right + 1 < l.size())
                    sum += l.get(++right);
                else
                    break;
            }
        }

        System.out.println(cnt);
    }

    static boolean[] findPrime(int N){

        boolean[] isNotPrime = new boolean[N + 1];

        for(int i = 2; i <= N; i++){
            if(isNotPrime[i])
                continue;

            int tmp = i * 2;
            while(tmp <= N){
                isNotPrime[tmp] = true;
                tmp += i;
            }
        }

        return isNotPrime;
    }
}
