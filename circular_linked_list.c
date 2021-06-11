#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct ListNode {
	char data[4];
	struct ListNode* link;
} listNode;

typedef struct {
	listNode* head;
} linkedList_h;

linkedList_h* createLinkedList_h(void){
	linkedList_h* link = (linkedList_h*)calloc(1,sizeof(linkedList_h));
	link->head = NULL;
	return link;
}
void printList(linkedList_h* CL){
	if(CL == NULL || CL->head == NULL)
		return;
	listNode* node = CL->head;
	do{
		printf("%s ",node->data);
		node = node->link;
	}while(node != CL->head);
	return;
}
void insertFirstNode(linkedList_h *CL, char* x){
	listNode* node = (listNode*)calloc(1, sizeof(listNode));
	strcpy(node->data, x);
	if(CL->head == NULL){
		CL->head = node;
		CL->head->link = CL->head;
		return;
	}
	node->link = CL->head;
	listNode* temp = CL->head;
	while(temp->link != CL->head)
		temp = temp->link;
	temp->link = node;
	CL->head = node;
	return;
}
void insertMiddleNode(linkedList_h* CL, listNode* pre, char*x){
	listNode* node = (listNode*)calloc(1, sizeof(listNode));
	strcpy(node->data, x);
	if(CL->head == NULL){
		CL->head = node;
		CL->head->link = CL->head;
	}
	else{
		node->link = pre->link;
		pre->link = node;
	}
	return;
}
void deleteNode(linkedList_h* CL, listNode* old){
	if(CL->head == NULL)
		return;
	if(CL->head -> link == CL->head){
		free(CL->head);
		CL->head = NULL;
	}
	else if(old == NULL)
		return;
	listNode* pre = CL->head;
	while(pre->link != old)
		pre = pre->link;
	pre->link = old->link;
	if(old == CL->head)
		CL->head = old->link;
	free(old);
}
listNode* searchNode(linkedList_h* CL, char* x){
	listNode* node = CL->head;
	if(node == NULL)
		return NULL;
	do{
		if(!strcmp(node->data, x))
			return node;
		node = node->link;
	}while(node != CL->head);
	return node;
}
int main(){
	linkedList_h* CL;
	listNode *p;
	CL = createLinkedList_h();
	printf("(1) 원형 연결 리스트 생성하기!\n");
	getchar();
	
	printf("(2) 원형 연결 리스트에 [월] 노드 삽입하기! \n");
	insertFirstNode(CL, "월");
	printList(CL); getchar();
	
	printf("(3) 원형 연결 리스트의 [월] 노드 뒤에 [수] 노드 삽입하기! \n");
	p = searchNode(CL,"월"); insertMiddleNode(CL, p, "수");
	printList(CL); getchar();
	
	printf("(4) 원형 연결 리스트의 [수] 노드 뒤에 [금] 노드 삽입하기! \n");
	p = searchNode(CL, "수"); insertMiddleNode(CL, p, "금");
	printList(CL); getchar();
	
	printf("(5) 원형 연결 리스트에서 [수] 노드 삭제하기! \n");
	p = searchNode(CL, "수"); deleteNode(CL, p);
	printList(CL); getchar();
	
	return 0;
}