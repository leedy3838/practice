#include<stdio.h>
#include<limits.h>
#define TRUE 1
#define FALSE 0
#define MAX_VERTICES 5
#define INF 10000

int weight[MAX_VERTICES][MAX_VERTICES] = {
	{0, 10, 5, INF, INF},
	{INF, 0, 2, 1, INF},
	{INF, 3, 0, 9, 2},
	{INF, INF, INF, 0, 4},
	{7, INF, INF, 6, 0},
};
int distance[MAX_VERTICES];	//시작 정점에서 시작하는 최단 경로 길이 저장
int S[MAX_VERTICES];		// 정점의 집합 S

//최단 경로를 갖는 다음 정점을 찾는 연산
int nextVertex(int n){
	int min = INT_MAX;
	int minPos = -1;
	for(int i = 0; i<n; i++){
		if((distance[i]<min) && !S[i]){
			min = distance[i];
			minPos = i;
		}
	}
	return minPos;
}

//최단 경로 구하는 과정을 출력하는 연산
int printStep(int step){
	printf("\n %3d 단계 : S = {", step);
	for(int i = 0; i< MAX_VERTICES; i++)
		if(S[i] == TRUE)
			printf("%3c", i + 65);
	if(step<1)
		printf(" }\t\t\t");
	else if(step<4)
		printf(" }\t\t");
	else
		printf(" }\t");
	printf(" distance : [");
	for(int i = 0; i < MAX_VERTICES; i++){
		if(distance[i] == INF)
			printf("%4c",'*');
		else
			printf("%4d", distance[i]);
	}
	printf("%4c", ']');
	return ++step;
}

void Dijkstra_shortestPath(int start, int n){
	for(int i = 0; i < n; i++){
		distance[i] = weight[start][i];
		S[i] = FALSE;
	}
	
	S[start] = TRUE;
	distance[start] = 0;
	
	int step = printStep(0);
	
	for(int i = 0; i<n -1; i++){
		int u = nextVertex(n);
		S[u] = TRUE;
		for(int w = 0; w < n; w++){
			if(!S[w])
				if(distance[u] + weight[u][w] < distance[w])
					distance[w] = distance[u] + weight[u][w];
		}
		step = printStep(step);
	}
}

void main(){
	printf("\n ******** 가중치 인접 행렬 *********\n\n");
	for(int i = 0; i < MAX_VERTICES; i++){
		for(int j = 0; j < MAX_VERTICES; j++){
			if(weight[i][j] == INF)
				printf("%4c", '*');
			else
				printf("%4d", weight[i][j]);
		}
		printf("\n\n");
	}
	printf("\n ************* 다익스트라 최단 경로 구하기 *************\n\n");
	Dijkstra_shortestPath(0, MAX_VERTICES);
	
	getchar();
}
