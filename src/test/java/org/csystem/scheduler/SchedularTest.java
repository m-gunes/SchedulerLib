package org.csystem.scheduler;

import org.csystem.util.thread.ThreadUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class SchedularTest {
    private int m_count;

    @Test
    void test()
    {
        var scheduler = Scheduler.of(1, TimeUnit.SECONDS).schedule(() -> ++m_count);
        ThreadUtil.sleep(5_000);
        scheduler.cancel();
        Assertions.assertEquals(6, m_count);
    }

    @Test
    void testWithTimeout()
    {
        var dateTime = LocalDateTime.now().plusSeconds(5);
        var scheduler = Scheduler.of(1, TimeUnit.SECONDS).schedule(() -> ++m_count, dateTime);
        ThreadUtil.sleep(10_000);
        scheduler.cancel();
        Assertions.assertEquals(5, m_count);
    }

    @Test
    void testWithCancelTask()
    {
        var scheduler = Scheduler.of(1, TimeUnit.SECONDS).schedule(() -> ++m_count, () -> --m_count);
        ThreadUtil.sleep(5_000);
        scheduler.cancel();
        Assertions.assertEquals(0, m_count);
    }
}
