package com.wpp.bjsxt.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * lock.getHoldCount()方法：只能在当前调用线程内部使用，不能再其他线程中使用
 * 那么我可以在m1方法里去调用m2方法，同时m1方法和m2方法都持有lock锁定即可 测试结果holdCount数递增
 *
 */
public class TestHoldCount {

	//重入锁
	private ReentrantLock lock = new ReentrantLock();

	private String name ;
	public TestHoldCount(String name){
		this.name =name;
	}
	
	public void m1(){
		try {
			lock.lock();
			System.out.println(this.name+"进入m1方法，holdCount数为：" + lock.getHoldCount());
			//调用m2方法
			m2();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}



		System.out.println(this.name+"退出m1方法，holdCount数为：" + lock.getHoldCount());
	}
	
	public void m2(){
		try {
			lock.lock();
			System.out.println(this.name+"进入m2方法，holdCount数为：" + lock.getHoldCount());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

		System.out.println(this.name+"退出m2方法，holdCount数为：" + lock.getHoldCount());
	}
	
	
	public static void main(String[] args) {
		TestHoldCount thc = new TestHoldCount("t1");
		thc.m1();

		//同一个时候只能一个对象进入
		TestHoldCount thc2 = new TestHoldCount("t2");
		thc2.m1();
	}
}
