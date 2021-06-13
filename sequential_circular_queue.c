#include<stdio.h>
#include<stdlib.h>
#define cQ_SIZE 4

typedef char element;
typedef struct{
	element queue[cQ_SIZE];
	int front, rear;
}QueueType;

QueueType *createQueue(){
	QueueType* Q = (QueueType*)malloc(sizeof(QueueType));
	Q->front = 0;
	Q->rear = 0;
	return Q;
}

//원형 큐가 공백 상태인지 검사하는 연산
int isEmpty(QueueType *cQ){
	if(cQ->front == cQ->rear)
		return 1;
	return 0;
}

//원형 큐가 포화 상태인지 검사하는 연산
int isFull(QueueType* cQ){
	if((cQ->front - 1) % cQ_SIZE == cQ->rear)
		return 1;
	return 0;
}

//원형 큐의 rear에 원소를 삽입하는 연산
void enQueue(QueueType *cQ, element data){
	if(isFull(cQ))
		printf("Queue is full\n");
	else{
		cQ->rear = (cQ->rear + 1) % cQ_SIZE;
		cQ->queue[cQ->rear] = data;
	}
	return;
}

//원형 큐의 front에서 원소를 삭제하고 반환하는 연산
element deQueue(QueueType *cQ){
	int data;
	if(isEmpty(cQ))
		printf("Queue is empty\n");
	else{
		cQ->front = (cQ->front + 1) % cQ_SIZE;
		data = cQ->queue[cQ->front];
	}
	return data;
}

//원형 큐의 가장 앞에 있는 원소를 검색하는 연산
element peek(QueueType *cQ){
	if(isEmpty(cQ))
		exit(1);
	return cQ->queue[(cQ->front + 1) % cQ_SIZE];
}

//원형 큐의 원소를 출력하는 연산
void printQ(QueueType *cQ){
	int q = (cQ->front + 1) % cQ_SIZE;
	while(q != (cQ->rear + 1)%cQ_SIZE){
		printf("%c ", cQ->queue[q]);
		q = (q+1)%cQ_SIZE;
	}
}

void main(){
	QueueType *cQ = createQueue();
	element data;
	printf("\n ***** 원형 큐 연산 ***** \n");
	printf("\n 삽입 A>>"); enQueue(cQ, 'A'); printQ(cQ);
	printf("\n 삽입 B>>"); enQueue(cQ, 'B'); printQ(cQ);
	printf("\n 삽입 C>>"); enQueue(cQ, 'C'); printQ(cQ);
	data = peek(cQ);	printf(" peek iten : %c \n",data);
	printf("\n 삭제 >>"); data = deQueue(cQ); printQ(cQ);
	printf("\t 삭제 데이터 : %c", data);
	printf("\n 삭제 >>"); data = deQueue(cQ); printQ(cQ);
	printf("\t 삭제 데이터 : %c", data);
	printf("\n 삭제 >>"); data = deQueue(cQ); printQ(cQ);
	printf("\t 삭제 데이터 : %c", data);
	printf("\n 삽입 D>>"); enQueue(cQ, 'D'); printQ(cQ);
	printf("\n 삽입 E>>"); enQueue(cQ, 'E'); printQ(cQ);
}