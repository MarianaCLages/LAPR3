main: main.o verifyStruc.o fill_matrix.o find_container_position.o
	gcc -g -Wall main.o verifyStruc.o fill_matrix.o find_container_position.o -o main

main.o: main.c
	gcc -g -Wall -c main.c -o main.o
	
verifyStruc.o: verifyStruc.s
	gcc -g -Wall -c verifyStruc.s -o verifyStruc.o
	
fill_matrix.o: fill_matrix.c
	gcc -g -Wall -c fill_matrix.c -o fill_matrix.o
	
find_container_position.o: find_container_position.c
	gcc -g -Wall -c find_container_position.c -o find_container_position.o
	
clean:
	rm -f *.o main

run: main
	./main
