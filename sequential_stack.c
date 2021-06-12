#include<stdio.h>
#include<stdlib.h>
#define STACK_SIZE 100

typedef int element;

element stack[STACK_SIZE];
int top =-1;

//스택이 공백 상태인지 확인하는 연산
int isEmpty(){
	if(top == -1)
		return 1;
	return 0;
}

//스택이 포화 상태인지 확인하는 연산
int isFull(){
	if(top == STACK_SIZE - 1)
		return 1;
	return 0;
}
//스택의 top에 원소를 삽입하는 연산
void push(element item){
	if(isFull()){
		printf("Stack is full\n");
		return;
	}
	stack[++top] = item;
	return;
}

//스택의 top에서 원소를 삭제하는 연산
element pop(){
	if(isEmpty()){
		printf("Stack is empty\n");
		return 0;
	}
	return stack[top--];
}

//스택의 top 원소를 검색하는 연산
element peek(){
	if(isEmpty()){
		printf("Stack is empty\n");
		exit(1);
	}
	return stack[top];
}

//스택의 원소를 출력하는 연산
void printStack(){
	for(int i = 0; i<=top; i++)
		printf("%d ",stack[i]);
	printf("\n");
}
void main(){
	element item;
	printf("\n** 순차 스택 연산 **\n");
	printStack();
	push(1); printStack();
	push(2); printStack();
	push(3); printStack();
	
	item = peek(); printStack();
	printf("\npeek => %d\n", item);
	
	item = pop(); printStack();
	printf("\n pop => %d\n", item);
	
	item = pop(); printStack();
	printf("\n pop => %d\n", item);
	
	item = pop(); printStack();
	printf("\n pop => %d\n", item);
	
	getchar();
}
