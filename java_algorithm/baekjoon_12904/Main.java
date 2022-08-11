package baekjoon_12904;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputA = br.readLine().split("");
        String[] inputB = br.readLine().split("");

        List<String> A  = new ArrayList<>();
        List<String> B = new ArrayList<>();

        Collections.addAll(A, inputA);
        Collections.addAll(B, inputB);

        while(A.size() != B.size()){
            String last = B.get(B.size()-1);

            if(last.equals("A"))
                B.remove(B.size()-1);
            else{
                B.remove(B.size()-1);
                Collections.reverse(B);
            }
        }

        boolean flag = true;

        for(int i = 0; i<A.size(); i++){
            if(!A.get(i).equals(B.get(i))){
                flag = false;
                break;
            }
        }

        if(flag)
            System.out.println(1);
        else
            System.out.println(0);
    }
}
