#include<stdio.h>
#include<stdlib.h>

float coef;
typedef struct ListNode{
	float coef;
	int expo;
	struct ListNode* link;
}ListNode;
typedef struct ListHead{
	ListNode* head;
}ListHead;
ListHead* createLinkedList(void){
	ListHead* node = (ListHead*)malloc(sizeof(ListHead));
	node->head = NULL;
	return node;
}
void appendTerm(ListHead* L, float coef, int expo){
	ListNode* newNode = (ListNode*)malloc(sizeof(ListNode));
	ListNode* node;
	newNode->coef = coef;
	newNode->expo = expo;
	newNode->link = NULL;
	if(L->head == NULL)
		L->head = newNode;
	else{
		node = L->head;
		while(node->link != NULL)
			node = node->link;
		node->link = newNode;
	}
}
void addPoly(ListHead* A, ListHead* B, ListHead* C){
	ListNode* nodeA = A->head;
	ListNode* nodeB = B->head;
	while(nodeA != NULL && nodeB != NULL){
		if(nodeA->expo > nodeB->expo){
			appendTerm(C, nodeA->coef, nodeA->expo);
			nodeA = nodeA->link;
		}
		else if(nodeA->expo < nodeB->expo){
			appendTerm(C, nodeB->coef, nodeB->expo);
			nodeB = nodeB->link;
		}
		else{
			appendTerm(C, nodeB->coef + nodeA->coef, nodeB->expo);
			nodeB = nodeB->link;
			nodeA = nodeA->link;
		}
	}
	while(nodeA != NULL){
		appendTerm(C, nodeA->coef, nodeA->expo);
			nodeA = nodeA->link;
	}
	while(nodeB != NULL){
		appendTerm(C, nodeB->coef, nodeB->expo);
			nodeB = nodeB->link;
	}
	return;
}
void printPoly(ListHead* L){
	ListNode* node = L->head;
	while(node != NULL){
		printf("%.0fx^%d", node->coef, node->expo);
		if(node->link != NULL)
			printf(" + ");
		node = node->link;
	}
	return;
}
void main(){
	ListHead *A, *B, *C;
	A = createLinkedList();
	B = createLinkedList();
	C = createLinkedList();
	
	appendTerm(A, 4, 3);
	appendTerm(A, 3, 2);
	appendTerm(A, 5, 1);
	printf("\n A(x) =");
	printPoly(A);
	
	appendTerm(B, 3, 4);
	appendTerm(B, 1, 3);
	appendTerm(B, 2, 1);
	appendTerm(B, 1, 0);
	printf("\n B(x) =");
	printPoly(B);
	
	addPoly(A, B, C);
	printf("\n C(x) =");
	printPoly(C);
	getchar();
}
