#!/bin/bash

JAVA_VERSION=8
PACKAGES="se.itu.hunt"

javadoc --source-path src/ -d docs -link http://docs.oracle.com/javase/${JAVA_VERSION}/docs/api/ $PACKAGES 
