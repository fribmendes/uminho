#ifndef READING_H_
#define READING_H_

int read_file(char* filename);

char* getReadStats();
char* getAuthorStats();
int getAuthorsBy(char initial, char** list, int number);
void resetAuthorBy(char initial);

void initializeGestauts();
void leaveGestauts();

#endif
