#include<stdio.h>
#include<limits.h>
#define MAX_VERTICES 5
#define INF 10000

int weight[MAX_VERTICES][MAX_VERTICES] = {
	{0, 10, 5, INF, INF},
	{INF, 0, 2, 1, INF},
	{INF, 3, 0, 9, 2},
	{INF, INF, INF, 0, 4},
	{7, INF, INF, 6, 0},
};

int A[MAX_VERTICES][MAX_VERTICES];

//최단 경로를 구하는 과정을 출력하는 연산
void printStep(int step){
	printf("\n A%d : ", step);
	for(int i = 0; i<MAX_VERTICES; i++){
		printf("\t");
		for(int j = 0; j<MAX_VERTICES; j++){
			if(A[i][j] == INF)
				printf("%4c", '*');
			else
				printf("%4d", A[i][j]);
		}
		printf("\n\n");
	}
}

void Floyd_shortestPath(int n){
	int step = -1;
	for(int v = 0; v<n; v++)
		for(int w = 0; w<n; w++)
			A[v][w] = weight[v][w];
	
	printStep(step);
	
	for(int k = 0; k<n; k++){
		for(int v = 0; v<n; v++)
			for(int w = 0; w<n; w++)
				if(A[v][k] + A[k][w] < A[v][w])
					A[v][w] = A[v][k] + A[k][w];
		printStep(++step);
	}
}

int main(){
	printf("\n ************ 가중치 인접 행렬 ************\n\n");
	for(int i = 0; i < MAX_VERTICES; i++){
		for (int j = 0; j < MAX_VERTICES; j++){
			if(weight[i][j] == INF)
				printf("%4c", '*');
			else
				printf("%4d", weight[i][j]);
		}
		printf("\n\n");
	}
	printf("\n *********** 플로이드 최단 경로 구하기 ***********\n\n");
	Floyd_shortestPath(MAX_VERTICES);
	
	getchar();
}
