package ordersgoogler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
//import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import table1records.Table1Bean;
import tablerecords.TableBean;

public class OrdersStatusViewController 
{

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="radP"
    private RadioButton radP; // Value injected by FXMLLoader

    @FXML // fx:id="same"
    private ToggleGroup same; // Value injected by FXMLLoader

    @FXML // fx:id="radD"
    private RadioButton radD; // Value injected by FXMLLoader

    @FXML // fx:id="comboWorkers"
    private ComboBox<String> comboWorkers; // Value injected by FXMLLoader

    @FXML // fx:id="tblShow"
    private TableView<TableeBean> tblShow; // Value injected by FXMLLoader

    @FXML // fx:id="datePicker"
    private DatePicker datePicker; // Value injected by FXMLLoader
    
    PreparedStatement pst;
    ObservableList<TableeBean> list;
    @FXML
    void doFetch(ActionEvent event)
    {
    	list=FXCollections.observableArrayList();
	
    	LocalDate date=datePicker.getValue();
    	
    	try {
			pst = con.prepareStatement("select * from orders where ddate  > ? ");
		//	pst.setDate(1,valueOfDate(date));
			pst.setDate(1, java.sql.Date.valueOf(date));
			//pst.setString(1, "%" + date + "%");
			
			ResultSet table = pst.executeQuery();
			while(table.next())
	      	{
	      		
	      		int oid=table.getInt("oid");
	      		String contact=table.getString("contact");
	      		String dress=table.getString("dress");
	      		String workers=table.getString("worker");
	      		String mrs=table.getString("measurement");
	      		Date dos=table.getDate("ddate");
	      		int amount=table.getInt("amount");
	      		int status=table.getInt("status");
	      	
	      		
	      		System.out.println(oid+"   "+contact+"   "+dress+"   "+workers+"   "+mrs+"   "+dos+"   "+amount+"  "+status);
	      		TableeBean obj=new TableeBean(oid,contact,dress,workers,mrs, dos.toString(),amount,status);
	      		list.add(obj);
	      		
	      	}
			
			tblShow.setItems(list);
			//comboSD.getItems().clear();
			
    	}
    	catch (SQLException e) {
			e.printStackTrace();
			}
    }
    @FXML
    void doShowDataInExcel(MouseEvent event)  throws Exception
    {
    	 
    	        Writer writer = null;
    	        try {
    	        	File file = new File("Users.csv");
    	            writer = new BufferedWriter(new FileWriter(file));
    	            String text="oid,contact,dress,worker,measurement,ddate,amount,status\n";
    	            writer.write(text);
    	            for (TableeBean p : list)
    	            {
    					text = p.getOid()+ "," + p.getContact()+ "," + p.getDress()+ "," + p.getWorker()+","+ p.getMeasurement()+","
    					+p.getDdate() +","+p.getAmount()+","+p.getStatus()+"\n";
    	                writer.write(text);
    	            }
    	            System.out.println("Exported Successfully");
    	        } catch (Exception ex) {
    	            ex.printStackTrace();
    	        }
    	        finally {
    	           
    	            writer.flush();
    	             writer.close();
    	        
    }
    }

    @FXML
    void doGetData(ActionEvent event) 
    {
    	list=FXCollections.observableArrayList();//
    	String worker = comboWorkers.getSelectionModel().getSelectedItem();
    	
    	if(radP.isSelected())
    	{
    		
    	try {
			pst = con.prepareStatement("select * from orders where status=1");
			
			ResultSet table = pst.executeQuery();
			while(table.next())
	      	{
	      		
	      		int oid=table.getInt("oid");
	      		String contact=table.getString("contact");
	      		String dress=table.getString("dress");
	      		String workers=table.getString("worker");
	      		String mrs=table.getString("measurement");
	      		Date dos=table.getDate("ddate");
	      		int amount=table.getInt("amount");
	      		int status=table.getInt("status");
	      	
	      		
	      		System.out.println(oid+"   "+contact+"   "+dress+"   "+workers+"   "+mrs+"   "+dos+"   "+amount+"  "+status);
	      		TableeBean obj=new TableeBean(oid,contact,dress,worker,mrs, dos.toString(),amount,status);
	      		list.add(obj);
	      		
	      	}
			
			tblShow.setItems(list);
			//comboSD.getItems().clear();
			
    	}
    	catch (SQLException e) {
			e.printStackTrace();
			}
    }
    
    else
    	if(radD.isSelected())
    	{
    		try {
    			pst = con.prepareStatement("select * from orders where status=0");
    			
    			ResultSet table = pst.executeQuery();
    			while(table.next())
    	      	{
    	      		
    	      		int oid=table.getInt("oid");
    	      		String contact=table.getString("contact");
    	      		String dress=table.getString("dress");
    	      		String workers=table.getString("worker");
    	      		String mrs=table.getString("measurement");
    	      		Date dos=table.getDate("ddate");
    	      		int amount=table.getInt("amount");
    	      		int status=table.getInt("status");
    	      	
    	      		
    	      		System.out.println(oid+"   "+contact+"   "+dress+"   "+workers+"   "+mrs+"   "+dos+"   "+amount+"  "+status);
    	      		TableeBean obj=new TableeBean(oid,contact,dress,worker,mrs, dos.toString(),amount,status);
    	      		list.add(obj);
    	      		
    	      	}
    			
    			tblShow.setItems(list);
    			//comboSD.getItems().clear();
    			
        	}
        	catch (SQLException e) {
    			e.printStackTrace();
    			}
    	}
    }

    @FXML
    void doGetWorkA(ActionEvent event)
    {
    	list=FXCollections.observableArrayList();//
	    String worker = comboWorkers.getSelectionModel().getSelectedItem();

    	try {
			pst = con.prepareStatement("select * from orders where worker like ?");
			pst.setString(1, "%" + worker + "%");
			
			ResultSet table = pst.executeQuery();
			while(table.next())
	      	{
	      		
	      		int oid=table.getInt("oid");
	      		String contact=table.getString("contact");
	      		String dress=table.getString("dress");
	      		String workers=table.getString("worker");
	      		String mrs=table.getString("measurement");
	      		Date dos=table.getDate("ddate");
	      		int amount=table.getInt("amount");
	      		int status=table.getInt("status");
	      	
	      		
	      		System.out.println(oid+"   "+contact+"   "+dress+"   "+workers+"   "+mrs+"   "+dos+"   "+amount+"  "+status);
	      		TableeBean obj=new TableeBean(oid,contact,dress,worker,mrs, dos.toString(),amount,status);
	      		list.add(obj);
	      		
	      	}
			
			tblShow.setItems(list);
			//comboSD.getItems().clear();
			
    	}
    	catch (SQLException e) {
			e.printStackTrace();
			}
    }

    @FXML
    void doShowAll(ActionEvent event)
    {
    	list=FXCollections.observableArrayList();//creation of object
      	try{
      		pst=con.prepareStatement("select * from orders");
      	ResultSet table=	pst.executeQuery();
      	while(table.next())
      	{
      		
      		int oid=table.getInt("oid");
      		String contact=table.getString("contact");
      		String dress=table.getString("dress");
      		String worker=table.getString("worker");
      		String mrs=table.getString("measurement");
      		Date dos=table.getDate("ddate");
      		int amount=table.getInt("amount");
      		int status=table.getInt("status");
      	
      		
      		System.out.println(oid+"   "+contact+"   "+dress+"   "+worker+"   "+mrs+"   "+dos+"   "+amount+"  "+status);
      		TableeBean obj=new TableeBean(oid,contact,dress,worker,mrs, dos.toString(),amount,status);
      		list.add(obj);
      		
      	}
      	tblShow.setItems(list);
      	}
      	catch(Exception exp)
      	{ 
      		exp.printStackTrace();
      	}

    }
    
    
    
    void addCols()
    {
    	TableColumn<TableeBean, Integer> oidCol=new TableColumn<TableeBean,  Integer>("Cust. Oid");
       	oidCol.setCellValueFactory(new PropertyValueFactory<TableeBean, Integer>("oid"));
    	oidCol.setMinWidth(50);
    	
    	TableColumn<TableeBean, String> contactCol=new TableColumn<TableeBean, String>("Cust. Contact");
       	contactCol.setCellValueFactory(new PropertyValueFactory<TableeBean,String>("contact"));
    	contactCol.setMinWidth(100);
    	
    	TableColumn<TableeBean, String> dressCol=new TableColumn<TableeBean, String>("Dress");
       	dressCol.setCellValueFactory(new PropertyValueFactory<TableeBean,String>("dress"));
    	dressCol.setMinWidth(100);
    	
    	TableColumn<TableeBean, String> workerCol=new TableColumn<TableeBean, String>("Worker");
    	workerCol.setCellValueFactory(new PropertyValueFactory<TableeBean,String>("worker"));
    	workerCol.setMinWidth(60);
    	
    	TableColumn<TableeBean, String> measurementCol=new TableColumn<TableeBean, String>("Measurement");
    	measurementCol.setCellValueFactory(new PropertyValueFactory<TableeBean,String>("measurement"));
    	measurementCol.setMinWidth(100);
    	
    	TableColumn<TableeBean, String> dateCol=new TableColumn<TableeBean, String>("Date");
    	dateCol.setCellValueFactory(new PropertyValueFactory<TableeBean,String>("ddate"));
    	dateCol.setMinWidth(70);
    	
    	TableColumn<TableeBean, Integer> amountCol=new TableColumn<TableeBean, Integer>("Amount");
    	amountCol.setCellValueFactory(new PropertyValueFactory<TableeBean,Integer>("amount"));
    	amountCol.setMinWidth(50);
    	
    	TableColumn<TableeBean, Integer> statusCol=new TableColumn<TableeBean, Integer>("Status");
    	statusCol.setCellValueFactory(new PropertyValueFactory<TableeBean,Integer>("status"));
    	statusCol.setMinWidth(50);
    	
    	
    	
    	tblShow.getColumns().addAll(oidCol,contactCol,dressCol,workerCol,measurementCol,dateCol,amountCol,statusCol);
    	
    }
    
    void doFillCombo()
    {
    	 try {
   			pst=con.prepareStatement("select distinct worker from orders");
   			ResultSet table=pst.executeQuery();
   			while(table.next())
   			{
   				String wr=table.getString("worker");
   				comboWorkers.getItems().add(wr);
   				System.out.println(wr);
   			}
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
     }
    }
    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
    	con=DataBaseConnector.getConnection();
       addCols();
       doFillCombo();
    }
}
