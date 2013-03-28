package classTest;

public class ClassTest {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Class clazz=Class.forName("java.lang.Class");
		System.out.println("Class name:"+clazz.getName());
		Class superClass=clazz.getSuperclass();
		if(superClass!=null)
			System.out.println("Super class name:"+superClass.getName());
		if(clazz.isInterface())
			System.out.println("Is interface");
		Class[] interfaces=clazz.getInterfaces();
		for(Class c:interfaces){
			System.out.println(c.getName());
		}
		
		ClassLoader loader=clazz.getClassLoader();
//		System.out.println(loader.toString());
	}

}
