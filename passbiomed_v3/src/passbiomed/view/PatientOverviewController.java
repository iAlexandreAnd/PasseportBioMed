package passbiomed.view;



import java.awt.Checkbox;
import java.awt.TextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore.ProtectionParameter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalQuery;
import java.util.Date;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import passbiomed.MainApp;
import passbiomed.model.Medicament;
import passbiomed.model.Operation;
import passbiomed.model.Patient;
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
    private TableColumn<Trouble, String> dateConsignerColonne;
    @FXML
    private TableColumn<Trouble, String> flagImportance;
    @FXML
    private TableColumn<Trouble, String> flagActif;
    

    
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
    private AnchorPane paneInfoPatient;
    
    /*deuxieme vague*/
    
    
    @FXML
    private JFXTextField nomLabel2; 

    @FXML
    private JFXTextField prenomLabel2;

    @FXML
    private JFXTextField grpSanLabel2;

    @FXML
    private JFXTextField adresseLabel2;

    @FXML
    private JFXTextField codePosLabel2;

    @FXML
    private JFXTextField villeLabel2;

    @FXML
    private JFXTextField phoneLabel2;

    @FXML
    private JFXTextField birthdayLabel2;
    
    @FXML
    private JFXTextField paysLabel2;

    @FXML
    private JFXTextField sexeLabel2;

    @FXML
    private JFXTextField iceNomLabel2;

    @FXML
    private JFXTextField iceTelephoneLabel2;
    
    @FXML
    private JFXTextField emailLabel2;
    
    @FXML
    private GridPane editGridPatient;
    
    @FXML
    private JFXButton buttonEdit1;
    
    @FXML
    private JFXButton buttonEdit2;
    
    @FXML
    private JFXButton buttonEdit3;
   
    
    @FXML
    private Button retourButton;
    
    private MainApp mainApp;
    
    public PatientOverviewController() 
    {
    	
    	
    }
    
    @FXML
    private void initialize() 
    {
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
    	dateConsignerColonne.setCellValueFactory(new PropertyValueFactory<Trouble, String>("dateConsigner"));
    	flagImportance.setCellValueFactory(new PropertyValueFactory<Trouble, String>("important"));
    	flagActif.setCellValueFactory(new PropertyValueFactory<Trouble, String>("actif"));
    	
    	//Tableview d'operation et traitement
    	nomOperationColonne.setCellValueFactory(new PropertyValueFactory<Operation, String>("nomOperation"));
    	commentaireOpColonne.setCellValueFactory(new PropertyValueFactory<Operation, String>("commentaire"));

    flagImportance.setStyle("-fx-alignment: CENTER;");
    	flagActif.setStyle("-fx-alignment: CENTER;");
    	
//    //////////////////////////////////////////////////////////////// 
//    	//boutton 1	
//    	FileInputStream input = null;
//		try 
//		{
//			input = new FileInputStream("Ressources/Icons/007-cogwheel.png");
//		} 
//		catch (FileNotFoundException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    
//	
//	Image image = new Image(input);
//    ImageView imageView = new ImageView(image);
//    imageView.setFitWidth(30);
//    imageView.setPreserveRatio(true);
//    imageView.setSmooth(true);
//    imageView.setCache(true); 
//   
//    buttonEdit1.setGraphic(imageView);
//    
//    ////////////////////////////////////////////////////////////////   
//    //boutton 1	
//    FileInputStream input2 = null;
//	try 
//	{
//		input2 = new FileInputStream("Ressources/Icons/005-check-1.png");
//	} 
//	catch (FileNotFoundException e) 
//	{
//		// 
//		e.printStackTrace();
//	}
//
//	
//	Image image2 = new Image(input2);
//	ImageView imageView2 = new ImageView(image2);
//	imageView2.setFitWidth(30);
//	imageView2.setPreserveRatio(true);
//	imageView2.setSmooth(true);
//	imageView2.setCache(true); 
//
//	buttonEdit2.setGraphic(imageView2);
//	
	////////////////////////////////////////////////////////////////
    
    	
    	initTextFildsClosed();
    	
    	
    }
    
    void initTextFildsClosed() 
    {
    	
       nomLabel2.setEditable(false);
       prenomLabel2.setEditable(false);
       grpSanLabel2.setEditable(false);
       adresseLabel2.setEditable(false);
       codePosLabel2.setEditable(false);
       villeLabel2.setEditable(false);
       phoneLabel2.setEditable(false);
       birthdayLabel2.setEditable(false);
       paysLabel2.setEditable(false);
       sexeLabel2.setEditable(false);
       iceNomLabel2.setEditable(false);
       iceTelephoneLabel2.setEditable(false);
       emailLabel2.setEditable(false);
       buttonEdit2.setVisible(false);
       buttonEdit3.setVisible(false);
       
    }
   
    @FXML
    void editPatientOn() 
    {
    		nomLabel2.setEditable(true);
        prenomLabel2.setEditable(true);
        grpSanLabel2.setEditable(true);
        adresseLabel2.setEditable(true);
        codePosLabel2.setEditable(true);
        villeLabel2.setEditable(true);
        phoneLabel2.setEditable(true);
        birthdayLabel2.setEditable(true);
        paysLabel2.setEditable(true);
        sexeLabel2.setEditable(true);
        iceNomLabel2.setEditable(true);
        iceTelephoneLabel2.setEditable(true);
        emailLabel2.setEditable(true);
        buttonEdit2.setVisible(true);
        buttonEdit3.setVisible(true);
        buttonEdit1.setVisible(false);
    	
    }
    
    @FXML
    void editPatientCancel() 
    {
    		nomLabel2.setEditable(false);
        prenomLabel2.setEditable(false);
        grpSanLabel2.setEditable(false);
        adresseLabel2.setEditable(false);
        codePosLabel2.setEditable(false);
        villeLabel2.setEditable(false);
        phoneLabel2.setEditable(false);
        birthdayLabel2.setEditable(false);
        paysLabel2.setEditable(false);
        sexeLabel2.setEditable(false);
        iceNomLabel2.setEditable(false);
        iceTelephoneLabel2.setEditable(false);
        emailLabel2.setEditable(false);
        buttonEdit2.setVisible(false);
        buttonEdit3.setVisible(false);
        buttonEdit1.setVisible(true);
        
        handleOk();
    }
    
    @FXML
    private void handleOk() 
    {
    	
    	String nomFieldS = this.nomField.getText();
    	String prenomFieldS = this.prenomField.getText();
        if (isInputValid()) 
        {
        	
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
    				System.out.println("Patient trouve");
    				loadedPatientID=resultSet.getString(1);
    				loadedPassbiomedID=resultSet.getString("IDpasseport_biomed");
    				displayData();
    			}
    			else
    			{
    				System.out.println("Patient non-trouve");
    				Alert alert = new Alert(AlertType.ERROR);
    	            alert.setTitle("Erreur");
    	            alert.setHeaderText("Erreur");
    	            alert.setContentText("Patient n'a pas ete trouve");
    	            alert.showAndWait();
    			}
    			
    			preparedStatement.close();
    			resultSet.close();
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
        }
    }
    
    
    private boolean isInputValid() 
    {
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
    	String sql3 = "Select troubles.Code_CIM, troubles.Nom_commun, sous_type.Nom_sous_type, type_trouble.Nom_type_trouble, Consigner.DateEntreeConsigner, Consigner.Important, Consigner.Actif, Consigner.IDConsigner from patient\n" + 
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
			
				nomLabel2.setText(resultSet.getString("Nom"));
				prenomLabel2.setText(resultSet.getString("Prenom"));
				grpSanLabel2.setText(resultSet.getString("Groupe_sanguin"));
				adresseLabel2.setText(resultSet.getString("Adresse"));
				codePosLabel2.setText(resultSet.getString("Code_postal"));
				villeLabel2.setText(resultSet.getString("Localite"));
				phoneLabel2.setText(resultSet.getString("Telephone"));
				birthdayLabel2.setText(resultSet.getString("Date_naissance"));
				paysLabel2.setText(resultSet.getString("Pays"));
				sexeLabel2.setText(resultSet.getString("Sexe"));
				iceNomLabel2.setText(resultSet.getString("ICE_nom"));
				iceTelephoneLabel2.setText(resultSet.getString("ICE_telephone"));
				emailLabel2.setText(resultSet.getString("Email"));
				
				
				
				
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
					tempTrouble.setiDConsigner(resultSet.getInt("IDConsigner"));
					
					System.out.println("ID consigner: "+tempTrouble.getiDConsigner());
					
//					String dateEssai = resultSet.getDate("DateEntreeConsigner").toString();
					
					Date date = resultSet.getDate("DateEntreeConsigner");
					DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
					String dateFinal = df.format(date);
					System.out.println(dateFinal);
					CheckBox essai = new CheckBox();
					
					int flagImportance = resultSet.getInt("Important");
					
					if(flagImportance == 1) 
					{	
						System.out.println("");
						System.out.println("*******************");
						System.out.println("Nom: "+tempTrouble.getNomCommun());
						System.out.println("Bool retourné: "+flagImportance);
						System.out.println("*******************");
						
						boolean checkboxSelection = true;
						essai.setSelected(checkboxSelection);
					}
					else 
					{
						System.out.println("");
						System.out.println("*******************");
						System.out.println("Nom: "+tempTrouble.getNomCommun());
						System.out.println("Bool retourné: "+flagImportance);
						System.out.println("*******************");
						
						boolean checkboxSelection = false;
						essai.setSelected(checkboxSelection);
						
					}
					
					CheckBox actif = new CheckBox();
					int flagActif = resultSet.getInt("Actif");
					
					System.out.println("Flag actif: "+flagActif);
					
					
					if(flagActif == 1) 
					{
						boolean checkBoxActif = true;
						actif.setSelected(checkBoxActif);
					}
					else 
					{
						boolean checkBoxActif = false;
						actif.setSelected(checkBoxActif);
					}

					tempTrouble.setImportant(essai);				
					tempTrouble.setActif(actif);
					tempTrouble.setDateConsigner(dateFinal);
					
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
				System.out.println("Patient non-trouve");
			}
			
			
			preparedStatement.close();
			resultSet.close();
			
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
    }
    
    private void refreshTabTroubles() 
    {
    	troubleData = FXCollections.observableArrayList();

    	
    	Trouble tempTrouble;

    	String sql = "Select troubles.Code_CIM, troubles.Nom_commun, sous_type.Nom_sous_type, type_trouble.Nom_type_trouble, Consigner.DateEntreeConsigner, Consigner.Important, Consigner.Actif, Consigner.IDConsigner from patient\n" + 
    			"inner join passeport_biomed using (IDPasseport_biomed)\n" + 
    			"inner join consigner using (IDPasseport_biomed)\n" + 
    			"inner join troubles using (IDTrouble)\n" + 
    			"inner join sous_type using (IDSous_type)\n" + 
    			"inner join type_trouble using(IDType_trouble)\n" +
    			"where IDPasseport_biomed = ?;";

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
				
				preparedStatement =(PreparedStatement) connect.prepareStatement(sql);
				preparedStatement.setString(1, loadedPassbiomedID);
				
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next())
				{
					tempTrouble = new Trouble();
					tempTrouble.setNomUniversel(resultSet.getString("Code_CIM"));
					tempTrouble.setNomCommun(resultSet.getString("Nom_commun"));
					tempTrouble.setSousType(resultSet.getString("Nom_sous_type"));
					tempTrouble.setMasterType(resultSet.getString("Nom_type_trouble"));
					tempTrouble.setiDConsigner(resultSet.getInt("IDConsigner"));
					
					Date date = resultSet.getDate("DateEntreeConsigner");
					DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
					String dateFinal = df.format(date);
					System.out.println(dateFinal);
					CheckBox essai = new CheckBox();
					
					int flagImportance = resultSet.getInt("Important");
					
					if(flagImportance == 1) 
					{	
						boolean checkboxSelection = true;
						essai.setSelected(checkboxSelection);
					}
					else 
					{						
						boolean checkboxSelection = false;
						essai.setSelected(checkboxSelection);
					}
				
					CheckBox actif = new CheckBox();
					int flagActif = resultSet.getInt("Actif");
					
					if(flagActif == 1) 
					{
						boolean checkBoxActif = true;
						actif.setSelected(checkBoxActif);
					}
					else 
					{
						boolean checkBoxActif = false;
						actif.setSelected(checkBoxActif);
					}

					tempTrouble.setImportant(essai);				
					tempTrouble.setActif(actif);
					tempTrouble.setDateConsigner(dateFinal);
					
					troubleData.add(tempTrouble);
				}
				troubleTable.setItems(troubleData);

			}
			else
			{
				System.out.println("Patient non-trouve");
			}
				
			preparedStatement.close();
			resultSet.close();
			
		}catch (Exception e) 
		{
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
    private void handleDeleteTrouble() 
    {
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
    		Trouble tempTrouble = troubleTable.getSelectionModel().getSelectedItem();
    		try {
    			
    			Class.forName("com.mysql.jdbc.Driver");
    			System.out.println("Driver OK");
    			
    			String url = "jdbc:mysql://localhost:3306/passbiomed_v3";
    			String user = "root";
    			String password = "Secret123";
    			
    			int idConsignerDel = tempTrouble.getiDConsigner();
    			System.out.println("Id trouble deleted: "+ idConsignerDel);
    			
    			
    			String sql = "DELETE FROM Consigner WHERE `IDConsigner`=?;";
    			
    			Connection connect = (Connection) DriverManager.getConnection(url, user, password);
    			
    			
    			PreparedStatement prepStat =(PreparedStatement) connect.prepareStatement(sql);
    			prepStat.setInt(1, idConsignerDel );
    			
    			prepStat.executeUpdate();
    			
    			preparedStatement.close();
    			
    			
    		}catch (Exception e) 
    		{
				e.printStackTrace();
    		}
    	}
    		refreshTabTroubles();
    }
    
    @FXML
    private void handleEditTrouble() 
    {
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
        	String idPage = loadedPatientID;
        	System.out.println("ID du patient charge: "+idPage);
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
	    		
	    		stage.setTitle("Ajout d'un medicament");
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
    private void handleEditMedic() 
    {
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
    private void refreshInfoPatient()
    {	
    	
    	Patient tempPatient = new Patient();
    	String idPage = loadedPatientID;
    	int idPatient = Integer.parseInt(idPage);
    	System.out.println("ID du patient charge: "+idPage);
    
    	
    String nom = nomLabel2.getText();
    String prenom = prenomLabel2.getText();
    String groupSang = grpSanLabel2.getText();
    String adresse = adresseLabel2.getText();
    String CP = codePosLabel2.getText();
    String ville = villeLabel2.getText();
    String tel = phoneLabel2.getText();
    String birthday = birthdayLabel2.getText();
    String pays = paysLabel2.getText();
    String genre = sexeLabel2.getText();
    String ICENom = iceNomLabel2.getText();
    String ICETel = iceTelephoneLabel2.getText();
    String email = emailLabel2.getText();
    
    if(nom.length()==0||prenom.length()==0||groupSang.length()==0||adresse.length()==0||CP.length()==0||ville.length()==0||tel.length()==0||birthday.length()==0||pays.length()==0||genre.length()==0||ICENom.length()==0||ICETel.length()==0||email.length()==0) 
    {
    		Alert alertas = new Alert(AlertType.ERROR);
    		alertas.setTitle("Erreur");
		alertas.setHeaderText("Erreur");
		alertas.setContentText("Tous les champs n'ont pas ete remplis correctement.");
		alertas.showAndWait();
    }
    else 
    {
    		String sql = "SELECT * FROM Patient WHERE IDPatient = ?";	
    		
    		try 
    		{
    			Class.forName("com.mysql.jdbc.Driver");
    			String url = "jdbc:mysql://localhost:3306/passbiomed_v3";
    			String user = "root";
    			String password = "Secret123";
    			
    			Connection connect = (Connection) DriverManager.getConnection(url, user, password);
    			ResultSet resultSet = null;
    			
    			PreparedStatement preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    			preparedStatement.setInt(1, idPatient);

    			resultSet = preparedStatement.executeQuery();
    			
    			if(resultSet.next())
    			{	
    				tempPatient.setIDPatient(resultSet.getInt("IDPatient"));
    				tempPatient.setIdPassBioMed(resultSet.getInt("IDPasseport_biomed"));
    				tempPatient.setNom(resultSet.getString("Nom"));
    				tempPatient.setPrenom(resultSet.getString("Prenom"));
    				tempPatient.setTelephone(resultSet.getString("Telephone"));
    				tempPatient.setAdresse(resultSet.getString("Adresse"));
    				tempPatient.setiCE_nom(resultSet.getString("ICE_nom"));
    				tempPatient.setiCE_telephone(resultSet.getString("ICE_telephone"));
    				tempPatient.setGrpSanguin(resultSet.getString("Groupe_sanguin"));
    				tempPatient.setSexe(resultSet.getString("Sexe"));
    				tempPatient.setDateNaissance(resultSet.getString("Date_naissance"));
    				tempPatient.setCodePostal(resultSet.getString("Code_postal"));
    				tempPatient.setVille(resultSet.getString("Localite"));
    				tempPatient.setPays(resultSet.getString("Pays"));
    				tempPatient.setFlagConnexion(resultSet.getInt("Flag_connexion"));
    				tempPatient.seteMail(resultSet.getString("Email"));
    				tempPatient.setUrlImage(resultSet.getString("ImageURL"));
    				tempPatient.setIdPassBioMed(resultSet.getInt("IDPasseport_biomed"));
    				tempPatient.setIdLogin(resultSet.getInt("IDLogin"));
    				
    				System.out.println("ID Patient: "+tempPatient.getIDPatient());
    				System.out.println("ID PassBiomed: "+tempPatient.getIdPassBioMed());
    				System.out.println("ID Login: "+tempPatient.getIdLogin());
    				System.out.println("Nom: "+tempPatient.getNom());
    				System.out.println("Prenom: "+tempPatient.getPrenom());
    				System.out.println("Mail: "+tempPatient.geteMail());
    				
    				resultSet.close();
    				preparedStatement.close();
    				    					
    			}
			
    				String sqlU = "UPDATE Patient SET Nom = ?, Prenom = ?, Telephone = ?, Adresse = ?, ICE_nom = ?, ICE_telephone = ?, Groupe_sanguin = ?, Sexe = ?, Date_naissance = ?, Code_postal= ?, Localite = ?, Pays = ?, Email = ? WHERE IDPatient = ?";
    				PreparedStatement preparedStatement2 = (PreparedStatement) connect.prepareStatement(sqlU);
    				preparedStatement2.setString(1, nom);
    				preparedStatement2.setString(2, prenom);
    				preparedStatement2.setString(3, tel);
    				preparedStatement2.setString(4, adresse);
    				preparedStatement2.setString(5, ICENom);
    				preparedStatement2.setString(6, ICETel);
    				preparedStatement2.setString(7, groupSang);
    				preparedStatement2.setString(8, genre);
    				preparedStatement2.setString(9, birthday);
    				preparedStatement2.setString(10, CP);
    				preparedStatement2.setString(11, ville);
    				preparedStatement2.setString(12, pays);
    				preparedStatement2.setString(13, email);
    				preparedStatement2.setInt(14, idPatient);
    			
    				preparedStatement2.executeUpdate();
    				System.out.println("Patient modifié.");
    				
    				Alert alertos = new Alert(AlertType.INFORMATION);
            		alertos.setTitle("Succes");
            		alertos.setHeaderText("Succes");
            		alertos.setContentText("Le patient a ete modifie correctement.");
            		alertos.showAndWait(); 	
    			
    			
    		}
    		catch (Exception e) 
    		{
			e.printStackTrace();
    		}   	
    }
        		
   }
}   

