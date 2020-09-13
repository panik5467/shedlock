#### Tutorial su Shedlock con Spring Batch

Documentazione libreria Shedlock:\
https://github.com/lukas-krecan/ShedLock/blob/master/README.md

#### Creare un container Docker mysql

##### Passo 1: container mysql
docker pull mysql:8.0.1 \
docker run -p 3306:3306 --name sql-container -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0.1
##### Passo 2: container phppmyadmin

docker pull phpmyadmin/phpmyadmin:latest
docker run --name phpmyadmin-container -d --link sql-container:db -p 8081:80 phpmyadmin/phpmyadmin

console phpmyadmin: http://localhost:8081/ \
user: root \
pass: root