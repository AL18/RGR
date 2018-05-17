package data;

public class ProfessionData implements IAdressData{

	
	private static final long serialVersionUID = 1L;
	

	private String name;
	private int employeesQuan;

	public ProfessionData(String name, int employeesQuan) {
		super();
		this.name = name;
		this.employeesQuan = employeesQuan;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public int getEmployeesQuan() {
		return employeesQuan;
	}

	@Override
	public int compareTo(IAdressData o) {
		ProfessionData obj = (ProfessionData) o;
		return name.compareTo(obj.getName());
	}

//	public boolean isSpec() {
//		if(employeesQuan >= 5)
//			return true;
//		return false;
//	}
}

