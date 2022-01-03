laprProject: main.o  menuPrint.o printMatrix.o existe.o occupiedSlots.o 
	gcc -Wall -g main.o existe.o occupiedSlots.o menuPrint.o printMatrix.o -o laprProject

main.o: main.c
	gcc -Wall -g -c main.c -o main.o
	
occupiedSlots.o: occupiedSlots.s
	gcc -Wall -g -c occupiedSlots.s -o occupiedSlots.o

existe.o: existe.s
	gcc -Wall -g -c existe.s -o existe.o
	
menuPrint.o: menuPrint.c
	gcc -Wall -g -c menuPrint.c -o menuPrint.o

printMatrix.o: printMatrix.c
	gcc -Wall -g -c printMatrix.c -o printMatrix.o


clean :
	rm -f ∗.o laprProject
	
run: laprProject
	./laprProject
