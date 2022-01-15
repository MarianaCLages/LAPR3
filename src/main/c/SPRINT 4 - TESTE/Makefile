main: main.o verifyStruc.o fill_matrix.o
	gcc -g -Wall main.o verifyStruc.o fill_matrix.o -o main

main.o: main.c
	gcc -g -Wall -c main.c -o main.o
	
verifyStruc.o: verifyStruc.s
	gcc -g -Wall -c verifyStruc.s -o verifyStruc.o
	
fill_matrix.o: fill_matrix.c
	gcc -g -Wall -c fill_matrix.c -o fill_matrix.o
	
clean:
	rm -f *.o main

run: main
	./main
