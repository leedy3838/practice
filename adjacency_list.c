#include<stdio.h>
#include<stdlib.h>
#define MAX_VERTEX 30

//인접 리스트의 노드 구조를 구조체로 정의
typedef struct graphNode{
	int vertex;
	struct graphNode* link;
} graphNode;

//그래프를 인접 리스트로 표현하기 위한 구조체 정의
typedef struct graphType{
	int n;
	graphNode* adjList_H[MAX_VERTEX];
} graphType;

//공백 그래프를 생성하는 연산
void createGraph(graphType* g){
	g->n = 0;
	for(int i = 0; i<MAX_VERTEX; i++){
		g->adjList_H[i] = NULL;
	}
	return;
}

//그래프 g에 정점 v를 삽입하는 연산
void insertVertex(graphType* g, int v){
	if(g->n+1>MAX_VERTEX){
		printf("삽입할 수 없습니다.\n");
		return;
	}
	g->n++;
}

//그래프 g에 간선 (u, v)를 삽입하는 연산
void insertEdge(graphType* g, int u, int v){
	if(u>=g->n||v>=g->n){
		printf("삽입할 수 없습니다.\n");
		return;
	}
	graphNode* node = (graphNode*)malloc(sizeof(graphNode));
	node->vertex = v;
	node->link = g->adjList_H[u];
	g->adjList_H[u] = node;
}

//그래프 g의 각 정점에 대한 인접 리스트를 출력하는 연산
void print_adjList(graphType* g){
	for(int i = 0; i<g->n; i++){
		graphNode* p = g->adjList_H[i];
		printf("정점 %c의 인접 리스트", i + 65);
		while(p){
			printf("%c ",p->vertex + 65);
			p = p->link;
		}
		printf("\n");
	}
	return;
}

void main(){
	graphType *G1, *G2, *G3, *G4;
	G1 = (graphType*)malloc(sizeof(graphType));
	G2 = (graphType*)malloc(sizeof(graphType));
	G3 = (graphType*)malloc(sizeof(graphType));
	G4 = (graphType*)malloc(sizeof(graphType));
	createGraph(G1); createGraph(G2); createGraph(G3); createGraph(G4);
	
	//그래프 G1
	for(int i = 0; i<4; i++)
		insertVertex(G1, i);
	insertEdge(G1, 0, 3);
	insertEdge(G1, 0, 1);
	insertEdge(G1, 1, 3);
	insertEdge(G1, 1, 2);
	insertEdge(G1, 1, 0);
	insertEdge(G1, 2, 3);
	insertEdge(G1, 2, 1);
	insertEdge(G1, 3, 2);
	insertEdge(G1, 3, 1);
	insertEdge(G1, 3, 0);
	printf("\n\n G1의 인접 리스트\n");
	print_adjList(G1);
	
	//그래프 G2
	for(int i = 0; i<3; i++)
		insertVertex(G2, i);
	insertEdge(G2, 0, 2);
	insertEdge(G2, 0, 1);
	insertEdge(G2, 1, 2);
	insertEdge(G2, 1, 0);
	insertEdge(G2, 2, 1);
	insertEdge(G2, 2, 0);
	printf("\n\n G2의 인접 리스트\n");
	print_adjList(G2);
	
	//그래프 G3
	for(int i = 0; i<4; i++)
		insertVertex(G3, i);
	insertEdge(G3, 0, 3);
	insertEdge(G3, 0, 1);
	insertEdge(G3, 1, 3);
	insertEdge(G3, 1, 2);
	insertEdge(G3, 2, 3);
	printf("\n\n G3의 인접 리스트\n");
	print_adjList(G3);
	
	//그래프 G4
	for(int i = 0; i<3; i++)
		insertVertex(G4, i);
	insertEdge(G4, 0, 2);
	insertEdge(G4, 0, 1);
	insertEdge(G4, 1, 2);
	insertEdge(G4, 1, 0);
	printf("\n\n G4의 인접 리스트\n");
	print_adjList(G4);
	
	getchar();
}