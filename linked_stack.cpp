#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef int element;

typedef struct stackNode{
	element data;
	struct stackNode *link;
} stackNode;

stackNode* top;

//스택이 공백 상태인지 확인하는 연산
int isEmpty(){
	if(top == NULL)
		return 1;
	return 0;
}

//스택의 top에 원소를 삽입하는 연산
void push(element item){
	stackNode* newNode = (stackNode*)malloc(sizeof(stackNode));
	newNode->data = item;
	newNode->link = NULL;
	if(top == NULL)
		top = newNode;
	else{
		newNode->link = top;
		top = newNode;
	}
	return;
}

//스택의 top에서 원소를 삭제하는 연산
element pop(){
	stackNode* node = top;
	element data;
	if(isEmpty()){
		printf("Stack is empty\n");
		return 0;
	}
	else{
		data = top->data;
		top = top->link;
		free(node);
	}
	return data;
}

//스택의 top 원소를 검색하는 연산
element peek(){
	return top->data;
}

//스택의 원소를 top에서 bottom 순서로 출력하는 연산
void printStack(){
	stackNode* node = top;
	while(node){
		printf("%d ",node->data);
		node = node->link;
	}
	printf("\n");
	return;
}

void main(){
	element item;
	top = NULL;
	printf("\n** 연결 스택 연산 **\n");
	printStack();
	push(1); printStack();
	push(2); printStack();
	push(3); printStack();
	
	item = peek(); printStack();
	printf("peek => %d\n",item);
	
	item = pop(); printStack();
	printf("pop => %d\n",item);
	
	item = pop(); printStack();
	printf("pop => %d\n",item);
	
	item = pop(); printStack();
	printf("pop => %d\n",item);
	
	getchar();
}