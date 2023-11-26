package baekjoon_2754;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String grade = br.readLine();
        double gradePoint = 0;

        if (grade.equals("A+"))
            gradePoint = 4.3;
        else if (grade.equals("A0"))
            gradePoint = 4.0;
        else if (grade.equals("A-"))
            gradePoint = 3.7;
        else if (grade.equals("B+"))
            gradePoint = 3.3;
        else if (grade.equals("B0"))
            gradePoint = 3.0;
        else if (grade.equals("B-"))
            gradePoint = 2.7;
        else if (grade.equals("C+"))
            gradePoint = 2.3;
        else if (grade.equals("C0"))
            gradePoint = 2.0;
        else if (grade.equals("C-"))
            gradePoint = 1.7;
        else if (grade.equals("D+"))
            gradePoint = 1.3;
        else if (grade.equals("D0"))
            gradePoint = 1.0;
        else if (grade.equals("D-"))
            gradePoint = 0.7;
        else if (grade.equals("F"))
            gradePoint = 0;

        System.out.printf("%.1f", gradePoint);
    }
}
