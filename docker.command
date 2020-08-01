docker ps / docker ps -a

<!——————  mysql ———————>
docker run --name mysql-partion1 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 -d mysql:latest
docker exec -it mysql-partion1 bash
mysql -uroot -p1234 -h localhost

<!——————  postgres ———————>
docker run --name mypostgres -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres:latest
docker exec -it mypostgres /bin/bash
psql -U postgres -W

