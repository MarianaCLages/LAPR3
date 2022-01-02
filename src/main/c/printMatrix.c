void printMatrix(void) {

for (i = 0; i < tamanho_x; i++){

        for (int j = 0; j < tamanho_y; j++){

            for (int k = 0; k < tamanho_z; k++){

                if(matrix3D[i][j][k] != 0){

                    printf("matrixd[%d][%d][%d] = %d\n ",i,j,k,matrix3D[i][j][k]);

                }

            }

        }

    }

}