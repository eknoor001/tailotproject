package tablerecords;

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


public class TableViewwwController 
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboCity;

    @FXML
    private TableView<TableBean> tblRecords;
    
    PreparedStatement pst;
    ObservableList<TableBean> list;
    @FXML
    void doShow(ActionEvent event)
    {
    	list=FXCollections.observableArrayList();//
    	String cityselect = comboCity.getSelectionModel().getSelectedItem();
    	
    		
    	try {
			pst = con.prepareStatement("select * from customers where city like ?");
			pst.setString(1, "%" + cityselect + "%");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				String name=rs.getString("name");
				String contact=rs.getString("contact");
				String address=rs.getString("address");
				String gender=rs.getString("gender");
				String dor=rs.getString("dor");
				String ref=rs.getString("ref");
				
			TableBean obj=new TableBean(name,contact,address,gender, dor.toString(),ref);
	     		list.add(obj);
	     		
			}
			tblRecords.setItems(list);
			//comboSD.getItems().clear();
			
    	}
    	catch (SQLException e) {
			e.printStackTrace();
			}
    }
     	
     	
     	
     	
     	
   // ObservableList<TableBean> list;
    @FXML
    void doShowAll(ActionEvent event) 
    {
    	 list=FXCollections.observableArrayList();//creation of object
     	try{
     		pst=con.prepareStatement("select * from customers");
     	ResultSet table=	pst.executeQuery();
     	while(table.next())
     	{
     		
     		String name=table.getString("name");
     		String contact=table.getString("contact");
     		String address=table.getString("address");
     		String gender=table.getString("gender");
     		Date dos=table.getDate("dor");
     		String ref=table.getString("ref");
     		
     		System.out.println(name+"   "+contact+"   "+address+"   "+gender+"   "+dos+"   "+ref);
     		TableBean obj=new TableBean(name,contact,address,gender, dos.toString(),ref);
     		list.add(obj);
     		
     	}
     	tblRecords.setItems(list);
     	//writeExcel();
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
    	            String text="Name,Contact,address,gender,dor,ref\n";
    	            writer.write(text);
    	            for (TableBean p : list)
    	            {
    					text = p.getName()+ "," + p.getContact()+ "," + p.getAddress()+ "," + p.getGender()+","+ p.getDor()+","
    					+p.getRef()+"\n";
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



    
    void doFillCombo()
    {
    	 try {
   			pst=con.prepareStatement("select distinct city from customers");
   			ResultSet table=pst.executeQuery();
   			while(table.next())
   			{
   				String city=table.getString("city");
   				comboCity.getItems().add(city);
   				System.out.println(city);
   			}
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
     }
    }
    
   
    
    void addCols()
    {
    	TableColumn<TableBean, String> cnameCol=new TableColumn<TableBean, String>("Cust. Name");
       	cnameCol.setCellValueFactory(new PropertyValueFactory<TableBean,String>("name"));
    	cnameCol.setMinWidth(100);
    	
    	TableColumn<TableBean, String> contactCol=new TableColumn<TableBean, String>("Cust. Contact");
       	contactCol.setCellValueFactory(new PropertyValueFactory<TableBean,String>("contact"));
    	contactCol.setMinWidth(100);
    	
    	TableColumn<TableBean, String> addressCol=new TableColumn<TableBean, String>("Address");
    	addressCol.setCellValueFactory(new PropertyValueFactory<TableBean,String>("address"));
    	addressCol.setMinWidth(100);
    	
    	TableColumn<TableBean, String> genderCol=new TableColumn<TableBean, String>("Gender");
    	genderCol.setCellValueFactory(new PropertyValueFactory<TableBean,String>("gender"));
    	genderCol.setMinWidth(100);
    	
    	TableColumn<TableBean, String> dorCol=new TableColumn<TableBean, String>("Dor");
    	dorCol.setCellValueFactory(new PropertyValueFactory<TableBean,String>("dor"));
    	dorCol.setMinWidth(100);
    	
    	TableColumn<TableBean, String> refCol=new TableColumn<TableBean, String>("Ref");
    	refCol.setCellValueFactory(new PropertyValueFactory<TableBean,String>("ref"));
    	refCol.setMinWidth(100);
    	
    	tblRecords.getColumns().addAll(cnameCol,contactCol,addressCol,genderCol,dorCol,refCol);
    	
    }
    
   
    Connection con;
    @FXML
    void initialize()
    {
    assert tblRecords != null : "fx:id=\"tblRecords\" was not injected: check your FXML file 'TableViewww.fxml'.";
       con=DataBaseConnector.getConnection();
       doFillCombo();
       addCols();
      // addCols1();
       
    }
}
