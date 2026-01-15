package org.csystem.scheduler.timeout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Açıklamalar:
 *
 * Sınıfın LocalTime parametreli ctor'u ile yaratılan nesne için, aldığı zaman geldiğinde start metodu ile verilen callback'e ilişkin metot çağrılacaktır.
 *
 * Sınıfın LocalDateTime parametreli ctor'u ile yaratılan nesne için, aldığı tarih-zaman geldiğinde start metodu ile verilen callback'e ilişkin metot çağrılacaktır
 *
 * Sınıfın LocalDate parametreli ctor'u ile yaratılan nesne için, aldığı tarih geldiğinde (geceyarısı) geldiğinde start metodu ile verilen callback'e ilişkin metot çağrılacaktır.
 *
 * Sınıf tarihin, zamanın ya da tarih zamanın geçmiş olup olmadığını kontrol etmeyecektir.
 *
 * Sınıfı Timer kullanarak yazınız
 *
 * Bir Alarm nesnesinden yalnızca bir kez start yapılabilecektir.
 *
 * Sınıfın cancel metodu Alarm'ı iptal eder.
 */

public class Alarm {
    private final LocalDateTime m_dateTime;
    private final Timer m_timer;

    private TimerTask createTimerTask(Runnable task)
    {
        return new TimerTask() {

            public void run()
            {
                var now = LocalDateTime.now();
                if (now.isBefore(m_dateTime))
                    return;

                task.run();
                m_timer.cancel();
            }
        };
    }

    public Alarm(LocalTime time)
    {
        // su an ki tarihin zamanini koyuyoruz
        this(time.atDate(LocalDate.now()));
    }

    public Alarm(LocalDateTime dateTime)
    {
        m_dateTime = dateTime;
        m_timer = new Timer();
    }

    public Alarm(LocalDate date)
    {
        this(date.atTime(0,0));
    }

    public void start(Runnable task)
    {
        m_timer.scheduleAtFixedRate(createTimerTask(task), 0, 1000);
    }

    public void cancel()
    {
        m_timer.cancel();
    }
}
