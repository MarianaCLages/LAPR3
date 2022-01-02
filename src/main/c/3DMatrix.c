#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int* matrix;
int tamanho_y;
int tamanho_x;
int tamanho_z;

void createMatrix3D(void) {
	
	FILE *fp;
	char buffer[255];
	int x = -999, y = -999, z = -999;

	fp = fopen("container.txt", "r");

	fgets(buffer, 255, (FILE*)fp);	
	char* separate = strtok(buffer,",");
	
	tamanho_x = atoi(separate);
	tamanho_y = atoi(separate);
	tamanho_z = atoi(separate);
	fclose(fp);

	int matrix3D[tamanho_x][tamanho_y][tamanho_z];
	matrix = &matrix3D[0][0][0];

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

	fgets(buffer, 255, (FILE*)fp);
	
	while (i != count) {

   		fgets(buffer, 255, (FILE*)fp);

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

		separate = strtok(NULL, ",");
		}
		
   		x = -999, y = -999, z =-999;
   		i++;
	}
	
   	fclose(fp);

}