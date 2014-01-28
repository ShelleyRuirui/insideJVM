/*
 * JConsole monitor dead lock
 */
package memoryPlay;

public class MonitorDeadLock {

	static class SynAddRunnable implements Runnable{
		int a,b;
		public SynAddRunnable(int a,int b){
			this.a=a;
			this.b=b;
		}
		@Override
		public void run() {
				synchronized(Integer.valueOf(a)){ //The valueof method will cache number between [-128,127]
					synchronized(Integer.valueOf(b)){
						System.out.println(a+b);
					}
				}
		}
	}
	
	public static void main(String[] args) throws InterruptedException{
		for(int i=0;i<100;i++){
			new Thread(new SynAddRunnable(1,2)).start();
			new Thread(new SynAddRunnable(2,1)).start();
		}
		Thread.sleep(100000);
	}
}
