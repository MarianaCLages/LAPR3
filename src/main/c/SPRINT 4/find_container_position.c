#include "create_container_structure.h"

//Saves, if found, the position of the container inside the array
char find_container_position(Container* container_array, char x, char y, char z) {
	for(int index = 0 ; index < numContainers ; index ++){
		if (container_array[index].xPos == x && container_array[index].yPos == y && container_array[index].zPos == z) {
			return index;
		}
	}
	return -1;	//In case the function does not found the desired position it returns a value of "-1", that will be interpreted as not found position
}
