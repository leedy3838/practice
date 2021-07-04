#include<stdio.h>
#include<stdlib.h>

typedef int element;
typedef struct treeNode{
	element key;
	struct treeNode* left;
	struct treeNode* right;
}treeNode;

treeNode* insertNode(treeNode *p, element x){
	treeNode *newNode;
	if(p == NULL){
		newNode = (treeNode*)malloc(sizeof(treeNode));
		newNode->left = NULL;
		newNode->right = NULL;
		newNode->key = x;
		return newNode;
	}
	else if(p->key<x)
		p->right = insertNode(p->right, x);
	else if(p->key>x)
		p->left = insertNode(p->left, x);
	else	printf("이미 같은 키가 있습니다\n");
	
	return p;
}

void displayInorder(treeNode* root){
	if(root){
		displayInorder(root->left);
		printf("%d ", root->key);
		displayInorder(root->right);
	}
}

void treeSort(element a[], int n){
	treeNode* root = NULL;
	root = insertNode(root, a[0]);
	for(int i = 1; i<n; i++)
		insertNode(root, a[i]);
	displayInorder(root);
}

void main(){
	element list[8] = { 69, 10, 30, 2, 16, 8, 31, 22};
	int size = 8;
	printf("\n<<<<<<트리 정렬 수행 >>>>>>\n");
	treeSort(list, size);
	
	getchar();
}