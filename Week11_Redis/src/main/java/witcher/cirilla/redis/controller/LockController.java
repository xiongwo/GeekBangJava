package witcher.cirilla.redis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@RestController
public class LockController {

    Logger logger = LoggerFactory.getLogger(LockController.class);

    @Autowired
    private RedisLockRegistry redisLockRegistry;

    @GetMapping("test")
    public void test() throws InterruptedException {
        Lock lock = redisLockRegistry.obtain("lock");

        boolean b1 = lock.tryLock(3, TimeUnit.SECONDS);
        logger.info("b1 is : {}", b1);

        TimeUnit.SECONDS.sleep(5);

        boolean b2 = lock.tryLock(3, TimeUnit.SECONDS);
        logger.info("b2 is : {}", b2);

        lock.unlock();
        lock.unlock();
    }

    @GetMapping("stock")
    public void stock() throws InterruptedException {
        Lock lock = redisLockRegistry.obtain("stock");

        boolean b = lock.tryLock(3, TimeUnit.SECONDS);
        if (b) {
            logger.info(System.currentTimeMillis() + "  库存减 1");
        }

        lock.unlock();
    }

}
