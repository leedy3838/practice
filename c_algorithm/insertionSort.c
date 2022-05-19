#include<stdio.h>
int size;

void insertionSort(int a[], int size){
	printf("\n정렬할 원소 : ");
	for(int i = 0; i<size; i++)	printf("%d ",a[i]);
	printf("\n\n<<<<<<<<<<< 삽입 정렬 수행 >>>>>>>>>>>>\n");
	for(int i = 1; i<size; i++){
		int j = i;
		int temp = a[i];
		while(j>0 && a[j]<a[j - 1]){
			a[j] = a[j-1];
			j = j-1;
		}
		a[j] = temp;
		printf("\n %d단계 : ",i);
		for(int k = 0; k<size; k++)	printf("%d ",a[k]);
	}
}

void main(){
	int list[8] = {69, 10, 30, 2, 16, 8, 31, 22};
	size = 8;
	
	insertionSort(list, size);
	
	getchar();
}
