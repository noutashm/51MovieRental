
public class Member {
	private String name;
	private String address;
	private int age;
	private int moviesRented;
	private int loyaltyPoints;
	private String membershipStatus;
	
	// Creating constructor for the members class
	public Member( String name, String address, int age, int moviesRented, int loyaltyPoints, String membershipStatus) {
		
		this.name = name;
		this.address = address;
		this.age = age;
		this.moviesRented = moviesRented;
		this.loyaltyPoints = loyaltyPoints;
	    this.membershipStatus = membershipStatus;
	}
	
	//Creating get and set methods for all the variables
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

	public int getLoyaltyPoints() {
		return this.loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public String getMembershipStatus() {
	    return this.membershipStatus;
	}
	 
	public void setMembershipStatus(String membershipStatus) {
		 this.membershipStatus = membershipStatus;
	}
}
