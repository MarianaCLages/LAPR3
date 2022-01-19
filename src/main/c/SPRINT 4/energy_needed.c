#include "energy_needed.h"

float energy_needed(Container* container_array, unsigned char x, unsigned char y, unsigned char z) {
	
	int container_position = find_container_position(container_array, x, y, z);
	
	if (verifyIfRefrigerated(container_array, container_position) == 1) {
		return ((20 + 5) / container_array[container_position].thermalResistance) * VOYAGE_TIME;
	} else if (verifyIfRefrigerated(container_array, container_position) == 0) {
		return ((20 - 7) / container_array[container_position].thermalResistance) * VOYAGE_TIME;
	} else {
		return -1;
	}
}
