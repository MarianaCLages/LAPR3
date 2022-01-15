main: main.o verifyStruc.o
	gcc -g -Wall main.o verifyStruc.o -o main

main.o: main.c
	gcc -g -Wall -c main.c -o main.o
	
verifyStruc.o: verifyStruc.s
	gcc -g -Wall -c verifyStruc.s -o verifyStruc.o
	
clean:
	rm -f *.o main

run: main
	./main
