#include<stdio.h>
#include<stdlib.h>
#define MAX_VERTEX 10
#define FALSE 0
#define TRUE 1

//그래프에 대한 인접 리스트의 노드 구조 정의
typedef struct graphNode{
	int vertex;
	struct graphNode* link;
} graphNode;

typedef struct graphType{
	int n;
	graphNode* adjList_H[MAX_VERTEX];
	int visited[MAX_VERTEX];
} graphType;

typedef int element;

typedef struct stackNode{
	element data;
	struct stackNode *link;
} stackNode;

stackNode* top;

//스택이 공백인지 확인하는 연산
int isEmpty(){
	if(top == NULL)
		return 1;
	return 0;
}


void push(int item){
	stackNode* temp = (stackNode*)malloc(sizeof(stackNode));
	temp->data = item;
	temp->link = top;
	top = temp;
}

int pop(){
	int item;
	item = top->data;
	stackNode* node = top;
	if(isEmpty()){
		printf("stack is empty\n");
		return 0;
	}
	top = top->link;
	free(node);
	return item;
}

//깊이 우선 탐색을 위해 초기 공백 그래프를 생성하는 연산
void createGraph(graphType* g){
	g->n = 0;
	for(int i = 0; i<MAX_VERTEX; i++){
		g->adjList_H[i] = NULL;
		g->visited[i] = 0;
	}
	return;
}

//그래프 g에 정점 v를 삽입하는 연산
void insertVertex(graphType* g, int v){
	if(g->n+1>MAX_VERTEX){
		printf("grapth is full\n");
		return;
	}
	g->n++;
	return;
}

//그래프 g에 간선 (u, v)를 삽입하는 연산
void insertEdge(graphType* g, int u, int v){
	if(u>=g->n||v>=g->n){
		printf("그래프의 범위를 넘었습니다.\n");
		return;
	}
	graphNode* node = (graphNode*)malloc(sizeof(graphNode));
	node->vertex = v;
	node->link = g->adjList_H[u];
	g->adjList_H[u] = node;
	return;
}

//그래프 g의 각 정점에 대한 인접 리스트를 출력하는 연산
void print_adjList(graphType* g){
	for(int i = 0; i<g->n; i++){
		graphNode* node = g->adjList_H[i];
		printf("%c의 인접 리스트 : ",i+65);
		while(node != NULL){
			printf("%c ",node->vertex + 65);
			node = node->link;
		}
		printf("\n");
	}
}

//그래프 g에서 정점 v에 대한 깊이 우선 탐색 연산
void DFS_adjList(graphType* g, int v){
	graphNode* w;
	top = NULL;
	push(v);
	g->visited[v] = TRUE;
	printf("%c ",v + 65);
	while(!isEmpty()){
		v = pop();
		w = g->adjList_H[v];
		while(w){
			if(!g->visited[w->vertex]){
				if(isEmpty()) push(v);
				push(w->vertex);
				g->visited[w->vertex] = TRUE;
				printf("%c ",w->vertex + 65);
				v = w->vertex;
				w = g->adjList_H[v];
			}
			else
				w = w->link;
		}
	}
}

void main(){
	int i;
	graphType *G9;
	G9 = (graphType*)malloc(sizeof(graphType));
	createGraph(G9);
	//그래프 G9 구성
	for(i = 0; i<7; i++)
		insertVertex(G9, i);
	insertEdge(G9, 0, 2);
	insertEdge(G9, 0, 1);
	insertEdge(G9, 1, 4);
	insertEdge(G9, 1, 3);
	insertEdge(G9, 1, 0);
	insertEdge(G9, 2, 4);
	insertEdge(G9, 2, 0);
	insertEdge(G9, 3, 6);
	insertEdge(G9, 3, 1);
	insertEdge(G9, 4, 6);
	insertEdge(G9, 4, 2);
	insertEdge(G9, 4, 1);
	insertEdge(G9, 5, 6);
	insertEdge(G9, 6, 5);
	insertEdge(G9, 6, 4);
	insertEdge(G9, 6, 3);
	printf("\n G9의 인접 리스트\n");
	print_adjList(G9);
	
	printf("\n\n/////////////////////\n\n깊이 우선 탐색 >> ");
	DFS_adjList(G9, 0);
	getchar();
}