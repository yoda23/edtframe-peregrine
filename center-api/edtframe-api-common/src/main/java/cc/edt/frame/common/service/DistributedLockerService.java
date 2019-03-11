package cc.edt.frame.common.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

/**
 * 分布式并发锁
 *
 * @author 刘钢
 * @date 2018/12/18 18:20
 */
@Component
public class DistributedLockerService {
    @Resource
    private RedissonClient redissonClient;

    /**
     * 上锁
     *
     * @param lockKey   lockKey
     * @param leaseTime leaseTime
     * @param unit      unit
     * @param waitTime  waitTime
     * @return boolean
     * @author 刘钢
     * @date 2018/12/19 9:24
     */
    public boolean lock(String lockKey, TimeUnit unit, int waitTime,
                        int leaseTime) throws InterruptedException {
        RLock lock = redissonClient.getLock(lockKey);
        return lock.tryLock(waitTime, leaseTime, unit);
    }

    /**
     * 解锁
     *
     * @param lockKey lockKey
     * @author 刘钢
     * @date 2018/12/19 9:30
     */
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }

}
