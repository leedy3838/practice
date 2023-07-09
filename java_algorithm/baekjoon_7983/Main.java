package baekjoon_7983;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        //일정을 담아두는 queue
        Queue<Date> q = new PriorityQueue<>();

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());

            q.offer(new Date(time, endDay));
        }

        int restDayEnd = Integer.MAX_VALUE;

        while(!q.isEmpty()){
            Date newDate = q.poll();

            int endDay = newDate.endDay;
            int time = newDate.time;

            if(endDay < restDayEnd)
                restDayEnd = endDay - time;
            else
                restDayEnd -= time;
        }

        System.out.println(restDayEnd);
    }
}
class Date implements Comparable<Date>{
    int time, endDay;

    public Date(int time, int endDay){
        this.time = time;
        this.endDay = endDay;
    }

    @Override
    public int compareTo(Date a){
        if(this.endDay == a.endDay)
            return a.time - this.time;
        return a.endDay - this.endDay;
    }
}