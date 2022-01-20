#include "create_container_structure.h"

char find_container_position(Container* container_array, char x, char y, char z) {		//Saves, if found, the position of the container inside the array
	
	for(int index = 0 ; index < numContainers ; index ++){
		if (container_array[index].xPos == x && container_array[index].yPos == y && container_array[index].zPos == z) {
			return index;
		}
	}
	return -1;
}
