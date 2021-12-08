package table1records;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tablerecords.DataBaseConnector;
import tablerecords.TableBean;

public class TablesViewwwController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboSD;

    @FXML
    private TableView<Table1Bean> tblShow;
    
    PreparedStatement pst;
    ObservableList<Table1Bean> list;

    @FXML
    void doShow(ActionEvent event)
    {
    	
    	list=FXCollections.observableArrayList();
    	String dressselect = comboSD.getSelectionModel().getSelectedItem();
    	
    		
    	try {
			pst = con.prepareStatement("select * from employees where dresses like ?");
			pst.setString(1, "%" + dressselect + "%");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				String name=rs.getString("name");
				String contact=rs.getString("contact");
				String address=rs.getString("address");
				String adhar=rs.getString("adhar");
				String exp=rs.getString("exp");
				String dor=rs.getString("dor");
				
				Table1Bean obj=new Table1Bean(name,contact,address,adhar,exp, dor.toString());
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
      		pst=con.prepareStatement("select * from employees");
      	ResultSet table=	pst.executeQuery();
      	while(table.next())
      	{
      		
      		String name=table.getString("name");
      		String contact=table.getString("contact");
      		String address=table.getString("address");
      		String adhar=table.getString("adhar");
      		String exp=table.getString("exp");
      		Date dos=table.getDate("dor");
      	
      		
      		System.out.println(name+"   "+contact+"   "+adhar+"   "+address+"   "+exp+"   "+dos);
      		Table1Bean obj=new Table1Bean(name,contact,address,adhar,exp, dos.toString());
      		list.add(obj);
      		
      	}
      	tblShow.setItems(list);
      	}
      	catch(Exception exp)
      	{ 
      		exp.printStackTrace();
      	}

    }
    @FXML
    void doShowDataInExcel(MouseEvent event)  throws Exception
    {
    	 
    	        Writer writer = null;
    	        try {
    	        	File file = new File("Users.csv");
    	            writer = new BufferedWriter(new FileWriter(file));
    	            String text="Name,Contact,address,adhar,exp,dor\n";
    	            writer.write(text);
    	            for (Table1Bean p : list)
    	            {
    					text = p.getName()+ "," + p.getContact()+ "," + p.getAddress()+ "," + p.getAdhar()+","+ p.getExp()+","
    					+p.getDor()+"\n";
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
    
    void addCols()
    {
    	TableColumn<Table1Bean, String> cnameCol=new TableColumn<Table1Bean, String>("Cust. Name");
       	cnameCol.setCellValueFactory(new PropertyValueFactory<Table1Bean,String>("name"));
    	cnameCol.setMinWidth(80);
    	
    	TableColumn<Table1Bean, String> contactCol=new TableColumn<Table1Bean, String>("Cust. Contact");
       	contactCol.setCellValueFactory(new PropertyValueFactory<Table1Bean,String>("contact"));
    	contactCol.setMinWidth(100);
    	
    	TableColumn<Table1Bean, String> addressCol=new TableColumn<Table1Bean, String>("Address");
    	addressCol.setCellValueFactory(new PropertyValueFactory<Table1Bean,String>("address"));
    	addressCol.setMinWidth(100);
    	
    	TableColumn<Table1Bean, String> adharCol=new TableColumn<Table1Bean, String>("Adhar");
    	adharCol.setCellValueFactory(new PropertyValueFactory<Table1Bean,String>("adhar"));
    	adharCol.setMinWidth(80);
    	
    	TableColumn<Table1Bean, String> expCol=new TableColumn<Table1Bean, String>("Exp");
    	expCol.setCellValueFactory(new PropertyValueFactory<Table1Bean,String>("exp"));
    	expCol.setMinWidth(10);
    	
    	TableColumn<Table1Bean, String> dorCol=new TableColumn<Table1Bean, String>("DOR");
    	dorCol.setCellValueFactory(new PropertyValueFactory<Table1Bean,String>("dor"));
    	dorCol.setMinWidth(80);
    	
    	tblShow.getColumns().addAll(cnameCol,contactCol,addressCol,adharCol,expCol,dorCol);
    	
    }
    
   /* void doFillCombo()
    {
    	 try {
   			pst=con.prepareStatement("select distinct dresses from employees");
   			ResultSet table=pst.executeQuery();
   			while(table.next())
   			{
   				String DR=table.getString("dresses");
   				comboSD.getItems().add(DR);
   				System.out.println(DR);
   			}
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
     }
    }*/
    
    Connection con;
    @FXML
    void initialize() 
    {
    	 assert tblShow != null : "fx:id=\"tblRecords\" was not injected: check your FXML file 'TablesViewww.fxml'.";
    	con=DataBaseConnector.getConnection();
        //doFillCombo();
    	String str[]={"Salwar suit","sharara","pajami suit","kurta pajama","coat pent","plazo","lehenge","gharara","skirts",
    			"leggings","lehenga choli","patiala suit","jackets"};
    	comboSD.getItems().addAll(str);
    	
    	 addCols();
    }
}
