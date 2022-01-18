#ifndef CREATE_CONTAINER_STRUCTURE_H
#define CREATE_CONTAINER_STRUCTURE_H

#define ARRAY_SIZE 5

//Structure size = 36 Bytes
//Structure data alignment (K = 4) : 1 + 1 + 1 + (GAP = 1) + 4 + 4 + 4 + 4 + 4 + 4 + 2 + 4 + 1 + 1

typedef struct {
	unsigned char xPos						//Container	x position inside the matrix
	unsigned char yPos						//Container y position inside the matrix
	unsigned char zPos						//Container z position inside the matrix
	unsigned int payload;					//Container Payload	
	unsigned int tare;						//Container Tare		
	unsigned int gross;						//Container Gross
	unsigned int widht;						//Container Widht
	unsigned int lenght;					//Container Lenght
	unsigned int height;					//Container Height
	unsigned short id						//Container ID
	unsigned char isoCode [4];				//Container ISO Code (contains information about the container's cargo)
	unsigned char isRefrigerated;			//Container Refrigeration (If the value is 1 it means the container is refrigerated if the value is 0 the container is not refrigerated)
	char refrigerationTemperature
}Container;

int verifyStruct(Container* c, int position);
Container* construct_array(Container* container_arrray, FILE* file);

#endif
