package com.naruto.thread.deadlock;

public class DeadLockThread implements Runnable {

	private String objA;
	private String objB;

	public DeadLockThread(String objA, String objB) {
		this.objA = objA;
		this.objB = objB;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String name = Thread.currentThread().getName();
		synchronized (objA) {
			System.out.println(name + " 获得 " + objA);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name + " 申请 " + objB);
			synchronized (objB) {
				System.out.println(name + " 获得 " + objB);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(name + " 释放 " + objA);
			}
			System.out.println(name + " 释放 " + objA);
		}

	}

}
