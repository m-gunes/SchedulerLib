package org.csystem.scheduler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Açıklamalar:
 *
 * Sınıf Timer sınıfının Java 8+ uyumlu olarak da kullanılabilen daha gelişmiş biçimidir.
 * Sınıfı Timer sınıfı kullanarak ancak türetme yapmadan yazınız.
 * Sınıfın public bölümü değiştirilmeden istediğiniz eklemeyi yapabilirsiniz.
 * Bir Scheduler nesnesi ile yalnızca bir kez schedule işlemi yapılabilecektir.
 */

public class Scheduler {
    private final Timer m_timer;
    private final long m_intervalInMillis;
    private final long m_delay;

    private static TimerTask createTimerTask(Runnable task)
    {
        return new TimerTask() {
            public void run()
            {
                task.run();
            }
        };
    }

    private static TimerTask createCancelTimerTask(Runnable task)
    {
        return new TimerTask() {
            public void run()
            {
                task.run();
            }
        };
    }

    public Scheduler(long intervalInMillis, long delay)
    {
        m_timer = new Timer();
        m_intervalInMillis = intervalInMillis;
        m_delay = delay;
    }

    public static Scheduler of(long interval, TimeUnit timeUnit)
    {
        return of(timeUnit.toMillis(interval));
    }

    public static Scheduler of(long intervalInMillis)
    {
        return of(intervalInMillis, 0);
    }

    public static Scheduler of(long interval, long delay, TimeUnit timeUnit)
    {
        return of(timeUnit.toMillis(interval), timeUnit.toMillis(delay));
    }

    public static Scheduler of(long intervalInMillis, long delay)
    {
        return new Scheduler(intervalInMillis, delay);
    }

    public final Scheduler schedule(Runnable task)
    {
        m_timer.scheduleAtFixedRate(createTimerTask(task), m_delay, m_intervalInMillis);
        return this;
    }

    public final Scheduler schedule(Runnable task, Runnable cancelTask)
    {
        m_timer.scheduleAtFixedRate(createTimerTask(task), m_delay, m_intervalInMillis);
        m_timer.scheduleAtFixedRate(createCancelTimerTask(cancelTask), m_delay, m_intervalInMillis);
        m_timer.cancel();
        return this;
    }

    public final Scheduler schedule(Runnable task, LocalDateTime dateTime) // timeout olucak. yani belirlenen taski zamani geldiginde calistiriracak
    {
        m_timer.schedule(createTimerTask(task), Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()), m_intervalInMillis);
        return this;
    }

    public final void cancel()
    {
        m_timer.cancel();
    }
}
