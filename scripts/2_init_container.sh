#!/bin/bash

MARIADB_NAME="some-mariadb"

# inspect and create a separate docker network if there is no such network
if docker network inspect mariadb-network >/dev/null 2>&1; then
    echo "mariadb-network already exists"
else 
    echo "creating mariadb-network"
    docker network create --driver bridge mariadb-network
fi

# create a mapping host to container to allow remote access
# port mapping. 3308:3306 means that the MySQL running in the container at port 3306 is mapped to the localhost of the host machine at port 3308. You can use a different port as wel
docker run --name $MARIADB_NAME --network mariadb-network -e MYSQL_ROOT_PASSWORD=mypass -p 3308:3306 -d mariadb/server:10.4

# mariadb takes time to open the socket
# otherwise it will throw ERROR 2002 (HY000): Can't connect to local MySQL server through socket '/var/run/mysqld/mysqld.sock' (2)
sleep 30

# create a new database
docker exec -i $MARIADB_NAME /bin/sh -c 'mysql -uroot -pmypass -e "CREATE DATABASE IF NOT EXISTS simplewiki;"'

# create user, grant permissions
docker exec -i $MARIADB_NAME mysql -uroot -pmypass simplewiki < init-priv-and-grants.sql
