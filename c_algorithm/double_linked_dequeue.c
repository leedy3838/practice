#include<stdio.h>
#include<stdlib.h>

typedef char element;
typedef struct DQNode {
	element data;
	struct DQNode* llink;
	struct DQNode* rlink;
} DQNode;

typedef struct {
	DQNode *front, *rear;
} DQueType;

//공백 데크를 생성하는 연산
DQueType* createDQue(){
	DQueType* DQ = (DQueType*)malloc(sizeof(DQueType));
	DQ->front = NULL;
	DQ->rear = NULL;
	return DQ;
}

//데크가 공백 상태인지 검사하는 연산
int isEmpty(DQueType* DQ){
	if(DQ->front == NULL)
		return 1;
	return 0;
}

//데크의 front 앞으로 원소를 삽입하는 연산
void insertFront(DQueType* DQ, element item){
	DQNode* node = (DQNode*)malloc(sizeof(DQNode));
	node->llink = NULL;
	node->data = item;
	if(isEmpty(DQ)){
		DQ->front = node;
		DQ->rear = node;
		node->rlink = NULL;
	}
	else{
		node->rlink = DQ->front;
		DQ->front->llink = node;
		DQ->front = node;
	}
}

//데크의 rear 뒤로 원소를 삽입하는 연산
void insertRear(DQueType* DQ, element item){
	DQNode* node = (DQNode*)malloc(sizeof(DQNode));
	node->rlink = NULL;
	node->data = item;
	if(isEmpty(DQ)){
		DQ->front = node;
		DQ->rear = node;
		node->llink = NULL;
	}
	else{
		node->llink = DQ->rear;
		DQ->rear->rlink = node;
		DQ->rear = node;
	}
}

//데크의 front 노드를 삭제하고 반환하는 연산
element deleteFront(DQueType* DQ){
	DQNode* node = DQ->front;
	element data;
	if(isEmpty(DQ)){
		printf("DQ is empty\n");
		return 0;
	}
	else{
		data = DQ->front->data;
		DQ->front = DQ->front->rlink;
		DQ->front->llink = NULL;
		free(node);
	}
	return data;
}

//데크의 rear 노드를 삭제하고 반환하는 연산
element deleteRear(DQueType* DQ){
	DQNode* node = DQ->rear;
	element data;
	if(isEmpty(DQ)){
		printf("DQ is empty\n");
		return 0;
	}
	else{
		data = DQ->rear->data;
		DQ->rear = DQ->rear->llink;
		DQ->rear->rlink = NULL;
		free(node);
	}
	return data;
}

//데크의 front 노드의 데이터 필드를 반환하는 연산
element peekFront(DQueType* DQ){
	return DQ->front->data;
}

//데크의 rear 노드의 데이터 필드를 반환하는 연산
element peekRear(DQueType* DQ){
	return DQ->rear->data;
}

//데크의 front 노드부터 rear 노드까지 출력하는 연산
void printDQ(DQueType* DQ){
	DQNode* node = DQ->front;
	while(node){
		printf("%c ",node->data);
		node = node->rlink;
	}
	return;
}

void main(){
	DQueType* DQ1 = createDQue();
	element data;
	printf("\n ***** 데크 연산 ***** \n");
	printf("\n front 삽입 A>>"); insertFront(DQ1, 'A'); printDQ(DQ1);
	printf("\n front 삽입 B>>"); insertFront(DQ1, 'B'); printDQ(DQ1);
	printf("\n rear 삽입 C>>"); insertRear(DQ1, 'C'); printDQ(DQ1);
	printf("\n front 삭제 >>"); data = deleteFront(DQ1); printDQ(DQ1);
	printf("\t 삭제 데이터 : %c", data);
	printf("\n rear 삭제 >>"); data = deleteRear(DQ1); printDQ(DQ1);
	printf("\t\t 삭제 데이터 : %c", data);
	printf("\n rear 삽입 D>>"); insertRear(DQ1, 'D'); printDQ(DQ1);
	printf("\n front 삽입 E>>"); insertFront(DQ1, 'E'); printDQ(DQ1);
	printf("\n front 삽입 F>>"); insertFront(DQ1, 'F'); printDQ(DQ1);
	
	data = peekFront(DQ1);	printf("\n peek Front item : %c \n", data);
	data = peekRear(DQ1);	printf(" peek Rear item : %c \n",data);
	
	getchar();
}
