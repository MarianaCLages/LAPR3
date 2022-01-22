#include "energy_needed.h"

//Function that calculates the energy needed for a given container
float energy_needed(Container* container_array, unsigned char x, unsigned char y, unsigned char z) {
	
	int container_position = find_container_position(container_array, x, y, z);		//Find the container position given the x,y,z coordinates
	
	if(container_position == -1) {													//If the container position isn't found, we stop the program here so we do not have problems while trying to acess memory that does not exist
		return -1;
	}
	
	if (verifyIfRefrigerated(container_array, container_position) == 1) {			//In case the container is refrigerated, we have to apply the formula to calculate the energy needed of the given container
		return ((20 - container_array[container_position].refrigerationTemperature) / container_array[container_position].thermalResistance) * VOYAGE_TIME;
	} else {
		return -1;																	//In case something bad happens, we return the value -1
	}
}
