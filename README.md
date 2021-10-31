#### Sixth step: let's configure Shedlock

    @Scheduled(cron = "0 */2 * * * *")
    @SchedulerLock(name = "TaskScheduler_scheduledTask",
            lockAtLeastFor = "1m", lockAtMostFor = "1m")

Let's annotate the run method with @SchedulerLock. Then Shedlock will check the lock of this method for every instance.

Let's analyze the lockAtMostFor e lockAtLeastFor parameters:

lockAtMostFor specifies the max time for that must keep the lock. This is just a fallback, so it is useful when a node dead, because the default when the job finished, Shedlock releases the lock. If we don't specify this parameter in the @SchedulerLock annotation, then it's use the default parameter (defaultLockAtMostFor = "1m" inside the @EnableSchedulerLock).
lockAtLeastFor specifies the min time for that must keep the lock. It has the purpose to prevent the execution of the jobs from different nodes in parallel.

#### Manage multiple instances of Spring Batch with Shedlock

My post about this project (English language): https://www.vincenzoracca.com/en/blog/framework/spring/shedlock/
My post about this project (Italian language): https://www.vincenzoracca.com/blog/framework/spring/shedlock/

Shedlock's documentation:\
https://github.com/lukas-krecan/ShedLock/blob/master/README.md

#### Create a MySQL Docker container

##### Step 1: container mysql
docker pull mysql:8.0.1 \
docker run -p 3306:3306 --name sql-container -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0.1
##### Step 2: container phppmyadmin

docker pull phpmyadmin/phpmyadmin:latest
docker run --name phpmyadmin-container -d --link sql-container:db -p 8081:80 phpmyadmin/phpmyadmin

console phpmyadmin: http://localhost:8081/ \
user: root \
pass: root
