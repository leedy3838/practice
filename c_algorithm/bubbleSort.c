#include<stdio.h>
typedef int element;
int size;

//버블 정렬하는 연산
void bubbleSort(element a[], int size){
	element temp;
	printf("\n정렬할 원소 : ");
	for(int i = 0; i<size; i++)
		printf("%d ",a[i]);
	printf("\n<<<<<<<<버블 정렬 수행 >>>>>>>>>>\n");
	for(int i = size - 1; i>0; i--){
		for(int j = 0; j< i; j++){
			if(a[j]>a[j+1]){
				temp = a[j];
				a[j] = a[j+1];
				a[j+1] = temp;
			}
		}
		for(int j = 0; j<size; j++)
			printf("%d ",a[j]);
		printf("\n");
	}
}
void main(){
	element list[8] = {69, 10, 30, 2, 16, 8, 31, 22};
	size = 8;
	bubbleSort(list, size);
	getchar();
}
