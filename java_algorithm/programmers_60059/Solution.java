package programmers_60059;

class Solution {
    private int[][] key, lock;
    private int M, N;
    
    public boolean solution(int[][] key, int[][] lock) {
        this.key = key;
        this.lock = lock;
        this.M = key.length;
        this.N = lock.length;
        
        for (int i = 0; i < 4; i++) {
            for (int dR = -M; dR <= N + M; dR++) {
                for (int dC = -M; dC <= N + M; dC++) {
                    if(checkKey(dR, dC)) {
                        return true;
                    }
                }
            }
            
            rotate();
        }
        
        return false;
    }
    
    private boolean checkKey(int dR, int dC) {
        int[][] tempLock = new int[N][N];

        // 자물쇠에 키를 적용한 상태를 임시 배열에 복사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tempLock[i][j] = lock[i][j];
            }
        }

        // 키를 자물쇠에 적용
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                int lockR = i + dR;
                int lockC = j + dC;

                if (lockR < 0 || lockR >= N || lockC < 0 || lockC >= N)
                    continue;
                
                if (key[i][j] == 1 && tempLock[lockR][lockC] == 1) {
                    return false; // 돌기가 만나면 안됨
                }
                
                if (key[i][j] == 1 && tempLock[lockR][lockC] == 0) {
                    tempLock[lockR][lockC] = 1; // 홈을 채움
                }
                
            }
        }

        // 모든 홈이 채워졌는지 체크
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tempLock[i][j] == 0) {
                    return false; // 비어있는 곳이 있으면 실패
                }
            }
        }

        return true; // 자물쇠가 열림
    }
    
    //시계 방향으로 회전
    private void rotate() {
        int[][] newKey = new int[M][M];
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                newKey[i][j] = key[M - 1 - j][i];
            }
        }
        
        key = newKey;
    }
}