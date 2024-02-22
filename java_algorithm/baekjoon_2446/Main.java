package baekjoon_2446;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {

            sb.append(" ".repeat(i));
            sb.append("*".repeat(Math.max(0, (2 * N - 1) - (2 * i))));
			sb.append('\n');
		}

		for (int i = 0; i < N - 1; i++) {

            sb.append(" ".repeat(Math.max(0, (N - 1) - i - 1)));
            sb.append("*".repeat(Math.max(0, 3 + 2 * i)));
			sb.append('\n');
		}

		System.out.println(sb);
	}
}