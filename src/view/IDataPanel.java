package view;

import data.IAdressData;

public interface IDataPanel {
	public IAdressData getAdressData();
	public void setData(IAdressData obj);
	public String getTabText();
	public void setEditable(boolean b);
}
