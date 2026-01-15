package org.csystem.scheduler;

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

    public Scheduler(long interval, TimeUnit timeUnit)
    {
        this(timeUnit.toMillis(interval));
    }
    public Scheduler(long intervalInMillis)
    {
        this(intervalInMillis, 0);
    }
    public Scheduler(long interval, long delay, TimeUnit timeUnit)
    {
        this(timeUnit.toMillis(interval), timeUnit.toMillis(delay));
    }
    public Scheduler(long intervalInMillis, long delay)
    {
        m_timer = new Timer();
        m_intervalInMillis = intervalInMillis;
        m_delay = delay;
    }
    public final Scheduler schedule(Runnable task)
    {
        m_timer.scheduleAtFixedRate(createTimerTask(task), m_delay, m_intervalInMillis);
        return this;
    }
    public final void cancel()
    {
        m_timer.cancel();
    }
}
