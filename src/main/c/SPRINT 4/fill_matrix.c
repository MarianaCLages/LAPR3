#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "create_container_structure.h"

//Function Responsible for assigning containers to their respective positions in the matrix

Container* fill_matrix(Container* container_array,FILE* file) {

	Container auxContainer;									//Variables needed to insert the containers inside the array
	Container* ptrContainer;								//Since the realloc allocates the memory in another memory address it is necessary to make our last return value have the correct pointer to the correct memory address of our container stucture array
	char buffer[1024];										//Line max buffer size
	int containerPosition = 0;								//Container position (This serves as a index to go through the array)
	
	int firstLineFlag = 0;									//Since the first line in the array it is the information about the values of the atributes we are going to skip that line while reading the file
	
	char* string;
	
	while(fgets(buffer,sizeof(buffer),file)) {
		
		if(firstLineFlag != 0) {							//Jump to the second line of the file
		
		string = strtok(buffer,",");
		
		if(atoi(string) == '\n') break;						//In case the last character is a new line, we have to stop reading from the file
		
		auxContainer.xPos = atoi(string);					//Multiple atois in order to obtain the value of each specific variable inside the structure
		string = strtok(NULL, ",");
		auxContainer.yPos = atoi(string);
		string = strtok(NULL, ",");
		auxContainer.zPos = atoi(string);
		string = strtok(NULL, ",");
		auxContainer.width = atoi(string);
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
		string = strtok(NULL, ",");
		auxContainer.energyConsumption = atoi(string);
		string = strtok(NULL, ",");
		auxContainer.thermalResistance = atof(string);
		
		if(containerPosition > ARRAY_SIZE) {				//If there is no more space inside the array, it is necessary to alloc more space inside of it, so we realloc more space for the array
			ptrContainer = (Container*) realloc(container_array,(containerPosition+1) * sizeof(Container));
			
			if(ptrContainer  == NULL) {
				perror("There was an error when the vector was resized!");	
				break;
			
				}	
	
			container_array = ptrContainer;					//Associate the new memory address of the allocated memory of the vector in to the return pointer
			free(ptrContainer);
			
			}	
		
		container_array[containerPosition] = auxContainer; //Place the "constructed" container inside the array
		containerPosition++;							   //Increment the index
		
		}
		
		firstLineFlag++;									//Variable that serves as a flag to control de flow of the file reading
	
	}
	
	fclose(file);											//Close the file
	numContainers = containerPosition;						//Get the number of containers that we had in the file into a global variable
	
	return container_array;									//Return the pointer to the array
	
}
