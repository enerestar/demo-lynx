#!/bin/bash

# declare array variable
declare -a arr=(
    "simplewiki-latest-category.sql.gz"
    "simplewiki-latest-categorylinks.sql.gz"
    "simplewiki-latest-page.sql.gz"
    "simplewiki-latest-pagelinks.sql.gz")

# loop through array and download to current directory
url="https://dumps.wikimedia.org/simplewiki/latest/"
for i in "${arr[@]}"
do
    echo "$i"
    $(curl -LO "{$url}${i}")
    gunzip -df "${i}"
done