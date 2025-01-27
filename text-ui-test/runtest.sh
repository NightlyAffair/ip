#!/usr/bin/env bash

DATA_DIR="./data"
TASKS_FILE="$DATA_DIR/tasks.txt"
EXPECTED_FILE="./EXPECTED_TASKS.TXT"

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# delete saved file from previous run
if [ -e "$TASKS_FILE" ]
then
  rm "$TASKS_FILE"
fi


# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin Cheryl < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
else
    echo "Test result: FAILED"
    exit 1
fi

# verify that tasks are saved to the file upon program closure
if [ -e "$TASKS_FILE" ]
then
    echo "Saved file exists. Checking contents..."
    # Replace with the expected saved file for comparison
    EXPECTED_FILE="EXPECTED_TASKS.TXT"
    cp $TASKS_FILE TASKS-UNIX.TXT
    cp $EXPECTED_FILE EXPECTED_TASKS-UNIX.TXT
    dos2unix TASKS-UNIX.TXT EXPECTED_TASKS-UNIX.TXT
    diff TASKS-UNIX.TXT EXPECTED_TASKS-UNIX.TXT
    if [ $? -eq 0 ]
    then
        echo "Saved file comparison: PASSED"
        exit 0
    else
        echo "Saved file comparison: FAILED"
        exit 1
    fi
else
    echo "Test failed: Saved file (tasks.txt) was not created."
    exit 1
fi