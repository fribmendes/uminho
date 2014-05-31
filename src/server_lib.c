
#include <unistd.h>		// Open (Read from named pipe)
#include <stdio.h>		// printf
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>

#include "server.h"

int incrementar(char* prefix[], int value){
	if(!prefix) return -1;
	char count[10];
	sprintf(count, "%d", value);
	char* new_str = (char*) calloc( strlen( prefix[0] ) + strlen(count) + 2, sizeof(char) ); // 4 => '\0', ';' and "2"
	sprintf(new_str, "%s;%s%s;", prefix[0], "2", count);

	new_str = (char*)realloc( new_str, strlen(new_str) + strlen( prefix[1] ) + 1 ); // 1 => '\0'
	sprintf(new_str, "%s%s", new_str, prefix[1]);

	for(int i = 2; prefix[i]; i++){
		new_str = (char*)realloc( new_str, strlen(new_str) + strlen( prefix[i] ) + 2 ); // 2 => '\0' and :
		sprintf(new_str, "%s:%s", new_str, prefix[i]);
	}

	int fd = open(SERVER_NAME, O_WRONLY);

	write(fd, new_str, strlen(new_str) + 1);

	close(fd);

	free(new_str);
	return 0;
}


int agregar(char *prefix[], unsigned level, char *path){
	if(!prefix) return -1;
	char lvl[10];
	sprintf(lvl, "%d", level);
	char* new_str = (char*) calloc( strlen( prefix[0] ) + strlen(path) + strlen(lvl) + 4, sizeof(char) ); // 4 => ;, '\0' and "3"
	sprintf(new_str, "%s;%s%s;%s", prefix[0], "3", lvl, path);

	new_str = (char*)realloc( new_str, strlen(new_str) + strlen( prefix[1] ) + 2 ); // 2 => ; and '\0'
	sprintf(new_str, "%s;%s", new_str, prefix[1]);

	for(int i = 2; prefix[i]; i++){
		new_str = (char*)realloc( new_str, strlen(new_str) + strlen( prefix[i] ) + 2 ); // 2 => ; and '\0'
		sprintf(new_str, "%s:%s", new_str, prefix[i]);
	}



	int fd = open(SERVER_NAME, O_WRONLY);

	write(fd, new_str, strlen(new_str) + 1);

	close(fd);

	free(new_str);

	return 0;
}
