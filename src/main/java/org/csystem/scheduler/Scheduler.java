package org.csystem.scheduler;

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
    public Scheduler(long interval, TimeUnit timeUnit)
    {
        throw new UnsupportedOperationException("Todo");
    }
    public Scheduler(long intervalInMillis)
    {
        throw new UnsupportedOperationException("Todo");
    }
    public Scheduler(long interval, long delay, TimeUnit timeUnit)
    {
        throw new UnsupportedOperationException("Todo");
    }
    public Scheduler(long intervalInMillis, long delay)
    {
        throw new UnsupportedOperationException("Todo");
    }
    public final Scheduler schedule(Runnable task)
    {
        throw new UnsupportedOperationException("Todo");
    }
    public final void cancel()
    {
        throw new UnsupportedOperationException("Todo");
    }
}
