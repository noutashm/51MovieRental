public class Customer {
	
	private String name;
	private int age;
	private String address;
	private int moviesRented;
	
	// Creating constructor for the customers class
	public Customer( String name, int age, String address, int moviesRented) {
		this.name = name;
		this.address = address;
		this.age = age;
		this.moviesRented = moviesRented;
	}

	// Creating get and set methods for all the variables
	public String getName() {
	    return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	} 
	
	public String getAddress() {
		return this.address;
	}
		 
	 public void setAddress(String address) {
	    this.address = address;
	 }
		 
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getMoviesRented() {
		return this.moviesRented;
		
	}
	
	public void setMoviesRented(int moviesRented) {
		this.moviesRented = moviesRented;
	}
}