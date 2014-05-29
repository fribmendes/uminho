#ifndef STRUTIL_H_
#define STRUTIL_H_

#include <string.h>
#include <ctype.h>
#include <stdlib.h>

char* strtrim(char *str);
char* str_dup(char* str);
char* str_slice(char *str, int i);

#endif
