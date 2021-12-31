#include <stdio.h>
#include "occupiedSlots.h"
#include "createMatrix3D.h"

int posicao_z;
int posicao_y;
int posicao_x;
int tamanho_y;
int tamanho_x;
int tamanho_z;
int* enderecoTest;

int main(void) {

    //Create the Matrix calling the createMatrix3D
	createMatrix3D();
	for (int i = 0; i < tamanho_x; i++)
	{
		for (int j = 0; j < tamanho_y; j++)
		{
			for (int k = 0; k < tamanho_z; k++)
			{
				if (*(matrix+i*tamanho_y*tamanho_z+j*tamanho_z+k)!=0)
				{
					printf("matrix[%d][%d][%d] = %d\n ",i,j,k,*(matrix+i*tamanho_y*tamanho_z+j*tamanho_z+k));
				}
			}
		}
	}
	printf("0x%lX\n",occupiedSlots());
   //createMatrix3D();
/*
    //Print the Menu with all options

   //menuPrint();

    int option;
    int error = 0;

    do {

    printf("Please type the option desired!\n");
    scanf("%d",&option);

    switch(option) {
    case(1):
        //printMatrix();
        break;
    case(2):
        //Outra US
        break;
    case(3):
        //Outra Us
        break;
    case(4):
        break;
    default:
        printf("Please enter a valid option!!\n");
        error++;
        break;

    }

    if(error == 3) menuPrint();

    } while (option < 0 || option);
*/
}
