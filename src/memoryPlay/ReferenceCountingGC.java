package memoryPlay;

public class ReferenceCountingGC {

	public Object instance=null;
	
	private static final int _1MB=1024*1024;
	
	private byte[] bigSize=new byte[_1MB];
	
	public static void testGC(){
		ReferenceCountingGC objA=new ReferenceCountingGC();
		ReferenceCountingGC objB=new ReferenceCountingGC();
		objA.instance=objB;
		objB.instance=objA;
		
		System.gc();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ReferenceCountingGC().testGC();
	}

}
