#include<stdio.h>
#include<stdlib.h>
#define Q_SIZE 4

typedef char element;
typedef struct{
	element queue[Q_SIZE];
	int front, rear;
} QueueType;

//공백 순차 큐를 생성하는 연산
QueueType *createQueue(){
	QueueType* type = (QueueType*)malloc(sizeof(QueueType));
	type->front = -1;
	type->rear = -1;
	return type;
}

//순차 큐가 공백 상태인지 검사하는 연산
int isEmpty(QueueType *Q){
	if(Q->front == Q->rear)
		return 1;
	return 0;
}

//순차 큐가 포화 상태인지 검사하는 연산
int isFull(QueueType *Q){
	if(Q->rear == Q_SIZE - 1)
		return 1;
	return 0;
}

//순차 큐의 rear에 원소를 삽입하는 연산
void enQueue(QueueType *Q, element item){
	if(isFull(Q))
		printf("Queue is full\n");
	else{
		Q->rear++;
		Q->queue[Q->rear] = item;
	}
	return;
}

//순차 큐의 front에서 원소를 삭제하는 연산
element deQueue(QueueType *Q){
	int data;
	if(isEmpty(Q))
		printf("Queue is empty\n");
	else{
		Q->front++;
		data = Q->queue[Q->front];
	}
	return data;
}

//순차 큐의 가장 앞에 있는 원소를 검색하는 연산
element peek(QueueType *Q){
	if(isEmpty(Q))
		exit(1);
	return Q->queue[Q->front + 1];
}

//순차 큐의 원소를 출력하는 연산
void printQ(QueueType *Q){
	int data = Q->front + 1;
	if(Q == NULL)
		return;
	while(data <= Q->rear){
		printf("%c ",Q->queue[data]);
		data++;
	}
		
}

void main(){
	QueueType *Q1 = createQueue();
	element data;
	printf("\n ***** 순차 큐 연산 ***** \n");
	printf("\n 삽입 A>>"); enQueue(Q1, 'A'); printQ(Q1);
	printf("\n 삽입 B>>"); enQueue(Q1, 'B'); printQ(Q1);
	printf("\n 삽입 C>>"); enQueue(Q1, 'C'); printQ(Q1);
	
	data = peek(Q1);	printf(" peek item : %c \n", data);
	
	printf("\n 삭제 >>"); data = deQueue(Q1); printQ(Q1);
	printf("\n삭제 데이터 : %c", data);
	printf("\n 삭제 >>"); data = deQueue(Q1); printQ(Q1);
	printf("\n삭제 데이터 : %c", data);
	printf("\n 삭제 >>"); data = deQueue(Q1); printQ(Q1);
	printf("\n삭제 데이터 : %c", data);
	
	printf("\n 삽입 D>>"); enQueue(Q1, 'D'); printQ(Q1);
	printf("\n 삽입 E>>"); enQueue(Q1, 'E'); printQ(Q1);
	getchar();
}