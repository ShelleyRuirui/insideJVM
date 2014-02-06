package testJava.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TestExecutorService {

	private static class CountDown implements Runnable{

		int countDown=10;
		private static  int taskCount=0;
		private final int id=taskCount++;
		
		public CountDown(){
			
		}
		
		public String status(){
			return "#" + id + "(" +(countDown > 0 ? countDown : "Nothing more!") + ") ";
		}
		@Override
		public void run() {
			while(countDown-->0){
				System.out.println(status());
				//To stop current and prefer other thread with same priority to run
				Thread.yield();
				//Thread.join() stops current and wait until others waits on join
			}
		}
		
	}
	
	private static void testCachedThreadPool(){
		ExecutorService exec=Executors.newCachedThreadPool();
		for(int i=0;i<100;i++){
			exec.execute(new TestExecutorService.CountDown());
		}
		
		exec.shutdown();
		
		int largestPoolSize = ((ThreadPoolExecutor) exec).getLargestPoolSize();
		System.out.println("Largest pool size:"+largestPoolSize); //May less than 100
	
	}
	
	private static void testFixedThreadPool(){
		//Another is ThreadPoolExecutor (corePoolSize,maxPoolSize,waiting queue)
		ExecutorService exec = Executors.newFixedThreadPool(3);
		
		for(int i=0;i<5;i++){
			exec.execute(new CountDown());
		}
		exec.shutdown();
	}
	
	public static void main(String[] args) {
		//testCachedThreadPool();
		testFixedThreadPool();
	}
}
