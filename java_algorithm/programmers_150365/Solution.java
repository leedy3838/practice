package programmers_150365;

import java.util.*;

class Solution {
    private int n, m, r, c, k;
    
    private String answer = "";
    private char[] charArr;
    private boolean stop = false;
    
    //하, 좌, 우, 상
    private final int[] dR = new int[]{1, 0, 0, -1};
    private final int[] dC = new int[]{0, -1, 1, 0};
    private final char[] ch = new char[]{'d', 'l', 'r', 'u'};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        this.k = k;
        
        if (x == r && y == c || !canArrival(x, y, 0)) {
            return "impossible";
        }
        
        charArr = new char[k];
        
        dfs(x, y, 0);
        
        return answer;
    }
    
    private void dfs(int row, int col, int idx) {
        if (idx == k) {
            if (row == r && col == c){
                answer = String.valueOf(charArr);
                stop = true;
            }
            
            return;
        }
        
        if (!canArrival(row, col, idx) || idx > k) {
            return;
        }
        
        if (stop)
            return;
        
        for (int i = 0; i < 4; i++) {
            int dr = row + dR[i];
            int dc = col + dC[i];
            
            if (dr <= 0 || dc <= 0 || dr > n || dc > m)
                continue;
            
            charArr[idx] = ch[i];
            dfs(dr, dc, idx + 1);
        }
    }
    
    private boolean canArrival(int row, int col, int idx) {
        // 현재 위치에서 목표까지의 최소 거리
        int minDistance = Math.abs(row - r) + Math.abs(col - c);
        if (idx + minDistance > k || (k - idx) % 2 != minDistance % 2) {
            return false;
        } else {
            return true;
        }
    }
}