#include <stdio.h>
#include <stdlib.h>
#include <errno.h>

#include "fill_matrix.h"

//Container size (20 Bytes)

int numContainers = 0;

int main(void) {	
	//Size of the Container Structure	
	const char CONTAINER_SIZE = sizeof(Container);
	
	//Allocate the memory to create an 3D Matrix for containers
	Container* container_array = malloc(CONTAINER_SIZE * ARRAY_SIZE);
	
	//Pointer to the file with the containers information
	FILE* fp = fopen(FILE_NAME,"r");
	
	//If, for some reason, the file doesn't exist, we should stop the program here
	if(fp == NULL) {
		perror("Unable to open file!");
		return EXIT_FAILURE;
	}
	
	//Fill dynamically the array with the containers information
	container_array = fill_matrix(container_array,fp);
	
	//Verify the integrity of the array
	if(container_array == NULL) {
		perror("Unable to read the file!,there is missinformation it it");
		return EXIT_FAILURE;
	}
	
	//Print the array
	for(unsigned char i = 0 ; i < numContainers; i++) {
		printf("\nContainer number : %d \n",i+1);
		printf(" ID : %hu \n",container_array[i].id);
		printf(" Width : %hu \n",container_array[i].widht);
		printf(" Length : %hu \n",container_array[i].lenght);
		printf(" Height : %hu \n",container_array[i].height);
		
		if(container_array[i].isRefrigerated){
			printf(" The container is refrigerated\n");
			printf(" The container has a temperature of : %hhd\n",container_array[i].refrigerationTemperature);
			printf(" The container has a energy consumption of : %hu \n",container_array[i].energyConsumption);
			printf(" Energy Consumption of the container is : %hu\n",container_array[i].energyConsumption);
			printf(" Thermal Resistance of the material of the container is : %f\n",container_array[i].thermalResistance);
		} else {
			printf(" The container is not refrigerated\n");
		}
		
		printf(" Payload : %hhu \n",container_array[i].payload);
		printf(" Tare : %hhu \n",container_array[i].tare);
		printf(" Gross : %hhu \n",container_array[i].gross);
		printf("\n");
		
	}
	
	//Test for the OffSets
	Container c1;
	Container* ptr = &c1;
	c1.tare = 99;
	
	//Quando for necessario fazer o menu implementar aqui ler da consola o X Y e Z e caso o valor seja -1 nao existe um contentor com essa posição
	printf("Position : %hhd\n",find_container_position(container_array,2,9,1));
	
	short value = verifyStruc(ptr,find_container_position(container_array,1,9,0));
	printf("Value : %hhd\n",value);
	
	free(container_array);
	
	return EXIT_SUCCESS;
}
