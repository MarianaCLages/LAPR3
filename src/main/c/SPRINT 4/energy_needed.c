#include "create_container_structure.h"
#include "find_container_position.h"
#include "verifyIfRefrigerated.h"

float energy_needed(Container* container_array, char x, char y, char z) {
	
	int container_position = find_container_position(container_array, x, y, z);
	
	if (verifyIfRefrigerated(container_array, container_position)) {
		return ((20 + 5) / container_array[container_position].thermalResistance) * VOYAGE_TIME;
	} else {
		return -1;
	}
}
