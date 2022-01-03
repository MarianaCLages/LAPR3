#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int tamanho_y;
int tamanho_x;
int tamanho_z;

int* createMatrix3D() {

	
	FILE *fp;
	char buffer[255];

	int x = -999, y = -999, z = -999;
	int matrix3D[50][50][50];
	
	tamanho_y = 10;
	tamanho_x = 10;
	tamanho_z = 10;
	
	for (int i = 0; i < 10; i++){
			
		for (int j = 0; j < 10; j++){
			
			for (int k = 0; k < 10; k++){
				
				matrix3D[i][j][k] = 0;
				
			}
		}
	}
	


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
		/*
			if (x != -999 && y != -999 && z != -999 && matrix3D[x][y][z] != 0) {
				printf("position[%d][%d][%d] = %d\n", x, y, z, matrix3D[x][y][z]);
			}
			*/
	
					
		separate = strtok(NULL, ",");
		}
		
   		x = -999, y = -999, z =-999;
   		i++;
	}
	
   	fclose(fp);
   	/*
   	for (i = 0; i < 10; i++){
			
		for (int j = 0; j < 10; j++){
			
			for (int k = 0; k < 10; k++){
				
				if(matrix3D[i][j][k] != 0){
					printf("matrixd[%d][%d][%d] = %d\n ",i,j,k,matrix3D[i][j][k]);
				}
			}
		}
	}*/
   	
	return &matrix3D;	
}
