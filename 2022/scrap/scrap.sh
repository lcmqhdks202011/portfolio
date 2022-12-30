#!/bin/bash

mkdir $1

chmod +rwx $1

cd $1

SOURCE="https://www.w3.org/Style/CSS/Test/CSS3/Selectors/current/html/full/flat/"
curl -s $SOURCE | grep -o "css3-modsel-.*\.html" > temp

i=0

while read line || [ -n "$line" ]; do

  i=$((i+1))
  url="$SOURCE${line}"
  echo "${i} : ${line}"
  curl -s $url > ${line}

  sleep 2

done < temp

rm temp