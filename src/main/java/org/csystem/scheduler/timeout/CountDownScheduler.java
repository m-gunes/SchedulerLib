package org.csystem.scheduler.timeout;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Sınıfın ctor'ları ilgili parametrelere göre geri sayımın her adımında onTick metodunu çağıracaktır.
 * Geri sayım tamamlandığında ise onFinish metodu çağrılacaktır. onTick metoduna kalan milisaniye sayısı argüman olarak geçilecektir.
 * Sınıfı Timer sınıfını kullanarak yazınız
 * Sınıfı bir CountDownScheduler nesnesi ile yalnızca bir kez geri sayım yapacak şekilde yazınız.
 */
public abstract class CountDownScheduler {
    private final Timer m_timer;
    private long m_millisInFuture;
    private final long m_countDownInterval;

    // mine approach
    private TimerTask createTimerTask()
    {
        return new TimerTask() {
            public void run()
            {
                if (m_millisInFuture <= 0){
                    m_timer.cancel();
                    onFinish();
                    return;
                }
                m_millisInFuture -= m_countDownInterval;
                onTick(m_millisInFuture);
            }
        };
    }

    // teacher's approach
    private TimerTask createTimerTask2()
    {
        return new TimerTask() {
            long duration;
            public void run()
            {
                if (duration >= m_millisInFuture) {
                    m_timer.cancel();
                    onFinish();
                    return;
                }
                duration += m_countDownInterval;
                onTick(duration);
            }
        };
    }

    protected CountDownScheduler(long durationInFuture, long countDownInterval, TimeUnit timeUnit)
    {
        this(timeUnit.toMillis(durationInFuture), timeUnit.toMillis(countDownInterval));
    }

    public CountDownScheduler(long millisInFuture, long countDownInterval)
    {
        m_timer = new Timer();
        m_millisInFuture = millisInFuture;
        m_countDownInterval = countDownInterval;
    }

    public abstract void onTick(long remainingMilliseconds);
    public abstract void onFinish();

    public final void start()
    {
        m_timer.scheduleAtFixedRate(createTimerTask2(), 0, m_countDownInterval);
    }

    public final void cancel()
    {
        m_timer.cancel();
    }
}
