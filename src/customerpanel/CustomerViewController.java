package customerpanel;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class CustomerViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="iv1"
    private ImageView iv1; // Value injected by FXMLLoader

    @FXML // fx:id="txtName"
    private TextField txtName; // Value injected by FXMLLoader

    @FXML // fx:id="txtAddress"
    private TextField txtAddress; // Value injected by FXMLLoader

    @FXML // fx:id="txtCity"
    private TextField txtCity; // Value injected by FXMLLoader

    @FXML // fx:id="txtEmail"
    private TextField txtEmail; // Value injected by FXMLLoader

    @FXML // fx:id="radMale"
    private RadioButton radMale; // Value injected by FXMLLoader

    @FXML // fx:id="Gem"
    private ToggleGroup Gem; // Value injected by FXMLLoader

    @FXML // fx:id="radFemale"
    private RadioButton radFemale; // Value injected by FXMLLoader

    @FXML // fx:id="txtReffer"
    private TextField txtReffer; // Value injected by FXMLLoader

    @FXML // fx:id="comboCn"
    private ComboBox<String> comboCn; // Value injected by FXMLLoader
    
    @FXML // fx:id="lblPath"
    private Label lblPath; // Value injected by FXMLLoader
    
    PreparedStatement pst;
    
    @FXML
    void doBrowse(ActionEvent event)
    {
    	 FileChooser fc=new FileChooser();
   	  File selectedFile=fc.showOpenDialog(null);
   	  String str=selectedFile.getAbsolutePath();
   	  lblPath.setText(str);
   	  System.out.println(str);

   	  try 
   	  {
   		FileInputStream stream=new FileInputStream(str);
   		Image img=new Image(stream);
   		iv1.setImage(img);
   	  }   
   	  catch (FileNotFoundException e)
   	  {
   		
   		e.printStackTrace();
   	  }
    }
    
    @FXML
    void doDetails(ActionEvent event) 
    {
    	try{
    		pst=con.prepareStatement("select * from customers where contact=?");
    		pst.setString(1, comboCn.getEditor().getText());
    	ResultSet table=pst.executeQuery();
    	while(table.next())
    	{
    		String name=table.getString("name");
    		String picpath=table.getString("picpath");
    		String address=table.getString("address");
    		String city=table.getString("city");
    		String email=table.getString("email");
    		 try 
    	   	  {
    	   		FileInputStream stream=new FileInputStream(picpath);
    	   		Image img=new Image(stream);
    	   		iv1.setImage(img);
    	   	  }   
    	   	  catch (FileNotFoundException e)
    	   	  {
    	   		
    	   		e.printStackTrace();
    	   	  }
    		String str=table.getString("gender");
    		if(str.matches("Male"))
    		{
    			 radMale.setSelected(true);
    		}
    		else if(str.matches("Female"))
    		{
    			
    			radFemale.setSelected(true);
    		}
    		Date date=table.getDate(8);
    		String ref=table.getString("ref");
    
    		
    		
    		System.out.println(name+"   "+picpath+"   "+address+"   "+city+"   "+email+"   "+str+"   "+date+"   "+ref);
    		txtName.setText(name);
    		lblPath.setText(picpath);
    		txtAddress.setText(address);
    		txtCity.setText(city);
    		txtEmail.setText(email);
    		txtReffer.setText(ref);
    		
    		
    		
    		
    		
    	}
    	}
    	catch(Exception exp)
    	{ 
    		exp.printStackTrace();
    	}
    	
    }


    @FXML
    void doLeft(ActionEvent event) 
    {
try {
	pst=con.prepareStatement("delete from customers where contact=?");
	pst.setString(1, comboCn.getEditor().getText());
	int count=pst.executeUpdate();
	if(count==0)
	{
		System.out.println("Wrong Id Please Check again");
	}
	else
	{
		System.out.println(count+" records deleted");
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
showMsg("Deleted successfully");


    }
    
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Its Result");
    	alert.setContentText(msg);
    	alert.show();
    }

    @FXML
    void doModify(ActionEvent event)
    {
    	try {
    		pst=con.prepareStatement("update customers set name=?,picpath=?,address=?,city=?,email=?,gender=?,ref=? where contact=?");
    		
    		pst.setString(1, txtName.getText());
    		pst.setString(2,lblPath.getText());
    		pst.setString(3, txtAddress.getText());
    		pst.setString(4, txtCity.getText());
    		pst.setString(5, txtEmail.getText());
    		if(radMale.isSelected())
    		{
    		pst.setString(6, radMale.getText());
    		}
    		else
    			if(radFemale.isSelected())
    			{
    				pst.setString(6, radFemale.getText());	
    			}
    		
    		pst.setString(7, txtReffer.getText());
    		pst.setString(8, comboCn.getEditor().getText());
    		
    		
		    int count=pst.executeUpdate();
    		if(count==0)
    			System.out.println("Invalid Id");
    		else
    			System.out.println(count+" Records Updated");
    		
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} 
    	
    	showMsg1("Updated successfully");

    }
    
    void showMsg1(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Its Result");
    	alert.setContentText(msg);
    	alert.show();
    }

    @FXML
    void doRefresh(ActionEvent event) 
    {
       txtName.setText(" ");
       txtAddress.setText(" ");
       txtCity.setText(" ");
       txtEmail.setText(" ");
       txtReffer.setText(" ");
       lblPath.setText(" ");
       iv1.setImage(null);
       if(radMale.isSelected())
		{
    	   radMale.setSelected(false);
		}
		else
			if(radFemale.isSelected())
			{
				radFemale.setSelected(false);
			}
    }

    @FXML
    void doRegister(ActionEvent event)
    {
try {
	pst=con.prepareStatement("insert into customers value(?,?,?,?,?,?,?,current_date(),?)");
	pst.setString(1,comboCn.getEditor().getText());
	pst.setString(2, txtName.getText());
	pst.setString(3, lblPath.getText());
	pst.setString(4, txtAddress.getText());
	pst.setString(5, txtCity.getText());
	pst.setString(6, txtEmail.getText());
	if(radMale.isSelected())
	{
	pst.setString(7, radMale.getText());
	}
	else
		if(radFemale.isSelected())
		{
			pst.setString(7, radFemale.getText());	
		}
	pst.setString(8, txtReffer.getText());
	pst.executeUpdate();
	
	System.out.println("Done");
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
showMsg3("Save successfully");

    }
    
    void showMsg3(String msg)
    {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Its Result");
    	alert.setContentText(msg);
    	alert.show();
    }
    void doFill()
    {
    	try{
    		pst=con.prepareStatement("select distinct contact from customers");
    	ResultSet table=	pst.executeQuery();
    	while(table.next())
    		{
    		String cn=table.getString("contact");
    		comboCn.getItems().add(String.valueOf(cn));
    		System.out.println(cn);
    		
    		}
    	}
    	catch(Exception exp)
    	{ 
    		exp.printStackTrace();
    	}
    }

    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
      con=DataBaseConnector.getConnection();
      doFill();
    }
}

