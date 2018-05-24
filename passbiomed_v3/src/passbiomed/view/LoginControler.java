package passbiomed.view;

import java.awt.Label;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

//import javax.xml.bind.TypeConstraintException;

import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class LoginControler 
{

	private int data;
	
//	
//	  @FXML
//	  private javafx.scene.control.Button connectButton;
//	  @FXML
//    private TextField loginField;
//    @FXML
//    private TextField passwordField;
	
		ObservableList<String> typeConnctList = FXCollections.observableArrayList("Medecin", "Patient");
	
 
		@FXML
	    private JFXTextField loginField;

	    @FXML
	    private JFXPasswordField passwordField;

	    @FXML

	    private JFXButton connectButton;    

	    @FXML
	    private JFXComboBox<String> typeConnexion;
	    
	    @FXML
	    private AnchorPane PaneEssai;


	  @FXML
	  private void initialize() 
	  {
//		  typeConnexion.setValue("Type Connexion");
		  typeConnexion.setItems(typeConnctList);	
		  typeConnexion.setPromptText("Connexion type");
		  
//		  PaneEssai.setDisable(true);

	  }

    
    static PreparedStatement preparedStatement = null;

//	Test remplissage CBox   

    
//    public String getValueCombo() 
//    {
//    		String str = typeConnexion.getValue();
//    		
//    		return str;
//    }
    
    
    private void LoginControler() 
    {

    }
    
    
    int getData() 
    {
    		return data;
    }
    
    @FXML
    private void handleConnect()
    {
    	
    	int verificationLogin = ConnectDataBase(loginField.getText(),passwordField.getText());
    	
    	if(verificationLogin == 1)
    	{
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Connexion OK");
    		alert.setHeaderText("Connexion OK");
    		alert.setContentText("Connexion OK");
    		
    		data = 1;
    		
    		Stage stage = (Stage) connectButton.getScene().getWindow();
    		stage.close();
    		
    		newMainWindow();
    		
    	}
    	else
    	{
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Erreur");
    		alert.setHeaderText("Erreur");
    		alert.setContentText("Erreur");
    		
    		alert.showAndWait();
    		
    	}
    	
    }
    
    
    
    public static int ConnectDataBase (String loginField, String passwordField) 
    {
    	int verificationLogin =0;
		try {
			String query = "{call get_login_password(?,?)}"; //Procedure
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver OK");
			
			String url = "jdbc:mysql://localhost:3306/passbiomed_v3";
			String user = "root";
			String password = "Secret123";
			
			
			Connection connect = (Connection) DriverManager.getConnection(url, user, password);
			CallableStatement statement =  (CallableStatement) connect.prepareCall(query);
			statement.setString(1, loginField);
			statement.setString(2, passwordField);
			
			ResultSet resultSet = null;
			
			resultSet = statement.executeQuery();
			
			
			if(resultSet.next())
			{
				verificationLogin=1;
				System.out.println("Verification : Succes");		
			}
			else
			{
				verificationLogin=0;
			}
			resultSet.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return verificationLogin;
	}
    
    private void newMainWindow() 
    {
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader();
    		fxmlLoader.setLocation(getClass().getResource("MainMenu.fxml"));
    		
    		Scene scene = new Scene(fxmlLoader.load(),1000, 600);
    		Stage stage = new Stage();
    		
    		stage.setTitle("Main Menu");
    		stage.setScene(scene);
    		stage.show();
    		
    	}catch (Exception e) 
    	{
			e.printStackTrace();
    	}
    	
    }
}