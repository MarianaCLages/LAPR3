#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "occupiedSlots.h"
#include "createMatrix3D.h"
#include "menuPrint.h"
#include "printmatrix.h"
#include "existeLoop.h"
#include "existe2.h"
#include "existe.h"
//Apontador para a primeira posição da matrix criada através do Cargo Manifest

int* matrix;

//Para ser possível saber se uma posição esta ocupada ou não é necessário definir estas variáveis como variáveis globais para ser possível aceder lhes em Assembly

int posicao_z;
int posicao_y;
int posicao_x;

//Tamanho da matrix para ser possível percorrer a mesma

int tamanho_y;
int tamanho_x;
int tamanho_z;

const int TAMANHO_X = 50;
const int TAMANHO_y = 50;
const int TAMANHO_z = 50;

int main(void) {


    //Create the Matrix calling the createMatrix3D function
    
	//////////////////////////////////////////////////////////////
	
	FILE *fp;
	char buffer[255];

	int x = -999, y = -999, z = -999;
	int matrix3D[TAMANHO_X][TAMANHO_y][TAMANHO_z];
	
	tamanho_y = TAMANHO_X;
	tamanho_x = TAMANHO_y;
	tamanho_z = TAMANHO_z;
	
	for (int i = 0; i < TAMANHO_X; i++){
			
		for (int j = 0; j < TAMANHO_y; j++){
			
			for (int k = 0; k < TAMANHO_z; k++){
				
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
   	
   	matrix = &matrix3D[0][0][0];
   	
    //Print the Menu with all options
    menuPrint();

    int option;
	count = 0;
    int exit = 0;

    do {

    printf("Please type the option desired!\n");
    scanf("%d",&option);
	
    switch(option) {
    case(1):
    
		//Função para dar print à matriz com apenas os valores ocupados
    
        printMatrix(TAMANHO_X ,TAMANHO_y, TAMANHO_z);
        count++;
        break;
    case(2):
    
		//US314 Mostrar os slots ocupados da matriz
    
         printf("\nThe number of occupied slots are : 0x%lX\n",occupiedSlots());
         count++;
        break;
    case(3):

		//US315 Mostrar se um específico slot esta ocupado ou não (introduzindo as coordenadas do mesmo)

         printf("\nEnter the x position: \n");
         scanf("%d",&posicao_x);

         printf("Enter the y position: \n");
         scanf("%d",&posicao_y);

         printf("Enter the z position: \n");
         scanf("%d",&posicao_z);

         char verification = existe();

         if(verification) printf("The typed slot is occupied!\n");
         else printf("The typed slot is not occupied!\n");

         count++;
         break;
    case(4):
        int total = existeLoop();
        printf("The total of positions occupied is: %d", total);
        break;
    case(5):
        exit++;
        break;
    default:
        printf("Please enter a valid option!!\n");
        count++;
        break;

    }

    if(count == 2){
    count = 0;
    menuPrint();
    }

    } while (exit == 0);

}
