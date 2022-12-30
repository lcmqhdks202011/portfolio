#!/bin/bash
for fichier in $1/*
do
	echo $1"/"$(basename $fichier) "->" $2"/"$(basename $fichier)
	python3 transf.py ${1} ${2} $(basename $fichier)
done