#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "existe.h"

int posicao_z;
int posicao_y;
int posicao_x;
int tamanho_y;
int tamanho_x;
int tamanho_z;
int* matrix;
int* enderecoTest;


int main(void) {
	
	FILE *fp;
	char buffer[255];

	int x = -999, y = -999, z = -999;
	int matrix3D[50][50][50];
	
	matrix = &matrix3D[0][0][0];
	posicao_x =25;
	posicao_y =5;
	posicao_z =2;
	tamanho_y = 50;
	tamanho_x = 50;
	tamanho_z = 50;

		
	fp = fopen("container.txt", "r");
	
	


  	int i = 0;
	char c;	
	int count = 0;

 	for (c = getc(fp); c != EOF; c = getc(fp)) {
        if (c == '\n') { // Increment count if this character is newline
            count++;
		}
	}
	

	fclose(fp);
	
	
	fp = fopen("container.txt", "r");
	

	
	while (i != count) {

		fgets(buffer, 255, (FILE*)fp);
   	
		//printf("%s",buffer);
		
		char* separate = strtok(buffer,",");
		
		
		while (separate != NULL) {
		
			if (x == -999) {
				x = atoi(separate);
			} else if (x != -999 && y == -999) {
				y = atoi(separate);
			} else if (y != -999 && z == -999) {
				z = atoi(separate);
			} else if(z != -999) {
				matrix3D[x][y][z] = atoi(separate);
			}
		
			/*printf("%d\n",x);
			printf("%d\n",y);
			printf("%d\n",z);*/
		
			if (x != -999 && y != -999 && z != -999 && matrix3D[x][y][z] != 0) {
				printf("position[%d][%d][%d] = %d\n", x, y, z, matrix3D[x][y][z]);
			}
			
	
					
		separate = strtok(NULL, ",");
		}
		
   		x = -999, y = -999, z =-999;
   		i++;
	}
	
   	fclose(fp);
   	
	printf("position[%d][%d][%d] ", posicao_x, posicao_y, posicao_z);
	
   	if(existe()){
		printf("existe\n");
	}else{
		printf("não existe\n");
	}
	
	return 0;	
}
