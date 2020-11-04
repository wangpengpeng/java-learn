package com.wpp.concurrent.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejected implements RejectedExecutionHandler{

	
	public MyRejected(){
	}

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("自定义处理..");
		System.out.println("当前被拒绝任务为：" + r.toString());
		try {
//		todo	拿不到要处理的数据,只能获取到线程级别的数据。
			System.out.println("当前被拒绝任务为：" + executor.getQueue().take());
		} catch (InterruptedException e) {

		}
		System.out.println("把该任务推送到kafka中："  );


		// 也可以推送到kakfa

	}

}
