package testJava.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LibProxy implements InvocationHandler {

	Object obj;
	
	public LibProxy(Object obj){
		this.obj=obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("Before calling "+method.getName());
		Object result=method.invoke(obj, args);
		System.out.println("After calling "+method.getName());
		return result;
	}

	public static void main(String[] args) {
		GreetingImpl obj=new GreetingImpl();
		LibProxy handler=new LibProxy(obj);
		Greet realProxy=(Greet)Proxy.newProxyInstance(obj.getClass().getClassLoader(), 
				obj.getClass().getInterfaces(), handler);
		realProxy.sayHello("Ruirui");
		realProxy.sayGoodbye();
		realProxy.toString();
		realProxy.hashCode();
	}
}
