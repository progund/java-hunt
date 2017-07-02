#!/bin/bash

usage() {
    echo "./run.sh OPTION"
    echo "where OPTION is one of:"
    echo " --gui        run the game in GUI mode"
    echo " --text       run the game in text mod"
    exit 1
}

echo "first: $1"
if [ "$#" -eq 1 ]
then
    if [[ "$1" == '--gui' ]]
    then
        java -cp bin se.itu.hunt.MainGUI
    elif [[ "$1" == '--text' ]]
    then
        java -cp bin se.itu.hunt.MainTextGame
    else
        usage
    fi
else
    usage
fi
