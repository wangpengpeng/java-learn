package com.wpp.concurrent.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UseThreadPoolExecutor2 implements Runnable{

	private static AtomicInteger count = new AtomicInteger(0);
	
	@Override
	public void run() {
		try {
			int temp = count.incrementAndGet();
			System.out.println("任务" + temp);
			Thread.sleep(3000);
			System.out.println("任务" + temp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception{

		//System.out.println(Runtime.getRuntime().availableProcessors());
		BlockingQueue<Runnable> queue = 
				//new LinkedBlockingQueue<Runnable>();
				new ArrayBlockingQueue<Runnable>(10);
		ExecutorService executor  = new ThreadPoolExecutor(
					5, 		//core
					10, 	//max
					30L, 	//2分钟，
					TimeUnit.SECONDS,
					queue,
				    new MyRejected());

		try {
			for (int i = 0; i < 200; i++) {
				executor.execute(new UseThreadPoolExecutor2());
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			executor.shutdown();
		}

		Thread.sleep(1000);

		System.out.println("queue size:" + queue.size());		//10
		Thread.sleep(2000);


	}


}
