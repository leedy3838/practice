package baekjoon_2141;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<Node> l = new ArrayList<>();
        long sum = 0;
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            long len = Long.parseLong(st.nextToken());
            long people = Long.parseLong(st.nextToken());

            sum += people;

            l.add(new Node(len, people));
        }

        Collections.sort(l, new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                return (int)(node.len - t1.len);
            }
        });

        long answer = 0;
        for(int i = 0; i<N; i++){
            answer += l.get(i).people;

            if(answer >= (sum+1)/2){
                System.out.println(l.get(i).len);
                return;
            }
        }
    }
}
class Node{
    long len, people;

    public Node(long len, long people) {
        this.len = len;
        this.people = people;
    }
}