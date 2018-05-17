package data;

public class CitycomData implements IAdressData {

	private static final long serialVersionUID = 1L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CitycomData{" +
				"cityName='" + name + '\'' +
				'}';
	}

	@Override
	public int compareTo(IAdressData arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public CitycomData(String name) {
		super();
		this.name = name;
	}
}
