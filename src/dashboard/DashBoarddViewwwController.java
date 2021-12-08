package dashboard;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DashBoarddViewwwController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    void doDelivery(MouseEvent event) 
    {
    	 try {
    			
    			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("dressdelivery/DressDeliverView.fxml")); 
    			Scene scene = new Scene(root);
    			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    			Stage stage=new Stage();
    		    stage.setScene(scene);
    		    stage.show();
    	       }    
    	       catch(Exception e) 
    	       {
    		     e.printStackTrace();
    	        }

    }

    @FXML
    void doOpenCustomerDataTable(MouseEvent event)
    {
    	try
    
    	{
    	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("tablerecords/TableViewww.fxml")); 
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage=new Stage();
	    stage.setScene(scene);
	    stage.show();
       }    
       catch(Exception e) 
       {
	     e.printStackTrace();
        
    }
    }

    @FXML
    void doOpenCustomerPanel(MouseEvent event) 
    {
    	try
        
    	{
    	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("customerpanel/CustomerView.fxml")); 
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage=new Stage();
	    stage.setScene(scene);
	    stage.show();
       }    
       catch(Exception e) 
       {
	     e.printStackTrace();
        
    }
    }

    @FXML
    void doOpenEmployeesData(MouseEvent event)
    {
    	try
        
    	{
    	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("table1records/TablesViewww.fxml")); 
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage=new Stage();
	    stage.setScene(scene);
	    stage.show();
       }    
       catch(Exception e) 
       {
	     e.printStackTrace();
        
    }
    }

    @FXML
    void doOpenEmployeesPanel(MouseEvent event) 
    {
    	try
        
    	{
    	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("employeeroom/EmployeeRoomView.fxml")); 
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage=new Stage();
	    stage.setScene(scene);
	    stage.show();
       }    
       catch(Exception e) 
       {
	     e.printStackTrace();
        
    }
    }

    @FXML
    void doOpenMeasurement(MouseEvent event) 
    {
    	try
        
    	{
    	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("customermeasure/CustomerMeasurementView.fxml")); 
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage=new Stage();
	    stage.setScene(scene);
	    stage.show();
       }    
       catch(Exception e) 
       {
	     e.printStackTrace();
        
    }
    }

    @FXML
    void doOpenOrdersPanel(MouseEvent event) 
    {
    	try
        
    	{
    	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("ordersgoogler/OrdersStatusView.fxml")); 
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage=new Stage();
	    stage.setScene(scene);
	    stage.show();
       }    
       catch(Exception e) 
       {
	     e.printStackTrace();
        
    }
    }
    
    @FXML
    void doOpenDevelopers(MouseEvent event)
    {
try
        
    	{
    	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("developers/DevelopersViewww.fxml")); 
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage=new Stage();
	    stage.setScene(scene);
	    stage.show();
       }    
       catch(Exception e) 
       {
	     e.printStackTrace();
        
    }
    }

    Connection  con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
      con=DataBaseConnector.getConnection();
    }
}
