package com.enzoracca.shedlock.runner;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class BatchRunner {

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    //lockAtMostFor: Puoi anche impostare l'attributo lockAtMostFor che specifica per quanto tempo deve essere mantenuto il blocco nel caso in cui il nodo in esecuzione muoia. Questo è solo un fallback, in circostanze normali il blocco viene rilasciato non appena le attività terminano. Devi impostare lockAtMostFor su un valore che è molto più lungo del normale tempo di esecuzione. Se l'attività richiede più tempo di lockAtMostFor, il comportamento risultante potrebbe essere imprevedibile (più di un processo manterrà effettivamente il blocco).If you do not specify lockAtMostFor in @SchedulerLock default value from @EnableSchedulerLock will be used.
//    ESEMPIO: @Scheduled(cron = "0 */15 * * * *")
//    @SchedulerLock(name = "scheduledTaskName", lockAtMostFor = "14m", lockAtLeastFor = "14m")
//    public void scheduledTask() {
//        // do something
//    }
    @Scheduled(cron = "0 */2 * * * *")
    @SchedulerLock(name = "TaskScheduler_scheduledTask",
            lockAtLeastFor = "1m", lockAtMostFor = "1m")
    public void run() throws Exception {
        jobLauncher.run(job, new JobParametersBuilder().addDate("date", new Date()).toJobParameters());
    }
}
