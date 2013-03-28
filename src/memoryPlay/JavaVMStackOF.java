package memoryPlay;

/*
 * VM Args: -Xss128k
 */
public class JavaVMStackOF {

	private int stackLength=1;
	
	public void stackLeak(){
		stackLength++;
		stackLeak();
	}
	
	public static void main(String[] args) {
		JavaVMStackOF oom=new JavaVMStackOF();
		try{
			oom.stackLeak();
		}catch(Throwable e){
			System.out.println("stack length:"+oom.stackLength);
			throw e;
		}
	}
}
