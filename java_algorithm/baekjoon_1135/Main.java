package baekjoon_1135;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        //직속 상사의 idx
        int[] parentIdx = new int[N];
        //직속 부하 직원의 총 수
        int[] childNum = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int person = 0; person<N; person++){
            int parent = Integer.parseInt(st.nextToken());

            parentIdx[person] = parent;

            //루트 노드
            if(parent == -1)
                continue;

            childNum[parent]++;
        }

        Queue<Person> q = new PriorityQueue<>();
        //루트 제외 다른 직원 전부 삽입
        for(int i = 1; i<N; i++)
            if(childNum[i] == 0)
                q.offer(new Person(i, parentIdx[i], childNum[i]));

        //현재 이 사람이 몇 명의 직속 부하에게 소식을 전했는가
        int[] nowCnt = new int[N];
        int[] time = new int[N];
        //이미 방문한 사람이면 검사를 진행하지 않는다.
        //상사는 부하보다 index가 앞에 있기 때문이다.
        boolean[] isVisit = new boolean[N];

        while(!q.isEmpty()){
            Person person = q.poll();

            //루트에 도달
            if(person.parentIdx == -1)
                break;

            if(!isVisit[person.idx]) {
                nowCnt[person.parentIdx]++;
                isVisit[person.idx] = true;

                if(time[person.parentIdx] < time[person.idx]+nowCnt[person.parentIdx]) {
                    time[person.parentIdx] = time[person.idx]+nowCnt[person.parentIdx];

                    //직속 상사를 q에 삽입
                    q.offer(new Person(person.parentIdx, parentIdx[person.parentIdx], time[person.parentIdx]));
                }
            }
        }

        System.out.println(time[0]);
    }

    static class Person implements Comparable<Person>{
        int idx, parentIdx, time;

        public Person(int idx, int parentIdx, int time) {
            this.idx = idx;
            this.parentIdx = parentIdx;
            this.time = time;
        }

        //부모 index의 내림차순으로 정렬
        //부모가 같을 경우 시간의 내림차순으로 정렬
        @Override
        public int compareTo(Person person) {
            if(person.parentIdx == this.parentIdx)
                return person.time - this.time;
            return person.parentIdx - this.parentIdx;
        }
    }
}
