#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "create_container_structure.h"

//Function Responsible for assigning containers to their respective positions in the matrix

Container* fill_matrix(Container* container_array,FILE* file) {

	Container auxContainer;									//Variables needed to insert the containers inside the array
	Container* ptrContainer;
	char buffer[1024];
	int containerPosition = 0;
	
	int firstLineFlag = 0;
	
	char* string;
	
	while(fgets(buffer,sizeof(buffer),file)) {
		
		if(firstLineFlag != 0) {							//Jump to the second line of the file
		
		string = strtok(buffer,",");
		
		auxContainer.xPos = atoi(string);					//Multiple atois in order to obtain the value of each specific variable inside the structure
		string = strtok(NULL, ",");
		auxContainer.yPos = atoi(string);
		string = strtok(NULL, ",");
		auxContainer.zPos = atoi(string);
		string = strtok(NULL, ",");
		auxContainer.widht = atoi(string);
		string = strtok(NULL, ",");
		auxContainer.lenght = atoi(string);
		string = strtok(NULL, ",");
		auxContainer.height = atoi(string);
		string = strtok(NULL, ",");
		auxContainer.isRefrigerated = atoi(string);
		string = strtok(NULL, ",");
		auxContainer.refrigerationTemperature = atoi(string);
		string = strtok(NULL, ",");
		auxContainer.payload = atoi(string);
		string = strtok(NULL, ",");
		auxContainer.tare = atoi(string);
		string = strtok(NULL, ",");
		auxContainer.gross = atoi(string);
		string = strtok(NULL, ",");
		auxContainer.id = atoi(string);
	
		if(containerPosition > ARRAY_SIZE) {				//If there is no more space inside the array, it is necessary to alloc more space inside of it, so we realloc more space for the array
			ptrContainer = (Container*) realloc(container_array,(containerPosition+1) * sizeof(Container));
			
			if(ptrContainer  == NULL) {
				perror("There was an error when the vector was resized!");	
				break;
			
				}	
	
			container_array = ptrContainer;
			
			}	
		
		container_array[containerPosition] = auxContainer; //Place the "constructed" container inside the array
		containerPosition++;	
		
		}
		
		firstLineFlag++;
	
	}
	
	fclose(file);
	numContainers = containerPosition;
	
	return container_array;
	
}
