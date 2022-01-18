#include "create_container_structure.h"
#include "find_container_position.h"
#include "verifyIfRefrigerated.h"

float energy_needed(Container* container_array, unsigned char x, unsigned char y, unsigned char z) {
	unsigned int container_position = find_container_position(container_array, x, y, z);
	
	char refrigerated = verifyIfRefrigerated(container_array, container_position);
	
	if (refrigerated == 1) {
		return ((20 + 5) / container_array[container_position].thermalResistance) * 9000;
	} else {
		return -1;
	}
}
