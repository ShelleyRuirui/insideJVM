package testJava.reflect.proxy;

public class SimpleProxy implements Greet {

	Greet greet;
	
	public SimpleProxy(Greet g){
		greet=g;
	}
	@Override
	public void sayHello(String msg) {
		System.out.println("before hello");
		greet.sayHello(msg);
		System.out.println("after hello");
	}

	@Override
	public void sayGoodbye() {
		System.out.println("before good bye");
		greet.sayGoodbye();
		System.out.println("after good bye");
	}

	public static void main(String[] args) {
		Greet greet=new SimpleProxy(new GreetingImpl());
		greet.sayHello("Ruirui");
		greet.sayGoodbye();
	}
}
