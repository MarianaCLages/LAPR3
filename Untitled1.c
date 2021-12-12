
#include <stdio.h>
#include <string.h>

int main(void) {
	
	FILE *fp;
	char buff[255];

	int x = -999,y = -999,z =-999;

	int matrix3D[30][30][30];

	
	
	fp = fopen("container.txt","r+");


  	int i = 0;

	char c;
	int count = 0;

 	for (c = getc(fp); c != EOF; c = getc(fp)){
        if (c == '\n') // Increment count if this character is newline
            count = count + 1;}


	fclose(fp);	
	
	fp = fopen("container.txt","r+");
	
	while(i != count){

		fgets(buff, 255, (FILE*)fp);
   	
		//printf("%s",buff);
		
		char * separate = strtok(buff,",");
	
		
	
		
		while( separate != NULL){
		
		
		if(x == -999)
		x = atoi(separate);
		
		else if(x != -999 && y == -999)
		y = atoi(separate);
		
		else if(y != -999 && z == -999)
		z = atoi(separate);
		
		else if(z != -999)
		matrix3D[x][y][z] = atoi(separate);

		
		if(x != -999 && y!= -999 && z != -999 && matrix3D[x][y][z] != 0)
			printf("position[%d][%d][%d] = %d\n",x,y,z,matrix3D[x][y][z]);	
	
					
		separate = strtok(NULL,",");
		}
			


   		x = -999,y = -999,z =-999;
   		i++;
		}
	
   	fclose(fp);
	

	
	return 0;	
}
