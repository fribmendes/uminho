#ifndef AUTHOR_CATALOG_AUTHORS_H_
#define AUTHOR_CATALOG_AUTHORS_H_

#include <string.h>
#include <avl.h>
#include "author_tree.h"
#include "author_catalog.h"

typedef struct year_publ_pair		YearPublPair;
typedef struct co_author_publ_pair 	CoAuthorPublPair;
typedef struct author_info			AuthorInfo;
typedef struct year_info			YearInfo;

struct year_publ_pair {
    int year;
    int nr_publications;
};

AVL_DEF_HEADER(YearPublPair, int)

struct co_author_publ_pair {
    Author coauthor;
    int nr_publications;
};

AVL_DEF_HEADER(CoAuthorPublPair, Author)

struct author_info {
    Author author;
    YearPublPairAVL publications_info;
    CoAuthorPublPairAVL coauthors_info;
};

AVL_DEF_HEADER(AuthorInfo, Author)

typedef AuthorInfoAVL				AuthorInfoTree;

int authorInfoTreeInsert(AuthorInfoTree tree, AuthorInfo new);
int authorInfoTreeYield(AuthorInfoTree tree, AuthorInfo *ret);
void authorInfoTreeRewindGenerator(AuthorInfoTree tree);
void authorInfoTreeDestroy(AuthorInfoTree tree);
AuthorInfoTree authorInfoTreeNew();
AuthorInfoTree authorInfoTreeClone(AuthorInfoTree tree);

AuthorInfo newAuthorInfo(Author name);
void deleteAuthorInfo(AuthorInfo info);
int authorInfoAddCoAuthor(AuthorInfo author, Author coauthor);
int authorInfoAddYear(AuthorInfo author, int year);

#ifdef DEBUG2
    #include <stdio.h>
    void authorInfoTreePrint(AuthorInfoTree);
#endif

#endif

