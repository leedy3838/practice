package baekjoon_10988;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        boolean isOk = true;

        for(int i = 0; i < input.length() / 2; i++){
            if(input.charAt(i) != input.charAt(input.length() - i - 1)){
                isOk = false;
                break;
            }
        }

        if(isOk)
            System.out.println(1);
        else
            System.out.println(0);
    }
}
