package modifierConflict;

/*
 * This class shows that final and synchronized are not conflict
 */
public class Customer {
	private int id;
	private String name;
	private String age;
	private String address;
	public synchronized final int getId() {
		return id;
	}
	public synchronized final void setId(int id) {
		this.id = id;
	}
	public synchronized final String getName() {
		return name;
	}
	public synchronized final void setName(String name) {
		this.name = name;
	}
	public synchronized final String getAge() {
		return age;
	}
	public synchronized final void setAge(String age) {
		this.age = age;
	}
	public synchronized final String getAddress() {
		return address;
	}
	public synchronized final void setAddress(String address) {
		this.address = address;
	}	
	
	public static void main(String[] args) {
		Customer c=new Customer();
		c.setName("Hi");
		System.out.println(c.getName());
	}
	
}
