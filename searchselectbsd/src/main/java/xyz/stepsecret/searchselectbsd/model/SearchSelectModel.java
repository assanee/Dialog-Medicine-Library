package xyz.stepsecret.searchselectbsd.model;

public class SearchSelectModel {

	private String medicineName;

	public void setMedicineName(String medicineName){
		this.medicineName = medicineName;
	}

	public String getMedicineName(){
		return medicineName;
	}

	@Override
 	public String toString(){
		return 
			"MedicineListItem{" +
			",medicine_name = '" + medicineName + '\'' + 
			"}";
		}
}
