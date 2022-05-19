#include<stdio.h>
typedef int element;
int size;

//부분집합에 대해 셸 정렬을 수행하는 연산
void intervalSort(element a[], int begin, int end, int interval){
	element item;
	int j;
	for(int i = begin + interval; i<=end; i += interval){
		item = a[i];
		for(j = i - interval; j>=begin && item<a[j]; j = j - interval)
			a[j + interval] = a[j];
		a[j + interval] = item;
	}
}

void shellSort(int a[], int size){
	printf("\n정렬할 원소 : ");
	for(int i = 0; i<size; i++)	printf("%d ", a[i]);
	printf("\n\n<<<<<<<<<< 셸 정렬 수행 >>>>>>>>>>>>\n");
	int interval = size/2;
	while(interval>=1){
		for(int i = 0; i<interval; i++)	intervalSort(a, i, size - 1, interval);
		printf("\n interval = %d >> ", interval);
		for(int t = 0; t<size; t++)	printf("%d ",a[t]);
		printf("\n");
		interval = interval/2;
	}
}

void main(){
	int list[8] = {69, 10, 30, 2, 16, 8, 31, 22};
	size = 8;
	
	shellSort(list, size);
	
	getchar();
}
