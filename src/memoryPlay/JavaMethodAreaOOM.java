package memoryPlay;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * @author Shelley
 *
 */

public class JavaMethodAreaOOM {

	public static void main(String[] args) {
		int depth=0;
		try{
		while(true){
			depth++;
			Enhancer enhancer=new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				
				@Override
				public Object intercept(Object obj, Method method, Object[] args,
						MethodProxy proxy) throws Throwable {
					return proxy.invokeSuper(obj, args);
				}
			});
			enhancer.create();
		}
		}catch(Exception e){
			System.out.println("Current depth:"+depth);
			e.printStackTrace();
		}
	}
	
	static class OOMObject{
		
	}
}
