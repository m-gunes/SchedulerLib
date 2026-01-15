package org.csystem.scheduler.timeout;

import org.csystem.util.thread.ThreadUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class AlarmLocalTimeConstructorTest {
    private static final long MILLISECOND = 7000;
    private static final int SECOND = 5;

    private Runnable createRunnable(LocalTime time)
    {

//        return new Runnable() {
//            public void run()
//            {
//                var now = LocalTime.now().withNano(0);
//                Assertions.assertEquals(now, time.withNano(0));
//            }
//        };

        // Asagidaki gibi lambda kullanimi ile de yazabilirim.
        // cunku Runnable da functional interface
        // run metodu yerine gececek. run metodu parametresiz.
//       return () -> {
//           var now = LocalTime.now().withNano(0);
//           Assertions.assertEquals(now, time.withNano(0));
//       };

       // daha yalin da yazilabilir
       return () -> Assertions.assertEquals(LocalTime.now().withNano(0), time.withNano(0));
    }

    @Test
    void test()
    {
        var time = LocalTime.now().plusSeconds(SECOND);

        Alarm alarm = new Alarm(time);
        alarm.start(createRunnable(time));

        ThreadUtil.sleep(MILLISECOND);
        // Soyle bir durum var:
        // asenkron islemlerde unit testleri bekletmeniz gerekiyor
    }
}
