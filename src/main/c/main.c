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

    //Create the Matrix calling the createMatrix3D function
	createMatrix3D();

    //Print the Menu with all options
    menuPrint();

    int option;
    int count = 0;
    int exit = 0;

    do {

    printf("Please type the option desired!\n");
    scanf("%d",&option);

    switch(option) {
    case(1):
        //printMatrix();
         count++;
        break;
    case(2):
         printf("0x%X\n",occupiedSlots());
         count++;
        break;
    case(3):

        printf("Enter the x position: \n");
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
        //
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
