### Info we have:
- Data [dumps](https://dumps.wikimedia.org/simplewiki/latest/)
- Database [schema](https://meta.wikimedia.org/wiki/Data_dumps/What%27s_available_for_download#Database_tables)

### Files we will download 
1. CATEGORY 
    - simplewiki-latest-category.sql.gz 
    - Fields: cat_id, cat_title, cat_pages
2. CATEGORYLINKS 
    - simplewiki-latest-categorylinks.sql.gz
    - Fields: cl_from, cl_to, cl_sortkey
3. PAGE 
    - simplewiki-latest-page.sql.gz
    - Fields: page_id, page_title, page_touched
4. PAGELINKS 
    - simplewiki-latest-pagelinks.sql.gz
    - Fields: pl_from, pl_title

###Database we will use:
- Since WIKIPEDIA dumps their SQL files using mariaDB v10.4, we shall use mariaDB v10.4 to restore the database from the sql files.
- To ensure environment is easy to replicate, we will be using **MariaDB via docker** to setup.

#####STEP BY STEP THOUGHTS AND IMPLEMENTATION CAN BE FOUND IN BASH AND SQL SCRIPTS.

## Architecture
REST endpoints:
- Java Springboot Application
- Basic Java Application - for SQL queries that requires more pre-handling
- cURL to call the REST endpoints

Server:
- Digital Ocean (already have subscription)

Script Automation:
- Bash scripts
- SQL scripts
- Cron jobs


###To Run
To run SQL prehandling
```
gradle build
gradle run
```

To run Java Springboot Application backend service
```
./gradlew bootRun
```

To run REST service with cURL given the category title:
```
curl localhost:8080/api/v1/categories 
curl localhost:8080/api/v1/categories/top10/{categorytitle}
curl localhost:8080/api/v1/categories/other/{categorytitle} 
eg. 
curl localhost:8080/api/v1/categories/top10/Living_people
```

To run REST service with RAW SQL query:
```
eg. 
curl -X POST localhost:8080/api/v1/sql -H 'Content-Type: application/x-www-form-urlencoded' -d 'SELECT cat_title, diff FROM outdated WHERE cat_title = 'Living_people';'
curl -X POST localhost:8080/api/v1/sql -H 'Content-Type: application/x-www-form-urlencoded' -d 'SELECT * FROM outdated WHERE cat_title = 'Living_people';'
```

