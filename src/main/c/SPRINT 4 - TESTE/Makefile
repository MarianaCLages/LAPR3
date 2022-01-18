main: main.o verifyStruct.o fill_matrix.o find_container_position.o verifyIfRefrigerated.o energy_needed.o
	gcc -g -Wall main.o verifyStruct.o fill_matrix.o find_container_position.o verifyIfRefrigerated.o energy_needed.o -o main

main.o: main.c
	gcc -g -Wall -c main.c -o main.o
	
verifyStruct.o: verifyStruct.s
	gcc -g -Wall -c verifyStruct.s -o verifyStruct.o
	
fill_matrix.o: fill_matrix.c
	gcc -g -Wall -c fill_matrix.c -o fill_matrix.o
	
find_container_position.o: find_container_position.c
	gcc -g -Wall -c find_container_position.c -o find_container_position.o
	
verifyIfRefrigerated.o: verifyIfRefrigerated.s
	gcc -g -Wall -c verifyIfRefrigerated.s -o verifyIfRefrigerated.o
	
energy_needed.o: energy_needed.c
	gcc -g -Wall -c energy_needed.c -o energy_needed.o
	
clean:
	rm -f *.o main

run: main
	./main
