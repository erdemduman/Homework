all:cmdls cmdcat cmdwc exe 

cmdls: cmd_ls.o
	gcc -o cmdls cmd_ls.o

cmd_ls.o: cmd_ls.c
	gcc -c cmd_ls.c

cmdwc: cmd_wc.o
	gcc -o cmdwc cmd_wc.o

cmd_wc.o: cmd_wc.c
	gcc -c cmd_wc.c

cmdcat: cmd_cat.o
	gcc -o cmdcat cmd_cat.o

cmd_cat.o: cmd_cat.c
	gcc -c cmd_cat.c

exe: 151044005_main.o
	gcc -o exe 151044005_main.o

151044005_main.o: 151044005_main.c
	gcc -c 151044005_main.c

clean:
	rm -f *.o
	rm -f exe cmdls cmdcat cmdwc
