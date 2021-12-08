

package customermeasure;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CustomerMeasurementViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboDA"
    private ComboBox<String> comboDA; // Value injected by FXMLLoader

    @FXML // fx:id="txtArea"
    private TextArea txtArea; // Value injected by FXMLLoader

    @FXML // fx:id="datePicker"
    private DatePicker datePicker; // Value injected by FXMLLoader

    @FXML // fx:id="txtAmount"
    private TextField txtAmount; // Value injected by FXMLLoader

    @FXML // fx:id="comboSW"
    private ComboBox<String> comboSW; // Value injected by FXMLLoader

    @FXML // fx:id="txtOI"
    private TextField txtOI; // Value injected by FXMLLoader

    @FXML // fx:id="lblS"
    private Label lblS; // Value injected by FXMLLoader

    @FXML // fx:id="comboCmn"
    private ComboBox<String> comboCmn; // Value injected by FXMLLoader

    PreparedStatement pst;
    @FXML
    void doFill(ActionEvent event) {
    	
    	ArrayList<String> specialguy = new ArrayList<String>(Arrays.asList());
    	String dressselect = comboDA.getSelectionModel().getSelectedItem();
    	/*if(dressselect == "")
    	{
    		
    		comboDA.getItems().clear();
    		return;
    	}*/
    		
    	try {
			pst = con.prepareStatement("select name from employees where dresses like ?");
			pst.setString(1, "%" + dressselect + "%");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				specialguy.add(rs.getString("name"));
			}
			comboSW.getItems().clear();
			comboSW.getItems().addAll(specialguy);	
    	}
    	catch (SQLException e) {
			e.printStackTrace();
			}
	
    }
    
 void showMsg(String string)
 {
	 Alert alert=new Alert(AlertType.INFORMATION);
	 alert.setTitle("Information Dialogue");
	 alert.setHeaderText("look,an information dialogue");
	 alert.showAndWait();
	 }
    @FXML
    void doNew(ActionEvent event) 
    
    {
    	comboCmn.getEditor().setText(" ");
    	txtOI.setText(" ");
    	txtAmount.setText(" ");
    	comboDA.getEditor().setText(" ");
    	comboSW.getEditor().setText(" ");
    	txtArea.setText(" ");
    	datePicker.setPromptText(" ");
    
    }

    @FXML
    void doPlaceOrder(ActionEvent event)
    {
    	LocalDate dte=datePicker.getValue();

    	try {
			pst=con.prepareStatement("insert into orders values(?,?,?,?,?,?,?,1)",
			Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1,null);
			pst.setString(2,comboCmn.getEditor().getText());
			pst.setString(3,comboDA.getEditor().getText());
			pst.setString(4,comboSW.getEditor().getText());
			pst.setString(5,txtArea.getText());
			pst.setDate(6,java.sql.Date.valueOf(dte));
			pst.setInt(7,Integer.parseInt(txtAmount.getText()));
			txtOI.setDisable(false);
			
			int res=pst.executeUpdate();
			ResultSet oid=pst.getGeneratedKeys();
			
			int newOid=2;
			//if(res>0)
			//{
				//clearValues();
				if(oid.next())
				{
					newOid=oid.getInt(1);
					txtOI.setText(String.valueOf(newOid));
				}
				showMsg("oid saved successfully." + "'" +newOid +"'"+ "is your oid. remember it during delivery of your dress.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    void showMsg2(String msg)
    {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Its Result");
    	alert.setContentText(msg);
    	alert.show();
    }
    private String valueOf(int newOid)
    {
    	return null;
    }
    	     
    void doFillCombo()
    {
    	 try {
   			pst=con.prepareStatement("select distinct contact from customers");
   			ResultSet table=pst.executeQuery();
   			while(table.next())
   			{
   				String contact=table.getString("contact");
   				comboCmn.getItems().add(contact);
   				System.out.println(contact);
   			}
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
     }
    }
  //  @FXML
 //   void doFind(ActionEvent event)
  //   {
   //  }
     
    
    @FXML
    void doFind(ActionEvent event)
     {

    	try{
			pst=con.prepareStatement("select * from orders where oid=?"); //0 or 1
			pst.setString(1, txtOI.getText());
			ResultSet table=pst.executeQuery();
		
			while(table.next())
			{
				
				
    			String custmobile= table.getString("contact");
    			String dresses= table.getString("dress");
    			String specialguy= table.getString("worker");
    			String measurementtt= table.getString("measurement");
    			String dod= table.getDate("ddate").toString();
    			String amountt= table.getString("amount");
    		
    			comboCmn.setPromptText(custmobile+"  ");
    			comboDA.setPromptText(dresses+"   ");
    			comboSW.setPromptText(specialguy+" ");
    			txtArea.setText(measurementtt);
    			datePicker.setPromptText(dod);
    			txtAmount.setText(amountt);
    			
   	}
			
	}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
    	
    }
    
    
    Connection con;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	String dresses[]= {"Salwar suit","sharara","pajami suit","kurta pajama","coat pent","plazo","lehenga"
    			,"gharara","skirts","lehenga choli","patiala suit","jackets"};
    	comboDA.getItems().addAll(dresses);
    con=DataBaseConnector.getConnection();
    doFillCombo();
    
    }
}


