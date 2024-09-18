package programmers_12979;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        for (int i = 0; i < stations.length; i++) {
            int station = stations[i];
            
            if (i == 0) {
                int addV = (station - w - 1) / (2 * w + 1);
                if ((station - w - 1) % (2 * w + 1) > 0)
                    addV++;
                
                answer += addV;
            }

            if (i == stations.length - 1) {
                break;
            }
            
            int nextStation = stations[i + 1];

            if (nextStation - station - 1 > 2 * w) {
                int gap = nextStation - station - 1 - 2 * w;

                int addV = gap / (2 * w + 1);
                if (gap % (2 * w + 1) > 0)
                    addV++;

                answer += addV;
            }
            
        }
        
        if (stations[stations.length - 1] + w < n) {
            int gap = n - (stations[stations.length - 1] + w);
                
            int addV = gap / (2 * w + 1);
            if (gap % (2 * w + 1) > 0)
                addV++;

            answer += addV;
        }

        return answer;
    }
}