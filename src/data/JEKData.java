package data;

public class JEKData implements IAdressData{
	
	private static final long serialVersionUID = 1L;

	private String district;
	private String street;
	private String chiefName;
	private int houseQuan;


	public JEKData(String district, String street, String chiefName, int houseQuan) {
		super();
		this.district = district;
		this.street = street;
		this.chiefName = chiefName;
		this.houseQuan = houseQuan;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDistrict() {return district;}
	public String getStreet() {return street;}
	public String getChiefName() {return chiefName;}
	public int getHouseQuan() {return houseQuan;}

	@Override
	public String toString() {
		return "JEKData{" +
				"district='" + district + '\'' +
				", street='" + street + '\'' +
				", chiefName='" + chiefName + '\'' +
				", houseQuan=" + houseQuan +
				'}';
	}


	@Override
	public int compareTo(IAdressData o) {
		return 0;
	}
}
