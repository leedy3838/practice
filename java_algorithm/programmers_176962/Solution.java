package programmers_176962;

import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        
        Queue<Node> q = new PriorityQueue<>();
        for (String[] plan : plans) {
            String name = plan[0];
            
            String[] time = plan[1].split(":");
            int start = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            
            int cost = Integer.parseInt(plan[2]);
            
            q.offer(new Node(start, cost, name));
        }
        
        Stack<Node> tmp = new Stack();
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            
            if (q.isEmpty()) {  //마지막 원소의 경우
                answer.add(node.name);
            } else {
                int nowEndTime = node.start + node.cost;
                int nextStartTime = q.peek().start;
                
                if (nowEndTime <= nextStartTime) {
                    answer.add(node.name);
                    
                    while (!tmp.isEmpty() && nowEndTime + tmp.peek().cost <= nextStartTime) {
                        Node tmpNode = tmp.pop();
                        
                        nowEndTime += tmpNode.cost;
                        answer.add(tmpNode.name);
                    }
                    
                    //stack에 있는 일을 전부는 아니고 조금 처리
                    if (!tmp.isEmpty() && nowEndTime < nextStartTime) {
                        Node tmpNode = tmp.pop();
                        
                        tmp.push(new Node(0, nowEndTime + tmpNode.cost - nextStartTime, tmpNode.name));
                    }
                } else {
                    tmp.push(new Node(0, nowEndTime - nextStartTime, node.name));
                }
            }
        }
        
        while (!tmp.isEmpty()) {
            answer.add(tmp.pop().name);
        }
        
        return answer.stream().toArray(String[]::new);
    }
    
    static class Node implements Comparable<Node> {
        int start, cost;
        String name;
        
        public Node(int start, int cost, String name) {
            this.start = start;
            this.cost = cost;
            this.name = name;
        }
        
        public int compareTo(Node node) {
            return this.start - node.start;
        }
    }
}