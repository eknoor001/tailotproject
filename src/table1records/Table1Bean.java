package table1records;

public class Table1Bean 
{
String name;
String contact;
String address;
String adhar;
String exp;
String dor;



public Table1Bean(String name, String contact, String address, String adhar, String exp, String dor ) {
	super();
	this.name = name;
	this.contact = contact;
	this.address = address;
	this.adhar = adhar;
	this.exp = exp;
	this.dor = dor;
	
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
public String getAdhar() {
	return adhar;
}
public void setAdhar(String adhar) {
	this.adhar = adhar;
}
public String getExp() {
	return exp;
}
public void setExp(String exp) {
	this.exp = exp;
}
public String getDor() {
	return dor;
}
public void setDor(String dor) {
	this.dor = dor;
}


}

