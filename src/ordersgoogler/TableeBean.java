package ordersgoogler;

public class TableeBean
{
int oid;
String contact;
String dress;
String worker;
String measurement;
String ddate;
int amount;
int status;


public TableeBean(int oid, String contact, String dress, String worker, String measurement, String ddate, int amount,
		int status) {
	super();
	this.oid = oid;
	this.contact = contact;
	this.dress = dress;
	this.worker = worker;
	this.measurement = measurement;
	this.ddate = ddate;
	this.amount = amount;
	this.status = status;
}


public int getOid() {
	return oid;
}
public void setOid(int oid) {
	this.oid = oid;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
public String getDress() {
	return dress;
}
public void setDress(String dress) {
	this.dress = dress;
}
public String getWorker() {
	return worker;
}
public void setWorker(String worker) {
	this.worker = worker;
}
public String getMeasurement() {
	return measurement;
}
public void setMeasurement(String measurement) {
	this.measurement = measurement;
}
public String getDdate() {
	return ddate;
}
public void setDdate(String ddate) {
	this.ddate = ddate;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}

}
