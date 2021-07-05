#include<stdio.h>
#include<stdlib.h>
#define RADIX 10
#define DIGIT 2

typedef int element;

typedef struct QNode{
	element data;
	struct QNode* link;
}QNode;

typedef struct{
	QNode *front, *rear;
}LQueueType;

LQueueType* createLinkedQueue(){
	LQueueType* LQ;
	LQ = (LQueueType*)malloc(sizeof(LQueueType));
	LQ->front = NULL;
	LQ->rear = NULL;
	return LQ;
}

int isEmpty(LQueueType* LQ){
	if(LQ->front == NULL)
		return 1;
	return 0;
}

void enQueue(LQueueType* LQ, element item){
	QNode* newNode = (QNode*)malloc(sizeof(QNode));
	newNode->data = item;
	newNode->link = NULL;
	if(LQ->front == NULL){
		LQ->front = newNode;
		LQ->rear = newNode;
	}
	else{
		LQ->rear->link = newNode;
		LQ->rear = newNode;
	}
}

element deQueue(LQueueType* LQ){
	QNode* node = LQ->front;
	element item;
	if(isEmpty(LQ))	return 0;
	else{
		item = node->data;
		LQ->front = LQ->front->link;
		if(LQ->front == NULL)
			LQ->rear = NULL;
		free(node);
		return item;
	}
}

//배열 a에 있는 n개 원소에 대해 기수 정렬을 수행하는 연산
void radixSort(element a[], int n){
	int i, bucket, d, factor = 1;
	
	LQueueType *Q[RADIX];
	for(bucket = 0; bucket<RADIX; bucket++)
		Q[bucket] = createLinkedQueue();
	
	for(d = 0; d<DIGIT; d++){
		for(i = 0; i<n; i++)
			enQueue(Q[(a[i] / factor) % RADIX], a[i]);
		for(i = 0, bucket = 0; bucket < RADIX; bucket++)
			while(!isEmpty(Q[bucket])) a[i++] = deQueue(Q[bucket]);
		printf("\n\n %d 단계 : ", d + 1);
		for(i = 0; i<n; i++)	printf(" %d", a[i]);
		
		factor = factor * RADIX;
	}
}

void main(){
	element list[8] = {69, 10, 30, 2, 16, 8, 31, 22};
	int size = 8;
	printf("\n <<<<<<<< 기수 정렬 수행 >>>>>>>>");
	radixSort(list, size);
	
	getchar();
}