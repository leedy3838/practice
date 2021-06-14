#include<stdio.h>
#include<stdlib.h>

typedef char element;
typedef struct QNode{
	element data;
	struct QNode *link;
}QNode;

typedef struct{
	QNode *front, *rear;
}LQueueType;

//공백 연결 큐를 생성하는 연산
LQueueType* createLinkedQueue(){
	LQueueType* LQ = (LQueueType*)malloc(sizeof(LQueueType));
	LQ->front = NULL;
	LQ->rear = NULL;
	return LQ;
}

//연결 큐가 공백 상태인지 검사하는 연산
int isEmpty(LQueueType* LQ){
	if(LQ->front == NULL)
		return 1;
	return 0;
}

//연결 큐의 rear에 원소를 삽입하는 연산
void enQueue(LQueueType* LQ, element item){
	QNode* node = (QNode*)malloc(sizeof(QNode));
	node->data = item;
	node->link = NULL;
	if(isEmpty(LQ)){
		LQ->front = node;
		LQ->rear = node;
	}
	else{
		LQ->rear->link = node;
		LQ->rear = LQ->rear->link;
	}
	return;
}

//연결 큐에서 원소를 삭제하고 반환하는 연산
element deQueue(LQueueType* LQ){
	QNode* node = LQ->front;
	element data;
	if(isEmpty(LQ)){
		printf("LQ is empty\n");
		return 0;
	}
	else{
		data = LQ->front->data;
		LQ->front = LQ->front->link;
		if(LQ->front == NULL)
			LQ->rear == NULL;
		free(node);
	}
	return data;
}

//연결 큐에서 원소를 검색하는 연산
element peek(LQueueType* LQ){
	return LQ->front->data;
}

//연결 큐의 원소를 출력하는 연산
void printLQ(LQueueType* LQ){
	QNode* node = LQ->front;
	while(node){
		printf("%c ",node->data);
		node = node->link;
	}
	return;
}

void main(){
	LQueueType* LQ = createLinkedQueue();
	element data;
	printf("\n ***** 연결 큐 연산 ***** \n");
	printf("\n 삽입 A>>"); enQueue(LQ, 'A'); printLQ(LQ);
	printf("\n 삽입 B>>"); enQueue(LQ, 'B'); printLQ(LQ);
	printf("\n 삽입 C>>"); enQueue(LQ, 'C'); printLQ(LQ);
	data = peek(LQ);	printf(" peek item : %c \n", data);
	printf("\n 삭제 >>"); data = deQueue(LQ); printLQ(LQ);
	printf("\t 삭제 데이터 : %c", data);
	printf("\n 삭제 >>"); data = deQueue(LQ); printLQ(LQ);
	printf("\t 삭제 데이터 : %c", data);
	printf("\n 삭제 >>"); data = deQueue(LQ); printLQ(LQ);
	printf("\t 삭제 데이터 : %c", data);
	printf("\n 삽입 D>>"); enQueue(LQ, 'D'); printLQ(LQ);
	printf("\n 삽입 E>>"); enQueue(LQ, 'E'); printLQ(LQ);
}