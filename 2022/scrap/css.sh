#!/bin/bash


for fichier in `ls *.html`
do
 echo $fichier
 python3 css.py $fichier
 for css_fichier in `ls *.css`
 do
    sass $css_fichier temp.css
    Error=$(head -1 temp.css| tr -d "/*")
    isError=$(echo $Error | grep -c "Error")
        if [[ $isError -eq 1 ]]
        then
            echo "$Error | $fichier" >> errors.txt

        fi
    rm $css_fichier temp.css temp.css.map
 done

done

python3 readError.py

rm errors.txt