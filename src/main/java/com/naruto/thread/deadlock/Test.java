package com.naruto.thread.deadlock;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		String A = "A";
		String B = "B";
		Thread threadA = new Thread(new DeadLockThread(A, B),"ThreadA");
		Thread threadB = new Thread(new DeadLockThread(B, A),"ThreadB");
		threadA.start();
		threadB.start();
	}

}
