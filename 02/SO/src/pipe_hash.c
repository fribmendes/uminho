#include <string.h>
#include <stdlib.h>
#include <strutil.h>
#include "pipe.h"
#include "pipe_hash.h"



#include <stdio.h>
#include <unistd.h>

typedef struct node {
  Pipe content;
  struct node* next;
} *PipeBucket;

struct pipe_table {
  int size;
  PipeBucket* table;
};

/** Creates a hash bucket with a Pipe of given name, generating the file descriptors for communication
  */
static PipeBucket newPipeBucket(char* name) {
    PipeBucket new = (PipeBucket)malloc( sizeof (struct node) );
    new -> content = newPipe(name);
    new -> next = NULL;

    return new;
}


/* djb2 hash function created by Dan Bernstein */
static unsigned int hash( char *str ) {
   unsigned int hash = 5381;
    int c;

        while ( (c = *str++) )
            hash = ((hash << 5) + hash) ^ c;

        return hash;
}


/** Iterates the table looking for the Pipe with the given name
  * ret will contain the address of the bucket where it should be (whether or not it in fact is)
  * Return will be 1 or 0 depending on existance
  */
static int get_pipe_address(PipeTable a, char* name, PipeBucket** ret) {
    unsigned int index = hash(name) % a -> size;                    // Get table index
    int found = 0;                                                  // Control variable
    PipeBucket *it = &(a -> table)[index];                              // Iterator for that bucket
    PipeBucket *head = it;                                              // Saving the head of the bucket

    while (*it && !found) {                                         // Scanning the bucket for the pipe
        if ( strcmp(name, getPipeName( (*it) -> content) ) == 0 )
            found = 1;
        else
            it = &( (*it) -> next );                                // Saving the new bucket address
    }

    if (! *it)
        *ret = head;                                                // Returning the position where the new bucket should be inserted
    else
        *ret = it;                                                  // Returning the position where the bucket is

    return found;                                                   // This allows us to know if we should create or update what's in ret
}

/** Tries to find the Pipe node
  * If found, returns its address
  * Otherwise adds it to the table
  */
static int get_pipe_ptr(PipeTable pt, char* name, PipeBucket** ret) {
    PipeBucket new;
    PipeBucket* it;
    int res = get_pipe_address(pt, name, &it);

    if(!res) {
        new = newPipeBucket(name);
        new -> next = *it;
        *it = new;
    }

    *ret = it;
    return res;
}

/**Deletes the given PipeBucket*/
static void deletePipeBucket (PipeBucket b) {
    PipeBucket bird;                                // Auxiliary iterator that deletes everything behind
    PipeBucket it = b;                              // Main iterator that leads the way

    while (it) {
        bird = it;
        it = it -> next;
        deletePipe(bird -> content);
        free(bird);
    }
}

/*Deletes the whole Pipe hash table*/
void deletePipeTable(PipeTable pt) {
  if (pt) {
    for (int i = 0; i < pt -> size; i++)
        deletePipeBucket( pt -> table[i] );

    free(pt -> table);
    free(pt);
  }
}

/*Creating a new Pipe Table*/
PipeTable newPipeTable(int size) {
    PipeTable pt = (PipeTable)malloc(sizeof (struct pipe_table) );
    pt -> size = size;
    pt -> table = (PipeBucket*)calloc( size, sizeof(PipeBucket) );

    return pt;
}

/** Checks if a district exists, creating it if so.
  * ret shall contain the file descriptors created for father-child communication
  * Returns 1 if needed to create a new father-son Pipe structure
  * Returns 0 if it already existed
  */
int pipe_writer(PipeTable pt, char* name, int** ret) {
    if (!pt) return 0;
    PipeBucket* it;
    int res = get_pipe_ptr(pt, name, &it);
    getDescriptors( (*it) -> content, ret );
    return res;
}

/**Sets the PID of the Pipe accessed by "name"*/
void set_pid(PipeTable pt, char*name, int pid) {
  if (pt) {
    PipeBucket* it;
    get_pipe_ptr(pt, name, &it);
    setChildPid(pid, (*it) -> content);
  }
}

/**Closes every Pipe in the given PipeTable*/
void shutdown_children(PipeTable pt) {
  for (int i = 0; i < (pt -> size); i++) {
    PipeBucket it = (pt -> table)[i];
    while (it) {
      closeChild(it -> content);
      it = it -> next;
    }
  }
}


/** Iterates the hash searching for the element with given pid
  * Removes it if found. In that case returns the name of the Pipe structure
  * Otherwise returns NULL
  */
char* bury_dead_child(PipeTable pt, int pid) {
  for (int i = 0; i < (pt -> size); i++) {
    PipeBucket it = (pt -> table)[i];
    PipeBucket b = it;

    while (it) {
      if( getChildPid(it -> content) == pid ) {
        char* name = str_dup( getPipeName(it -> content) );
        deletePipe(it -> content);
        if (it == (pt -> table)[i])   (pt -> table)[i] = it -> next;
        else                          b -> next = it -> next;
        free(it);
        return name;
      }

      b = it;
      it = it -> next;

    }
  }

  return NULL;
}

