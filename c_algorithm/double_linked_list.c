#include<stdio.h>
#include<string.h>
#include<stdlib.h>

typedef struct ListNode{
	struct ListNode* llink;
	char data[4];
	struct ListNode* rlink;
} listNode;
typedef struct{
	listNode* head;
}linkedList_h;
linkedList_h* createLinkedList_h(void){
	linkedList_h* link = (linkedList_h*)calloc(1, sizeof(linkedList_h));
	link->head = NULL;
	return link;
}
void printList(linkedList_h* DL){
	listNode* node = DL->head;
	while(node){
		printf("%s ",node->data);
		node = node->rlink;
	}
	return;
}
void insertNode(linkedList_h* DL, listNode* pre, char* x){
	listNode* node = (listNode*)calloc(1, sizeof(listNode));
	strcpy(node->data, x);
	if(DL->head == NULL){
		node->rlink = NULL;
		node->llink = NULL;
		DL->head = node;
	}
	else{
		listNode* head = pre;
		node->rlink = head->rlink;
		node->llink = head;
		if(head->rlink != NULL)
			head->rlink->llink = node;
		head->rlink = node;
	}
	return;
}
void deleteNode(linkedList_h* DL, listNode* old){
	if(DL->head == NULL)
		return;
	if(old == NULL)
		return;
	if(old->rlink != NULL)
		old->rlink->llink = old->llink;
	if(old->llink != NULL)
		old->llink->rlink = old->rlink;
	free(old);
	return;
}
listNode* searchNode(linkedList_h* DL, char* x){
	listNode* node = DL->head;
	while(node != NULL){
		if(!strcmp(x, node->data))
			break;
		node = node->rlink;
	}
	return node;
}
int main(){
	linkedList_h* DL;
	listNode *p;
	DL = createLinkedList_h();
	printf("(1) 이중 연결 리스트 생성하기! \n");
	printList(DL); getchar();
	
	printf("(2) 이중 연결 리스트에 [월] 노드 삽입하기! \n");
	insertNode(DL, NULL, "월");
	printList(DL); getchar();
	
	printf("(3) 이중 연결 리스트의 [월] 노드 뒤에 [수] 노드 삽입하기! \n");
	p = searchNode(DL, "월"); insertNode(DL, p, "수");
	printList(DL); getchar();
	
	printf("(4) 이중 연결 리스트의 [수] 노드 뒤에 [금] 노드 삽입하기! \n");
	p = searchNode(DL, "수"); insertNode(DL, p, "금");
	printList(DL); getchar();
	
	printf("(5) 이중 연결 리스트에서 [수] 노드 삭제하기! \n");
	p = searchNode(DL, "수"); deleteNode(DL, p);
	printList(DL); getchar();
	return 0;
}
