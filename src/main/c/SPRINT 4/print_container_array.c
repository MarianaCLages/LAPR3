#include "create_container_structure.h"
#include <stdio.h>

void print_container_array(Container* container_array){
//Print the array
	for(unsigned char i = 0 ; i < numContainers; i++) {
		printf("\nContainer number: %hhd \n", i + 1);
		printf(" ID: %hu\n", container_array[i].id);
		printf(" Width: %hu m\n", container_array[i].width);
		printf(" Length: %hu m\n", container_array[i].lenght);
		printf(" Height: %hu m\n", container_array[i].height);
		
		if(container_array[i].isRefrigerated){
			printf(" The container is refrigerated\n");
			printf(" The container has a temperature of: %hhd ºC\n", container_array[i].refrigerationTemperature);
			printf(" The container has a energy consumption of: %hu J\n", container_array[i].energyConsumption);
			printf(" Thermal Resistance of the material of the container is: %.2f K⋅m2/W\n", container_array[i].thermalResistance);
		} else {
			printf(" The container is not refrigerated\n");
		}
		
		printf(" Payload: %hhu KG\n", container_array[i].payload);
		printf(" Tare: %hhu KG\n", container_array[i].tare);
		printf(" Gross: %hhu KG\n", container_array[i].gross);
		printf("\n");
		
	}

}
