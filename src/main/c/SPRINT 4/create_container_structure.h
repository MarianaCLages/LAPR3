#ifndef CREATE_CONTAINER_STRUCTURE_H
#define CREATE_CONTAINER_STRUCTURE_H

#define ARRAY_SIZE 5						//Initial Array size
#define VOYAGE_TIME 9000					//Trip time
extern int numContainers;					//Number of containers to be printed in the main

//Structure size = 24 Bytes
//Structure data alignment (K = 4) : 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 2 + 2 + 2 + 2 + 4 + 2 + (GAP = 2)

typedef struct {
	unsigned char xPos;						//Container	X position inside the matrix
	unsigned char yPos;						//Container Y position inside the matrix
	unsigned char zPos;						//Container Z position inside the matrix
	unsigned char width;					//Container Width
	unsigned char lenght;					//Container Lenght
	unsigned char height;					//Container Height
	unsigned char isRefrigerated;			//Container Refrigeration (If the value is 1 it means the container is refrigerated if the value is 0 the container is not refrigerated)
	char refrigerationTemperature;			//Container Refrigeration temperature
	unsigned short payload;					//Container Payload	
	unsigned short tare;					//Container Tare		
	unsigned short gross;					//Container Gross
	unsigned short id;						//Container ID
	float thermalResistance;				//Container Material thermal resistance
	unsigned short energyConsumption;		//Container Energy Consumption
}Container;

#endif
