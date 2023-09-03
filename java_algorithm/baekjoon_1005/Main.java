package baekjoon_1005;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] parentNum, timeArr, maxParentTime;
    static List<List<Integer>> l;
    public static void main(String[] args) throws IOException{

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            sb.append(constructBuilding(firstSetting())).append("\n");
        }

        System.out.print(sb);
    }

    private static int firstSetting() throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        parentNum = new int[N+1];
        timeArr = new int[N+1];
        maxParentTime = new int[N+1];

        l = new ArrayList<>();
        for(int i = 0; i <= N; i++)
            l.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++)
            timeArr[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            l.get(parent).add(child);
            parentNum[child]++;
        }

        return Integer.parseInt(br.readLine());
    }

    private static int constructBuilding(int target){

        Queue<Node> q = new LinkedList<>();
        for(int i = 0; i < parentNum.length; i++){
            if(parentNum[i] == 0){
                if(i == target){
                    return timeArr[i];
                }

                q.offer(new Node(i, 0));
            }
        }

        while(!q.isEmpty()){

            Node node = q.poll();

            for(Integer childIdx : l.get(node.idx)){
                parentNum[childIdx]--;
                maxParentTime[childIdx] = Math.max(maxParentTime[childIdx], node.time + timeArr[node.idx]);

                if(parentNum[childIdx] == 0){
                    if(childIdx == target){
                        return maxParentTime[childIdx] + timeArr[childIdx];
                    }

                    q.offer(new Node(childIdx, maxParentTime[childIdx]));
                }
            }
        }

        return 0;
    }

    private static class Node{
        int idx, time;

        public Node(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }
}
