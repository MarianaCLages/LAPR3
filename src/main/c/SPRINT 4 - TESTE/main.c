#include <stdio.h>
#include "verifyStruc.h"

//Size of Container structure 32 bytes

typedef struct {
	char identification [8];		//Container ID
	char isoCode [11];				//Container ISO Code
	char isRefrigerated;			//Container Refrigeration (If the value is 1 it means the container is refrigerated if the value is 0 the container is not refrigerated)
	int payload;					//Container Payload
	int tare;						//Container Tare
	int gross;						//Container Gross
}Container;

int main(void) {

	Container c1;
	Container* ptr = &c1;
	
	ptr->tare = 2;
	
	c1.payload = 2;
	
	printf("Tare : %d\n",c1.tare);
	
	//printf("Payload : %d\n",c1.payload);
	
	int value = verificar(ptr);
	
	printf("Value : %d\n",value);

}
