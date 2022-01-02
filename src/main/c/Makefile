laprProject: main.o createMatrix3D.o existe.o occupiedSlots.o
	gcc -Wall -g main.o createMatrix3D.o existe.o occupiedSlots.o -o laprProject

main.o: main.c
	gcc -Wall -g -c main.c -o main.o
	
createMatrix3D.o: 3DMatrix.c
	gcc -Wall -g -c 3DMatrix.c -o createMatrix3D.o
	
occupiedSlots.o: occupiedSlots.s
	gcc -Wall -g -c occupiedSlots.s -o occupiedSlots.o

existe.o: existe.s
	gcc -Wall -g -c existe.s -o existe.o
	
clean :
	rm -f ∗.o laprProject
	
run: laprProject
	./laprProject
