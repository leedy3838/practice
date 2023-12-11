package baekjoon_1264;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {

            String input = br.readLine();

            if(input.equals("#"))
                break;

            StringTokenizer st = new StringTokenizer(input);
            int cnt = 0;

            while(st.hasMoreTokens()) {
                String str = st.nextToken();

                for(int i = 0; i < str.length(); i++) {
                    char alpha = str.charAt(i);

                    if(alpha == 'a' || alpha == 'e' || alpha == 'i' || alpha == 'o' || alpha == 'u')
                        cnt++;
                    if(alpha == 'A' || alpha == 'E' || alpha == 'I' || alpha == 'O' || alpha == 'U')
                        cnt++;
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.print(sb);
    }
}
