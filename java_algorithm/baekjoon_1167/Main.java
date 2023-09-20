package baekjoon_1167;

import java.io.*;
import java.util.*;

public class Main {

    static int V, maxDist = 0, finalV = 0;
    static boolean[] isVisit;
    static List<List<Node>> l = new ArrayList<>();

    public static void main(String[] args){

        makeTree();
        findLongest();
        findMaxLen();
    }

    private static void makeTree(){

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            V = Integer.parseInt(br.readLine());

            isVisit = new boolean[V + 1];
            for(int i = 0; i <= V; i++){
                l.add(new ArrayList<>());
            }

            for(int i = 1; i <= V; i++){
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());

                while(true){
                    int to = Integer.parseInt(st.nextToken());

                    if(to == -1){
                        break;
                    }

                    int dist = Integer.parseInt(st.nextToken());

                    l.get(from).add(new Node(to, dist));
                }
            }
        } catch (IOException e){
            e.printStackTrace(System.out);
        }
    }

    private static void findLongest(){

        isVisit[1] = true;
        findLongest(1, 0);
        isVisit[1] = false;
    }

    private static void findLongest(int vertex, int dist){

        for (Node node : l.get(vertex)) {

            if(isVisit[node.vertex]) {
                continue;
            }

            isVisit[node.vertex] = true;
            findLongest(node.vertex, dist + node.dist);
            isVisit[node.vertex] = false;
        }

        if(dist > maxDist){
            maxDist = dist;
            finalV = vertex;
        }
    }

    private static void findMaxLen(){

        isVisit[finalV] = true;
        findLongest(finalV, 0);
        isVisit[finalV] = false;

        System.out.println(maxDist);
    }

    private static class Node{
        int vertex, dist;

        public Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }
}
