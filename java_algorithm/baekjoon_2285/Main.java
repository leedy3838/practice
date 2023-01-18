package baekjoon_2285;

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

            int x = Integer.parseInt(st.nextToken());
            long personNum = Integer.parseInt(st.nextToken());

            sum += personNum;
            l.add(new Node(x, personNum));
        }

        Collections.sort(l);

        long personSum = 0;
        for(int i = 0; i<N; i++){
            Node a = l.get(i);

            personSum += a.personNum;

            if(personSum >= (sum+1)/2){
                System.out.println(a.x);
                return;
            }
        }
    }
}
class Node implements Comparable<Node>{
    int x;
    long personNum;

    public Node(int x, long personNum) {
        this.x = x;
        this.personNum = personNum;
    }

    @Override
    public int compareTo(Node a){
        return (int)(this.x - a.x);
    }
}
