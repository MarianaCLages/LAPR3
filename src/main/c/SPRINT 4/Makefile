main: main.o verifyStruct.o fill_matrix.o find_container_position.o verifyIfRefrigerated.o energy_needed.o print_container_array.o print_menu.o
	gcc -g -Wall main.o verifyStruct.o fill_matrix.o find_container_position.o verifyIfRefrigerated.o energy_needed.o print_container_array.o print_menu.o -o main

main.o: main.c
	gcc -g -Wall -c main.c -o main.o
	
print_menu.o: print_menu.c
	gcc -g -Wall -c print_menu.c -o print_menu.o
	
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
	
print_container_array.o: print_container_array.c
	gcc -g -Wall -c print_container_array.c -o print_container_array.o
	
clean:
	rm -f *.o main

run: main
	./main
