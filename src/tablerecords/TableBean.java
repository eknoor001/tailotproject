package tablerecords;

public class TableBean 
{
String name;
String contact;
String address;
String gender;
String dor;
String ref;


public TableBean(String name, String contact, String address, String gender, String dor, String ref) {
	super();
	this.name = name;
	this.contact = contact;
	this.address = address;
	this.gender = gender;
	this.dor = dor;
	this.ref = ref;
}



public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getDor() {
	return dor;
}
public void setDor(String dor) {
	this.dor = dor;
}
public String getRef() {
	return ref;
}
public void setRef(String ref) {
	this.ref = ref;
}



}
