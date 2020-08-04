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

*Step by step thoughts and implementation can be found in bash and SQL scripts*

## Architecture
HTTP endpoints:
- Java Springboot Application 
    - main HTTP service
- Basic Java Application 
    - to prebuild outdated table, where most outdated page from each top10 category resides in
- cURL to call the REST endpoints

Server:
- AWS EC2 t2.micro instance (free tier)
    - [Specs](https://aws.amazon.com/ec2/instance-types/t2/)

Script Automation:
- Bash scripts
- SQL scripts
- Cron jobs


##Getting Started

## Setting up locally
#### 1. Prerequisites
- OpenJDK 11/12
- Gradle 6.4
- MariaDB v10.4
- Docker

#### 2. Run with scripts
```bash
cd demo-lynx/scripts && ./1_download.sh && ./2_init_container.sh && ./3_load_data.sh && ./4_indexing.sh
```

#### 3. Prehandle outdated data and populate to database
```bash
cd simplewiki1

# Build gradle
gradle build

# Run gradle to prehandle outdated pages associated with each category
gradle run
```

#### 4. Run HTTP service
```bash
cd demo-lynx/

./gradlew bootRun
```

#### 5. cURL
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
______________________________________________________

### Setting up in a Cloud Server
1. Create an AWS EC2 instance
2. Install docker on EC2 Amazon Linux 2 [here](https://docs.aws.amazon.com/AmazonECS/latest/developerguide/docker-basics.html)
3. Install Java 11 on EC2 Amazon Linux
    ```
    sudo amazon-linux-extras install java-openjdk11
    ```
4. Install Gradle libraries on instance via [SDKMAN](https://sdkman.io/install), make sure source path is correct on AWS EC2
5. Clone repository. Make sure to run all scripts in `scripts` prior to building gradle
6. Create a new Cronjob via 
    ```
    crontab -e
    ```
7. Add the scripts for cron to run 1-2 min from current time
    ```
    21 16 * * * cd ~/demo-lynx/scripts && ./1_download.sh && ./2_init_container.sh && ./3_load_data.sh && ./4_indexing.sh
    ```
   
   ### Prehandle outdated data and populate to database
8. Once cron is done
    ```bash
   # change directory into application
<<<<<<< Updated upstream
   cd demo-lynx/simplewiki
=======
   cd demo-lynx/simplewiki1
>>>>>>> Stashed changes
   
   # Build gradle
   gradle build
   
   # Run gradle to prehandle outdated pages associated with each category
   gradle run
    ```
9. Change directory back to 
    ```bash
   cd demo-lynx/
   
   # Run backend REST service
   ./gradlew bootRun
    ```

10. To run REST service with cURL given the category title:
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