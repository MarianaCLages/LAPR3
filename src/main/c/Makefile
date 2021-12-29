laprProject: main.o existe.o
	gcc -Wall -g main.o existe.o -o laprProject

main.o: 3DMatrix.c
	gcc -Wall -g -c 3DMatrix.c -o main.o

existe.o: existe.s
	gcc -Wall -g -c existe.s -o existe.o
	
clean :
	rm ∗.o laprProject
	
run: laprProject
	./laprProject
