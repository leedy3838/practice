package baekjoon_4386;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Star[] star = new Star[N];
        boolean[] isVisit = new boolean[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            star[i] = new Star(x, y);
        }

        Queue<Node> q = new PriorityQueue<>();
        isVisit[0] = true;

        for(int i = 1; i < N; i++)
            q.offer(new Node(i, starDist(star[0], star[i])));

        double sum = 0;

        while(!q.isEmpty()){
            Node node = q.poll();

            if(isVisit[node.index])
                continue;

            sum += node.dist;
            isVisit[node.index] = true;

            for(int i = 1; i < N; i++){
                if(isVisit[i])
                    continue;

                q.offer(new Node(i, starDist(star[node.index], star[i])));
            }
        }

        System.out.printf("%.2f", sum);
    }

    private static double starDist(Star star1, Star star2){
        return Math.sqrt(Math.pow(star1.x - star2.x, 2) + Math.pow(star1.y - star2.y, 2));
    }

    private static class Star{
        double x, y;

        public Star(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Node implements Comparable<Node>{
        int index;
        double dist;

        public Node(int index, double dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node node) {
            return Double.compare(this.dist, node.dist);
        }
    }
}
