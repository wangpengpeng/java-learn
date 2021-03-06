package com.wpp.conart.chapter05;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 10-1
 *
 * lock.lock必须放在try外面
 *
 */
public class LockUseCase {

    public void lock() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
        } finally {
            lock.unlock();
        }
    }
}
