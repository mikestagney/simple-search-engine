# Simple Search Engine

Command line app to search text loaded from a file with 3 different search strategies. 

## Things learned 

First time using an inverted index

Strategy design pattern used to change the 3 search strategies at runtime

### Details

#### The directory contains 6 files: 

* Main.java - handles users input and menu, sets up SearchEngine object
* SearchEngine.java - Object holds the text data, inverted index, search method, and prints out results
* SearchStrategies.java - Interface for the three search strategies
* SearchAny.java - class that searches for lines with any matching words
* SearchAll.java - class that searches for lines that contain all matching words
* SearchNone.java - class that searches for lines with none of the matching words

Sixteenth project created for JetBrains Academy Java Developer course - hard level project.

### How to Run

The app will not run without the correct command line argument. 

Parses one argument: --data: filename to read the input data
 
### Sample input and output:

\#\#\# Sample data file ###

Dwight Joseph djo@gmail.com\
Rene Webb webb@gmail.com\
Katie Jacobs\
Erick Harrington harrington@gmail.com\
Myrtle Medina\
Erick Burgess

=== Menu ===
1. Find a person
2. Print all persons
0. Exit\
\> 1

Select a matching strategy: ALL, ANY, NONE\
\> ANY

Enter a name or email to search all suitable people.\
\> Katie Erick QQQ

3 persons found:\
Katie Jacobs\
Erick Harrington harrington@gmail.com\
Erick Burgess

=== Menu ===
1. Find a person
2. Print all persons
0. Exit\
\> 1

Select a matching strategy: ALL, ANY, NONE\
\> ALL

Enter a name or email to search all suitable people.\
\> Harrington Erick

1 persons found:\
Erick Harrington harrington@gmail.com

=== Menu ===
1. Find a person
2. Print all persons
0. Exit\
\> 1

Select a matching strategy: ALL, ANY, NONE\
\> NONE

Enter a name or email to search all suitable people.\
\> djo@gmail.com ERICK

3 persons found:\
Katie Jacobs\
Myrtle Medina\
Rene Webb webb@gmail.com
