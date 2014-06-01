#include "aggregate.h"

struct aggregate_s {
	char* name;
	int count;
	Aggregation subaggregate;
};

void countInc(Aggregate ag, int val) {
	ag -> count += val;
}

void setName(Aggregate ag, char* name) {
	ag -> name = str_dup(name);
}

int getCount(Aggregate ag) {
	return ag -> count;
}

char* getAggregateName(Aggregate ag) {
	return ag -> name;
}

Aggregation getSubAggregate(Aggregate ag) {
	return ag -> subaggregate;
}

int hasSubAggregate(Aggregate ag) {
	return ag -> subaggregate != NULL;
}

int createSubAggregate(Aggregate ag) {
	int res = 0;

	if ( !hasSubAggregate(ag) ) {
		ag -> subaggregate = newAggregation(AGGREGATION_SIZE);
		res = 1;
	}

	return res;
}

Aggregate newAggregate() {
	return (Aggregate)malloc( sizeof(struct aggregate_s) );
}

Aggregate newAggregateWith(char* name, int count) {
	Aggregate new = (Aggregate)malloc( sizeof(struct aggregate_s) );
	new -> name = str_dup(name);
	new -> count = count;
	new -> subaggregate = NULL;
	return new;
}

void deleteAggregate(Aggregate a) {
	free(a -> name);
	deleteAggregation(a -> subaggregate);
	free(a);
}

#ifdef DEBUG

#include <stdio.h>
void printAggregate(Aggregate a) {
	printf("AGGREGATE NAME: %s\n", a -> name);
	printf("TOTAL COUNT: %d\n", a -> count);
	printf("SUBAGGREGATIONS:\n");
	printAggregation(a -> subaggregate);
	printf("\n");
}

#endif
