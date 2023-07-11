package baekjoon_2140;

import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[][] intArr;

    public static void main(String[] args) throws IOException{

        int N = Integer.parseInt(br.readLine());

        //intArr의 요소가 5면 지뢰, -1이면 지뢰가 아님, 1이면 테두리를 의미
        intArr = new int[N][N];

        markSide(N);

        boomSet(N);

        int boomCnt = 0;

        for(int i = 0; i<N; i++)
            for(int j = 0; j<N; j++)
                if (intArr[i][j] == 0 || intArr[i][j] == 5)
                    boomCnt++;

        System.out.println(boomCnt);
    }

    //테두리를 표시
    static void markSide(int N){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                //위 아래 테두리
                if(i == 0 || i == N-1)
                    intArr[i][j] = 1;

                //양 옆 테두리
                if(j == 0 || j == N-1)
                    intArr[i][j] = 1;
            }
        }
    }

    //지뢰를 심는 함수
    static void boomSet(int N) throws IOException{

        //각 칸을 지뢰로 채우기
        for(int row = 0; row<N; row++){
            String[] input = br.readLine().split("");

            for(int col = 0; col<N; col++){
                String inputStr = input[col];
                //정점 주변에 이미 있는 지뢰의 수
                int nearBoom = 0;

                //테두리의 입력이 아니기 때문에 continue
                if(inputStr.equals("#"))
                    continue;

                int boomCnt = Integer.parseInt(inputStr);

                for(int i = 0; i<8; i++){
                    int dR = row + dr[i];
                    int dC = col + dc[i];

                    if(dR<0||dC<0||dR>=N||dC>=N)
                        continue;
                    if(intArr[dR][dC] == 1)
                        continue;

                    if(intArr[dR][dC] == 5)
                        nearBoom++;
                }

                //주변의 폭탄이 있어야 하는 폭탄보다 적은 경우
                if(nearBoom < boomCnt){
                    for(int i = 0; i<8; i++){
                        int dR = row + dr[i];
                        int dC = col + dc[i];

                        if(dR<0||dC<0||dR>=N||dC>=N)
                            continue;

                        //폭탄을 주입
                        if(intArr[dR][dC] == 0) {
                            intArr[dR][dC] = 5;
                            break;
                        }
                    }
                }
                //주변에 있어야 하는 폭탄이 다 있는 경우
                else{
                    for(int i = 0; i<8; i++){
                        int dR = row + dr[i];
                        int dC = col + dc[i];

                        if(dR<0||dC<0||dR>=N||dC>=N)
                            continue;

                        //폭탄이 없음
                        if(intArr[dR][dC] == 0) {
                            intArr[dR][dC] = -1;
                            break;
                        }
                    }
                }
            }
        }
    }
}
