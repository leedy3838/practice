package baekjoon_25206;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        double sum = 0;
        int pointSum = 0;

        for (int i = 0; i < 20; i++) {
            st = new StringTokenizer(br.readLine());

            st.nextToken();
            double point = Double.parseDouble(st.nextToken());
            String grade = st.nextToken();
            double gradePoint = 0;

            if (grade.equals("A+"))
                gradePoint = 4.5;
            else if (grade.equals("A0"))
                gradePoint = 4.0;
            else if (grade.equals("B+"))
                gradePoint = 3.5;
            else if (grade.equals("B0"))
                gradePoint = 3.0;
            else if (grade.equals("C+"))
                gradePoint = 2.5;
            else if (grade.equals("C0"))
                gradePoint = 2.0;
            else if (grade.equals("D+"))
                gradePoint = 1.5;
            else if (grade.equals("D0"))
                gradePoint = 1.0;
            else if (grade.equals("F"))
                gradePoint = 0;
            else if (grade.equals("P"))
                point = 0;

            sum += point * gradePoint;
            pointSum += (int) point;
        }

        System.out.printf("%.6f", sum / pointSum);
    }
}
