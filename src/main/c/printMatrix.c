#include <stdio.h>
extern int* matrix;

void printMatrix(int tamanho_x, int tamanho_y,int tamanho_z) {

for (int i = 0; i < tamanho_x; i++){

        for (int j = 0; j < tamanho_y; j++){

            for (int k = 0; k < tamanho_z; k++){

                if(*(matrix+i*tamanho_y*tamanho_z+j*tamanho_z+k)!=0){
					int offset = i*tamanho_y*tamanho_z+j*tamanho_z+k;

                    printf("matrix[%d][%d][%d] = %d\n",i,j,k, *(matrix+offset));

                }

            }

        }

    }

}
