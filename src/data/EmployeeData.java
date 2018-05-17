package data;

public class EmployeeData implements IAdressData{

	private static final long serialVersionUID = 1L;
	
	public EmployeeData(String name, int age, int salary) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	private String name;
	private int age;
	private int salary;
	
	public String getName() {
		return name;
	}

	public int getSalary() {
		return salary;
	}

	public int getAge() { return age; }


	@Override
	public String toString() {
		return "Employee "+ name;
	}

	@Override
	public int compareTo(IAdressData o) {
		ProfessionData obj = (ProfessionData) o;
		return name.compareTo(obj.getName());
	}

}
