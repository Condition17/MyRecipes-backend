docker-compose -f ./db/docker-compose.yml up -d
docker cp ./generatedb.sql myrecipespg:generatedb.sql
docker exec myrecipespg psql -U root -d recipes -f generatedb.sql