#include<stdio.h>
typedef int element;
int size;

//선택 정렬하는 연산
void SelectionSort(int a[], int size){
	element temp;
	printf("\n 정렬할 원소 : ");
	for(int i = 0; i<size; i++)
		printf("%d ", a[i]);
	printf("\n\n<<<<<<<<<<< 선택 정렬 수행 >>>>>>>>>>>>>\n");
	for(int i = 0; i<size - 1; i++){
		int min = i;
		for(int j = i+1; j<size; j++){
			if(a[j]<a[min])
				min = j;
		}
		temp = a[min];
		a[min] = a[i];
		a[i] = temp;
		printf("\n%d 단계 : ", i + 1);
		for(int j = 0; j<size; j++)
			printf("%d ",a[j]);
	}
}
void main(){
	element list[8] = {69, 10, 30, 2, 16, 8, 31, 22};
	int size = 8;
	SelectionSort(list, size);
	
	getchar();
}
