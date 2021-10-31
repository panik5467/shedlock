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
