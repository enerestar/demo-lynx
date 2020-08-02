#!/bin/bash

MARIADB_NAME="some-mariadb"

docker exec -i $MARIADB_NAME mysql -uroot -pmypass simplewiki < indexing.sql

