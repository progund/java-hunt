#!/bin/bash

JAVA_VERSION=8
PACKAGES="se.itu.hunt"

javadoc --source-path src/ -d doc -link http://docs.oracle.com/javase/${JAVA_VERSION}/docs/api/ $PACKAGES 
