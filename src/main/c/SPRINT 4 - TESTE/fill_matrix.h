#ifndef FILL_MATRIX_H
#define FILL_MATRIX_H

#include "create_container_structure.h"
#include "find_container_position.h"

#define FILE_NAME "containers.txt"									//File name to be read

char verifyStruc(Container* c,int position);									//Function to verify Data later on can be changed 
Container* fill_matrix(Container* container_arrray,FILE* file);		//Function that fills the allocated memory for the dynamic array

#endif
