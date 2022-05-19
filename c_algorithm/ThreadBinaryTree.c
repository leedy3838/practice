#include<stdio.h>
#include<stdlib.h>

typedef struct treeNode{
	char data;
	struct treeNode* left;
	struct treeNode* right;
	int isThreadRight;
} treeNode;

//data를 루트 노드로 하여 왼쪽 서브 트리와 오른쪽 서브 트리를 연결하는 연산
treeNode* makeRootNode(char data, treeNode* leftNode, treeNode* rightNode, int isThreadRight){
	treeNode* newNode = (treeNode*)malloc(sizeof(treeNode));
	newNode->data = data;
	newNode->left = leftNode;
	newNode->right = rightNode;
	newNode->isThreadRight = isThreadRight;
	return newNode;
}

//후속자 노드를 반환하는 연산
treeNode* findThreadSuccessor(treeNode* p){
	treeNode* q = p->right;
	if(q == NULL)
		return q;
	else if(p->isThreadRight == 1)
		return q;
	else
		while(q->left != NULL)
			q = q->left;
	return q;
}


//스레드 이진 트리의 중위 순회
void threadInorder(treeNode* root){
	treeNode* p = root;
	while(p->left != NULL)
		p = p->left;
	do{
		printf("%3c",p->data);
		p = findThreadSuccessor(p);
	}while(p);
}

void main(){
	treeNode* n7 = makeRootNode('D', NULL, NULL, 0);
	treeNode* n6 = makeRootNode('C', NULL, NULL, 1);
	treeNode* n5 = makeRootNode('B', NULL, NULL, 1);
	treeNode* n4 = makeRootNode('A', NULL, NULL, 1);
	treeNode* n3 = makeRootNode('/', n6, n7, 0);
	treeNode* n2 = makeRootNode('*', n4, n5, 0);
	treeNode* n1 = makeRootNode('-', n2, n3, 0);
	
	n4->right = n2;
	n5->right = n1;
	n6->right = n3;
	
	printf("\n 스레드 이진 트리의 중위 순회 : ");
	threadInorder(n1);
	
	getchar();
}
