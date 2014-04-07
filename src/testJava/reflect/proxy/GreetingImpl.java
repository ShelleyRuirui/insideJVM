package testJava.reflect.proxy;

public class GreetingImpl implements Greet {

	@Override
	public void sayHello(String msg) {
		System.out.println("Hello "+msg);
	}

	@Override
	public void sayGoodbye() {
		System.out.println("Good bye");
	}

}
