package passbiomed.view;



import java.awt.TextField;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import passbiomed.MainApp;
import passbiomed.model.Medicament;
import passbiomed.model.Operation;
import passbiomed.model.Trouble;

public class PatientOverviewController 
{
	
	private ObservableList<Medicament> medicamentData;
	private ObservableList<Trouble> troubleData;
	private ObservableList<Operation> operationData;
	
	private String loadedPatientID;
    private String loadedPassbiomedID;
    
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
    private TableView<Medicament> medicamentTable;
    @FXML
    private TableColumn<Medicament, String> nomChimiqueColonne;
    @FXML
    private TableColumn<Medicament, String> nomCommercialColonne;
    @FXML
    private TableColumn<Medicament, String> nomMedicamentUniColonne;
    
    @FXML
    private TableView<Operation> operationTable;
    @FXML
    private TableColumn<Operation, String> nomOperationColonne;
    @FXML
    private TableColumn<Operation, String> commentaireOpColonne;
    
    static PreparedStatement preparedStatement = null;
    
    @FXML
    private JFXTextField nomField;
    @FXML
    private JFXTextField prenomField;
    
    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private Label grpSanLabel;
    @FXML
    private Label adresseLabel;
    @FXML
    private Label codePosLabel;
    @FXML
    private Label villeLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label paysLabel;
    @FXML
    private Label sexeLabel;
    @FXML
    private Label iceNomLabel;
    @FXML
    private Label iceTelephoneLabel;
    
    @FXML
    private Button retourButton;
    
    private MainApp mainApp;
    
    public PatientOverviewController() {
    	
    	
    }
    
    @FXML
    private void initialize() {
    	loadedPatientID = "0";
    	loadedPassbiomedID = "0";
    	//Tableview de medicament
    	nomChimiqueColonne.setCellValueFactory(new PropertyValueFactory<Medicament, String>("nomChimique"));
    	nomMedicamentUniColonne.setCellValueFactory(new PropertyValueFactory<Medicament, String>("nomUniversel"));
    	nomCommercialColonne.setCellValueFactory(new PropertyValueFactory<Medicament, String>("nomCommercial"));
    	
    	//Tableview de troubles
    	nomUniverselColonne.setCellValueFactory(new PropertyValueFactory<Trouble, String>("nomUniversel"));
    	nomCommunColonne.setCellValueFactory(new PropertyValueFactory<Trouble, String>("nomCommun"));
    	sousTypeColonne.setCellValueFactory(new PropertyValueFactory<Trouble, String>("sousType"));
    	masterTypeColonne.setCellValueFactory(new PropertyValueFactory<Trouble, String>("masterType"));
    	
    	//Tableview d'opération et traitement
    	nomOperationColonne.setCellValueFactory(new PropertyValueFactory<Operation, String>("nomOperation"));
    	commentaireOpColonne.setCellValueFactory(new PropertyValueFactory<Operation, String>("commentaire"));
    }
    
    
    @FXML
    private void handleOk() {
    	
    	String nomFieldS = this.nomField.getText();
    	String prenomFieldS = this.prenomField.getText();
        if (isInputValid()) {
        	
        	String sql = "SELECT * FROM Patient WHERE Nom = ? and Prenom = ?";
    		try {
    			Class.forName("com.mysql.jdbc.Driver");
    			System.out.println("Driver OK");
    			
    			String url = "jdbc:mysql://localhost:3306/passbiomed_v3";
    			String user = "root";
    			String password = "Secret123";
    			
    			Connection connect = (Connection) DriverManager.getConnection(url, user, password);
    			ResultSet resultSet = null;
    			
    			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    			preparedStatement.setString(1, nomFieldS);
    			preparedStatement.setString(2, prenomFieldS);
    			
    			resultSet = preparedStatement.executeQuery();
    			
    			if(resultSet.next())
    			{
    				System.out.println("Patient trouvé");
    				loadedPatientID=resultSet.getString(1);
    				loadedPassbiomedID=resultSet.getString("IDpasseport_biomed");
    				displayData();
    			}
    			else
    			{
    				System.out.println("Patient non-trouvé");
    				Alert alert = new Alert(AlertType.ERROR);
    	            alert.setTitle("Erreur");
    	            alert.setHeaderText("Erreur");
    	            alert.setContentText("Patient n'a pas été trouvé");
    	            alert.showAndWait();
    			}
    			
    			preparedStatement.close();
    			resultSet.close();
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
        }
    }
    
    
    private boolean isInputValid() {
        String errorMessage = "";

        if (nomField == null) {
            errorMessage += "No valid first name!\n"; 
        }
        if (prenomField == null) {
            errorMessage += "No valid last name!\n"; 
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    
    
    private void displayData() 
    {
    	medicamentData = FXCollections.observableArrayList();
    	troubleData = FXCollections.observableArrayList();
    	operationData = FXCollections.observableArrayList();
    	
    	Medicament tempMedicament;
    	Trouble tempTrouble;
    	Operation tempOperation;
    	
    	String sql = "SELECT * FROM Patient WHERE IDPatient = ?";
    	String sql2 = "Select medicament.Nom_commercial, medicament.Nom_universel, medicament.Nom_chimique_IUPAC from patient\n" + 
    			"inner join passeport_biomed using(IDPasseport_biomed)\n" + 
    			"inner join repertorier using(IDPasseport_biomed)\n" + 
    			"inner join medicament using(IDMedicament)\n" + 
    			"where IDPasseport_biomed=? ;";
    	String sql3 = "Select troubles.Code_CIM, troubles.Nom_commun, sous_type.Nom_sous_type, type_trouble.Nom_type_trouble from patient\n" + 
    			"inner join passeport_biomed using (IDPasseport_biomed)\n" + 
    			"inner join consigner using (IDPasseport_biomed)\n" + 
    			"inner join troubles using (IDTrouble)\n" + 
    			"inner join sous_type using (IDSous_type)\n" + 
    			"inner join type_trouble using(IDType_trouble)\n" +
    			"where IDPasseport_biomed = ?;";
    	String sql4 = "Select operation_traitement.Nom, operation_traitement.Commentaire from patient\n" +
    			"inner join passeport_biomed using(IDPasseport_biomed)\n" +
    			"inner join subir using(IDPasseport_biomed)\n" +
    			"inner join operation_traitement using(ID_T_O)\n";
    	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver OK");
			
			String url = "jdbc:mysql://localhost:3306/passbiomed_v3";
			String user = "root";
			String password = "Secret123";
			
			Connection connect = (Connection) DriverManager.getConnection(url, user, password);
			ResultSet resultSet = null;
			
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, loadedPatientID);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				nomLabel.setText(resultSet.getString("Nom"));
				prenomLabel.setText(resultSet.getString("Prenom"));
				grpSanLabel.setText(resultSet.getString("Groupe_sanguin"));
				adresseLabel.setText(resultSet.getString("Adresse"));
				codePosLabel.setText(resultSet.getString("Code_postal"));
				villeLabel.setText(resultSet.getString("Localite"));
				phoneLabel.setText(resultSet.getString("Telephone"));
				paysLabel.setText(resultSet.getString("Pays"));
				sexeLabel.setText(resultSet.getString("Sexe"));
				iceNomLabel.setText(resultSet.getString("ICE_nom"));
				iceTelephoneLabel.setText(resultSet.getString("ICE_telephone"));
				
				preparedStatement =(PreparedStatement) connect.prepareStatement(sql2);
				preparedStatement.setString(1, loadedPassbiomedID);
				
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next())
				{
					tempMedicament = new Medicament(resultSet.getString("Nom_universel")
								,resultSet.getString("Nom_commercial")
								,resultSet.getString("Nom_chimique_IUPAC"));
					
					medicamentData.add(tempMedicament);
				}
				medicamentTable.setItems(medicamentData);
				
				preparedStatement =(PreparedStatement) connect.prepareStatement(sql3);
				preparedStatement.setString(1, loadedPassbiomedID);
				
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next())
				{
					tempTrouble = new Trouble();
					tempTrouble.setNomUniversel(resultSet.getString("Code_CIM"));
					tempTrouble.setNomCommun(resultSet.getString("Nom_commun"));
					tempTrouble.setSousType(resultSet.getString("Nom_sous_type"));
					tempTrouble.setMasterType(resultSet.getString("Nom_type_trouble"));
					
					troubleData.add(tempTrouble);
				}
				troubleTable.setItems(troubleData);
				/*
				preparedStatement =(PreparedStatement) connect.prepareStatement(sql4);
				preparedStatement.setString(1, loadedPassbiomedID);
				
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next())
				{
					tempOperation = new Operation();
					tempOperation.setNomOperation(resultSet.getString("Nom"));
					tempOperation.setCommentaire(resultSet.getString("Commentaire"));
					
					operationData.add(tempOperation);
				}
				operationTable.setItems(operationData);*/
			}
			else
			{
				System.out.println("Patient non-trouvé");
			}
			
			
			preparedStatement.close();
			resultSet.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    @FXML
    private void handleRetour() 
    {
    	Stage stage = (Stage) retourButton.getScene().getWindow();
    		try 
    		{
    			FXMLLoader fxmlLoader = new FXMLLoader();
    			fxmlLoader.setLocation(getClass().getResource("MainMenu.fxml"));
		
    			Scene scene = new Scene(fxmlLoader.load(),1000, 600);
    			stage.setScene(scene);
    			stage.centerOnScreen();
    			stage.show();
    		
    		}catch (Exception e) 
    			{
    				e.printStackTrace();
    			}
    	
    }
    
    
    
    @FXML
    private void handleNewTrouble() {
    	if(loadedPatientID.contentEquals("0"))
    	{
    		Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun Patient");
            alert.setContentText("Aucun patient n'a ete charge");
            alert.showAndWait();
    	}
    	else
    	{
    		try 
    		{
    		    FXMLLoader fxmlLoader = new FXMLLoader();
    		    fxmlLoader.setLocation(getClass().getResource("MaladieWindow.fxml"));
    		    
    		    Parent root =(Parent)fxmlLoader.load();
    		    
    		    MaladieWindowController controller = fxmlLoader.<MaladieWindowController>getController();
    		    controller.setPassBioMedID(loadedPassbiomedID);
    		    
    		    
    		    
    		    Scene scene = new Scene(root);
    		    
    		    Stage stage = new Stage();
    		    stage.setTitle("Ajout d'un trouble");
    		    stage.setScene(scene);
    		    stage.centerOnScreen();
    		    stage.initModality(Modality.APPLICATION_MODAL);
    		    stage.show();
    		    		
    		 }catch (Exception e) 
    		    {
    				e.printStackTrace();
    		    }
    		 }
    }
    
    @FXML
    private void handleDeleteTrouble() {
    	if(loadedPatientID.contentEquals("0"))
    	{
    		Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun Patient");
            alert.setContentText("Aucun patient n'a ete charge");
            alert.showAndWait();
    	}
    	else
    	{
    		Trouble temptrouble = troubleTable.getSelectionModel().getSelectedItem();
    		try {
    			Class.forName("com.mysql.jdbc.Driver");
    			System.out.println("Driver OK");
    			
    			String url = "jdbc:mysql://localhost:3306/passbiomed_v3";
    			String user = "root";
    			String password = "Secret123";
    			
    			Connection connect = (Connection) DriverManager.getConnection(url, user, password);
    			ResultSet resultSet = null;
    			
    			//DELETE FROM `passbiomed_v3`.`consigner` WHERE `IDConsigner`=?;
    			
    		}catch (Exception e) {
				e.printStackTrace();
    		}
    	}
    }
    
    @FXML
    private void handleEditTrouble() {
    	if(loadedPatientID.contentEquals("0"))
    	{
    		Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun Patient");
            alert.setContentText("Aucun patient n'a ete charge");
            alert.showAndWait();
    	}
    	else
    	{
    		
    	}
    }
    
    
    @FXML
    private void handleNewMedic() {
    	if(loadedPatientID.contentEquals("0"))
    	{
    		Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun Patient");
            alert.setContentText("Aucun patient n'a ete charge");
            alert.showAndWait();
    	}
    	else
    	{

	    	try 
	    	{
	    		FXMLLoader fxmlLoader = new FXMLLoader();
	    		fxmlLoader.setLocation(getClass().getResource("MedicWindow.fxml"));
	    		
	    		Scene scene = new Scene(fxmlLoader.load());
	    		Stage stage = new Stage();
	    		
	    		stage.setTitle("Ajout d'un médicament");
	    		stage.setScene(scene);
	    		stage.centerOnScreen();
	    		stage.initModality(Modality.APPLICATION_MODAL);
	    		stage.show();
	    		
	    	}catch (Exception e) 
	    		{
				e.printStackTrace();
	    		}
	    	
    		}
    	
    }
    
    @FXML
    private void handleDeleteMedic() {
    	if(loadedPatientID.contentEquals("0"))
    	{
    		Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun Patient");
            alert.setContentText("Aucun patient n'a ete charge");
            alert.showAndWait();
    	}
    	else
    	{
    		
    	}
    }
    
    @FXML
    private void handleEditMedic() {
    	if(loadedPatientID.contentEquals("0"))
    	{
    		Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun Patient");
            alert.setContentText("Aucun patient n'a ete charge");
            alert.showAndWait();
    	}
    	else
    	{
    		
    	}
    }
}