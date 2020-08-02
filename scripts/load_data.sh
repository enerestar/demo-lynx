#!/bin/bash

MARIADB_NAME="some-mariadb"

# Restore simplewiki database from sql file into mariadb
docker exec -i $MARIADB_NAME mysql -uroot -pmypass simplewiki < simplewiki-latest-category.sql
docker exec -i $MARIADB_NAME mysql -uroot -pmypass simplewiki < simplewiki-latest-categorylinks.sql
docker exec -i $MARIADB_NAME mysql -uroot -pmypass simplewiki < simplewiki-latest-page.sql
docker exec -i $MARIADB_NAME mysql -uroot -pmypass simplewiki < simplewiki-latest-pagelinks.sql
