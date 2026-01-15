package org.csystem.scheduler.timeout;

import java.util.concurrent.TimeUnit;


/**
 * Sınıf SchedulerLib içerisinde yazılacaktır
 * Sınıfın kullanımına ilişkin örnekleri inceleyiniz
 * Sınıfın public bölümünü değiştirmeden istediğiniz eklemeyi yapabilirsiniz.
 *
 *
 * return new CountDownSchedulerX(TOTAL_SECONDS, PERIOD_IN_SECONDS, TimeUnit.SECONDS) {
 *     public void onStart()
 *     {
 *         //Geri sayım başlatıldığında bir kez çağrılacak
 *     }
 *
 *     public void onTick(long remainingMilliseconds)
 *     {
 *         //Her saniyede bir çağrılacak ve kalan zaman (milisaniye cinsinden) argüman olarak geçilmiş olacak
 *     }
 *
 *     public void onFinish()
 *     {
 *         //10 saniye sonunda yani geri sayım tamamlandığında çağrılacak
 *     }
 * }.startX();
 */

public abstract class CountDownSchedulerX extends CountDownScheduler {

    protected CountDownSchedulerX(long durationInFuture, long countDownInterval, TimeUnit timeUnit)
    {
        this(timeUnit.toMillis(durationInFuture), timeUnit.toMillis(countDownInterval));
    }

    protected CountDownSchedulerX(long millisInFuture, long countDownInterval)
    {
        super(millisInFuture, countDownInterval);
    }

    public abstract void onStart();

    public void startX()
    {
        onStart();
        start();
    }
}
