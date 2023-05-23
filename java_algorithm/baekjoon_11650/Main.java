package baekjoon_11650;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Node> l = new LinkedList<>();

        while(N-->0){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            l.add(new Node(x, y));
        }

        Collections.sort(l);

        for (Node node : l)
            System.out.println(node.x + " " + node.y);
    }
}
class Node implements Comparable<Node>{
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Node a){
        if(this.x == a.x)
            return this.y - a.y;
        return this.x - a.x;
    }
}