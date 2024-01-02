package baekjoon_20292;

import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

        solveProblem();
    }

    private static void solveProblem() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Set<String> writeSet = new HashSet<>();         //write한 데이터
        Set<String> readSet = new HashSet<>();          //read한 데이터

        while (true) {
            String input = br.readLine();
            st = new StringTokenizer(input);
            String op = st.nextToken();

            if (op.equals("EXIT")) {
                sb.append("EXIT");
                break;
            } else if (op.equals("WRITE")) {
                String from = st.nextToken();
                st.nextToken();
                String to = st.nextToken();

                if (readSet.contains(to)) {
                    sb.append("WAIT\n");
                    writeSet.clear();
                    readSet.clear();
                } else if (writeSet.contains(from) || writeSet.contains(to)) {
                    sb.append("WAIT\n");
                    writeSet.clear();
                    readSet.clear();
                }

                sb.append(input).append("\n");
                readSet.add(from);
                writeSet.add(to);
            } else if (op.equals("READ")) {
                String readData = st.nextToken();

                if (writeSet.contains(readData)) {
                    sb.append("WAIT\n");
                    writeSet.clear();
                    readSet.clear();
                }

                sb.append(input).append("\n");
                readSet.add(readData);
            }
        }

        System.out.println(sb);
    }
}
