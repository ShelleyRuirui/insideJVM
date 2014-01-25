package memoryPlay.GCAndMalloc;

public class FinalizeEscapeGC {

	public static FinalizeEscapeGC SAVE_HOOK=null;
	
	public void isAlive(){
		System.out.println("I am still alive");
	}
	
	protected void finalize() throws Throwable{
		super.finalize();
		System.out.println("finalize method executed!");
		FinalizeEscapeGC.SAVE_HOOK=this;
	}
	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		SAVE_HOOK=new FinalizeEscapeGC();
		
		SAVE_HOOK=null;
		System.gc();
		Thread.sleep(600);
		if(SAVE_HOOK!=null){
			SAVE_HOOK.isAlive();
//			SAVE_HOOK.finalize();
//			SAVE_HOOK.finalize();
		}
		else
			System.out.println("I am collected back");
		
		SAVE_HOOK=null;
		System.gc();
		Thread.sleep(600);
		if(SAVE_HOOK!=null){
			SAVE_HOOK.isAlive();
//			SAVE_HOOK.finalize();
//			SAVE_HOOK.finalize();
		}
		else
			System.out.println("I am collected back");
	}

}
