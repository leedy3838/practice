#include<stdio.h>
#include<stdlib.h>

typedef struct treeNode{
	char data;
	struct treeNode* left;
	struct treeNode* right;
} treeNode;

//data를 루트 노드로 하여 왼쪽 서브 트리와 오른쪽 서브 트리를 연결하는 연산
treeNode* makeRootNode(char data, treeNode* leftNode, treeNode* rightNode){
	treeNode* newNode = (treeNode*)malloc(sizeof(treeNode));
	newNode->data = data;
	newNode->left = leftNode;
	newNode->right = rightNode;
	return newNode;
}

//이진 트리에 대한 전위 순회 연산
void preorder(treeNode* root){
	if(root){
		printf("%c",root->data);
		preorder(root->left);
		preorder(root->right);
	}
}

//이진 트리에 대한 중위 순회 연산
void inorder(treeNode* root){
	if(root){
		inorder(root->left);
		printf("%c",root->data);
		inorder(root->right);
	}
}

//이진 트리에 대한 후위 순회 연산
void postorder(treeNode* root){
	if(root){
		postorder(root->left);
		postorder(root->right);
		printf("%c",root->data);
	}
}

void main(){
	treeNode* n7 = makeRootNode('D', NULL, NULL);
	treeNode* n6 = makeRootNode('C', NULL, NULL);
	treeNode* n5 = makeRootNode('B', NULL, NULL);
	treeNode* n4 = makeRootNode('A', NULL, NULL);
	treeNode* n3 = makeRootNode('/', n6, n7);
	treeNode* n2 = makeRootNode('*', n4, n5);
	treeNode* n1 = makeRootNode('-', n2, n3);
	
	printf("\n preorder : ");
	preorder(n1);
	
	printf("\n inorder : ");
	inorder(n1);
	
	printf("\n postorder : ");
	postorder(n1);
	
	getchar;
}