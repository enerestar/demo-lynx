to run
```bash
./gradlew bootRun
```

to query REST endpoints
```bash
curl localhost:8080/api/v1/categories 
curl localhost:8080/api/v1/categories/top10/{categorytitle}
curl localhost:8080/api/v1/categories/other/{categorytitle}
 
 
curl -X POST localhost:8080/api/v1/sql -H 'Content-Type: application/x-www-form-urlencoded' -d 'SELECT cat_title, diff FROM outdated WHERE cat_title = 'Living_people';'
curl -X POST localhost:8080/api/v1/sql -H 'Content-Type: application/x-www-form-urlencoded' -d 'SELECT * FROM outdated WHERE cat_title = 'Living_people';'

```