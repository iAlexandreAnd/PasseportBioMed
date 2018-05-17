package passbiomed.view;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import passbiomed.model.Trouble;

import org.controlsfx.control.textfield.TextFields;


public class MaladieWindowController implements Initializable
{
	private String loadedPassbiomedID;
	
	static PreparedStatement preparedStatement = null;
	
	private ObservableList<Trouble> troubleData = FXCollections.observableArrayList();
	private ObservableList<String> troubleDataString = FXCollections.observableArrayList();
	FilteredList<Trouble> filteredData;
	@FXML
    private TableView<Trouble> troubleTable;
    @FXML
    private TableColumn<Trouble, String> nomUniverselColonne;
    @FXML
    private TableColumn<Trouble, String> nomCommunColonne;
    @FXML
    private TableColumn<Trouble, String> sousTypeColonne;
    @FXML
    private TableColumn<Trouble, String> masterTypeColonne;
    
    
    @FXML
    private Label labelTest;
    
	@FXML
    private GridPane borderGrid;
	
    @FXML
    private AnchorPane AnchorWindow;

    @FXML
    private TextField filterField;

    @FXML
    private JFXComboBox<?> comboBoxTypeMaladie;

    @FXML
    private JFXButton boutonRechercheMaladie;
    
    @FXML
    private Pane paneHeaderPatho;
    
    @FXML
    private JFXButton addToPatient;
	  
//	final String cssGrid = "-fx-border-color: #A0A0A0;" 
//			   +"-fx-border-width: 0px 4px 4px 4px";
//	   
    
    public void setPassBioMedID(String id) {
    	this.loadedPassbiomedID = id;
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	nomUniverselColonne.setCellValueFactory(new PropertyValueFactory<Trouble, String>("nomUniversel"));
    	nomCommunColonne.setCellValueFactory(new PropertyValueFactory<Trouble, String>("nomCommun"));
    	sousTypeColonne.setCellValueFactory(new PropertyValueFactory<Trouble, String>("sousType"));
    	masterTypeColonne.setCellValueFactory(new PropertyValueFactory<Trouble, String>("masterType"));
    	
    	displayData();	
		labelTest.setText(loadedPassbiomedID);
    	System.out.print("ID : ");
		System.out.println(loadedPassbiomedID);
		
		TextFields.bindAutoCompletion(filterField, troubleDataString);
	}
	  /*@FXML
	  private void initialize() 
	  {
		  nomUniverselColonne.setCellValueFactory(new PropertyValueFactory<Trouble, String>("nomUniversel"));
	    	nomCommunColonne.setCellValueFactory(new PropertyValueFactory<Trouble, String>("nomCommun"));
	    	sousTypeColonne.setCellValueFactory(new PropertyValueFactory<Trouble, String>("sousType"));
	    	masterTypeColonne.setCellValueFactory(new PropertyValueFactory<Trouble, String>("masterType"));
	    	
	    	displayData();	
			labelTest.setText(loadedPassbiomedID);
	    	TextFields.bindAutoCompletion(filterField, troubleDataString);
		  	
	  }*/
	  
	  private void displayData() {
		  
		  
		  Trouble tempTrouble;
		  try {
		  	Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver OK");
			
			String url = "jdbc:mysql://localhost:3306/passbiomed_v3";
			String user = "root";
			String password = "Secret123";
			
			String sql = "Select troubles.Code_CIM, troubles.Nom_commun, sous_type.Nom_sous_type, type_trouble.Nom_type_trouble from troubles\n" + 
	    			"inner join sous_type using (IDSous_type)\n" + 
	    			"inner join type_trouble using(IDType_trouble)\n";
			
			Connection connect = (Connection) DriverManager.getConnection(url, user, password);
			ResultSet resultSet = null;
			preparedStatement =(PreparedStatement) connect.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				tempTrouble = new Trouble();
				tempTrouble.setNomUniversel(resultSet.getString("Code_CIM"));
				tempTrouble.setNomCommun(resultSet.getString("Nom_commun"));
				tempTrouble.setSousType(resultSet.getString("Nom_sous_type"));
				tempTrouble.setMasterType(resultSet.getString("Nom_type_trouble"));
				
				troubleData.add(tempTrouble);
				troubleDataString.add(tempTrouble.getNomCommun());
				troubleDataString.add(tempTrouble.getNomUniversel());
				troubleDataString.add(tempTrouble.getMasterType());
			}
			
			troubleTable.setItems(troubleData);
			
			preparedStatement.close();
			resultSet.close();
			
		  }catch (Exception e) {
			e.printStackTrace();
		  }
		  
		  filteredData = new FilteredList<>(troubleData, p -> true);
		  filterField.textProperty().addListener((observable, oldValue, newValue) -> {
	            filteredData.setPredicate(trouble -> {
	                // If filter text is empty, display all persons.
	                if (newValue == null || newValue.isEmpty()) {
	                    return true;
	                }
	             // Compare first name and last name of every person with filter text.
	                String lowerCaseFilter = newValue.toLowerCase();

	                if (trouble.getNomCommun().toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // Filtre pour nom commun
	                } else if (trouble.getNomUniversel().toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // Filtre pour nom universel (code CIM-10)
	                } else if (trouble.getMasterType().toLowerCase().contains(lowerCaseFilter)) {
	                	return true; //Filtre pour type
	                } else if (trouble.getSousType().toLowerCase().contains(lowerCaseFilter)) {
	                	return true; //Filtre pour soustype
	                }
	                return false; // Does not match.
	            });
		  });
		  
		  SortedList<Trouble> sortedData = new SortedList<>(filteredData);
		  
		  sortedData.comparatorProperty().bind(troubleTable.comparatorProperty());
		  
		  troubleTable.setItems(sortedData);
		  
	  }
	  
	  @FXML
	  private void handleAddTrouble()
	  {
		    labelTest.setText(loadedPassbiomedID);
	    	System.out.print("ID : ");
			System.out.println(loadedPassbiomedID);
	  }


	
}