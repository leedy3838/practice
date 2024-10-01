package programmers_172927;

import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        Queue<Node> q = new PriorityQueue<>();
        
        int pickCnt = picks[0] + picks[1] + picks[2];
        
        int diaCnt = 0, ironCnt = 0, stoneCnt = 0;
        for (String mineral : minerals) {
            if (mineral.equals("diamond")) {
                diaCnt++;
            } else if (mineral.equals("iron")) {
                ironCnt++;
            } else if (mineral.equals("stone")) {
                stoneCnt++;
            }
            
            if (diaCnt + ironCnt + stoneCnt == 5) {
                q.offer(new Node(diaCnt, ironCnt, stoneCnt));
                diaCnt = 0;
                ironCnt = 0;
                stoneCnt = 0;
                pickCnt--;
                
                //어차피 못 캐는 경우 추가 X
                if (pickCnt == 0)
                    break;
            }
        }
        
        if (diaCnt != 0 || ironCnt != 0 || stoneCnt != 0) {
            q.offer(new Node(diaCnt, ironCnt, stoneCnt));
        }
        
        int answer = 0;
        while (!q.isEmpty() && (picks[0] + picks[1] + picks[2] != 0)) {
            Node node = q.poll();
            
            if (picks[0] != 0) {
                picks[0]--;
                answer += node.dia + node.iron + node.stone;
            } else if (picks[1] != 0) {
                picks[1]--;
                answer += node.dia * 5 + node.iron + node.stone;
            } else {
                picks[2]--;
                answer += node.dia * 25 + node.iron * 5 + node.stone;
            }
        }
        
        return answer;
    }
    
    static class Node implements Comparable<Node> {
        int dia, iron, stone;
        
        public Node(int dia, int iron, int stone) {
            this.dia = dia;
            this.iron = iron;
            this.stone = stone;
        }
        
        public int compareTo(Node node) {
            if (node.dia == this.dia) {
                return node.iron - this.iron;
            }
            return node.dia - this.dia;
        }
    }
}