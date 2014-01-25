package memoryPlay;

/*
 * VM Args:-XX:PermSize=10M -XX:MaxPermSize=10M
 */
import java.util.ArrayList;
import java.util.List;

public class RuntimeConstantPoolOOM {

	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		int i=0;
		while(true){
			list.add(String.valueOf(i++).intern());
		}
	}
}
