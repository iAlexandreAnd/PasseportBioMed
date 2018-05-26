package passbiomed.view;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CreatePatientController 
{

    @FXML
    private Pane bannierePane;
    
    @FXML
    private JFXButton previousButton;

    @FXML
    private JFXButton NextButton;

    @FXML
    private JFXButton BackToMainButton;

    @FXML
    private GridPane GridPaneInfo1;

    @FXML
    private JFXTextField TextFieldNom;

    @FXML
    private GridPane GridPaneInfo2;

    @FXML
    private JFXDatePicker DatePickerBirthday;
    
    @FXML
    private JFXTextField TextFieldPrenom;

    @FXML
    private JFXTextField TextFieldTel;

    @FXML
    private JFXTextField TextFieldAdresse;

    @FXML
    private JFXComboBox<String> ComboBoxPays;

    @FXML
    private JFXTextField TextFieldLocalite;

    @FXML
    private JFXTextField TextFieldCP;

    @FXML
    private JFXTextField TextFieldICE_Nom;

    @FXML
    private JFXTextField TextFieldICE_Tel;

    @FXML
    private JFXComboBox<String> ComboBox_GS;

    @FXML
    private JFXRadioButton RadioButtonMale;

    @FXML
    private ToggleGroup GenderGroup;

    @FXML
    private JFXRadioButton RadioButtonFemale;
    
    @FXML
    private GridPane GridPaneInfo3;

    @FXML
    private ImageView PicturePatient;

    @FXML
    private JFXButton Chercher_photo;
    
    @FXML
    private JFXButton NextButtonWin3;
    
    @FXML
    private JFXButton previousButtonToWin2;
    
    @FXML
    private GridPane GridPaneInfo4;

    @FXML
    private JFXTextField mailAddress;

    @FXML
    private JFXTextField logginName;

    @FXML
    private JFXTextField PasswordName;

    @FXML
    private JFXButton CreerUtilisateur;
    

    @FXML
    private JFXButton previousButtonToWin3;

    @FXML
    private JFXButton NextButtonWin4;
    
    @FXML
    private StackPane contentPane;
    
    @FXML
    private JFXTextField numeroRegistre;
    
    
    ObservableList<String> listePays = FXCollections.observableArrayList("Belgique", "Angleterre", "Canada", "Japon");
    ObservableList<String> groupeSanguin = FXCollections.observableArrayList("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-");
    
    @FXML
    private void initialize() 
    {
    		GridPaneInfo2.setVisible(false);
    		GridPaneInfo3.setVisible(false);
    		GridPaneInfo4.setVisible(false);
    		ComboBoxPays.setItems(listePays);
    		ComboBox_GS.setItems(groupeSanguin);
    		
    		LocalDate localDate = LocalDate.now();
    		
    		DatePickerBirthday.setValue(localDate);
    	    		
//    		previousButton.setDisable(true);
    		previousButton.setVisible(false);
    		previousButtonToWin2.setVisible(false);
    		previousButtonToWin3.setVisible(false);
    		NextButtonWin3.setVisible(false);
    		NextButtonWin4.setVisible(false);
    		
    }
    
    @FXML
    private void buttonActionNext() 
    {
    		String nom = TextFieldNom.getText();
    		String prenom = TextFieldPrenom.getText();
    		String tel = TextFieldTel.getText();
    		String adresse = TextFieldAdresse.getText();
    		String pays = ComboBoxPays.getValue();
    		String localite = TextFieldLocalite.getText();
    		
    		if(nom.length()== 0||prenom.length()== 0||tel.length()== 0||adresse.length()== 0||pays==null||localite.length()== 0) 
    		{
    			Alert alerto = new Alert(AlertType.ERROR);
        		alerto.setTitle("Erreur");
        		alerto.setHeaderText("Erreur");
        		alerto.setContentText("Tous les champs n'ont pas ete remplis correctement.");
        		alerto.showAndWait();   			
    		}
    		
    		else 
    		{
    			GridPaneInfo1.setVisible(false);
        		GridPaneInfo2.setVisible(true);
        		previousButton.setVisible(true);
        		NextButton.setVisible(false);
        		NextButtonWin3.setVisible(true);
        		
    		}
    		
    		System.out.println("Nom: "+nom);
    		System.out.println("Prenom: "+prenom);
    		System.out.println("Tel: "+tel);
    		System.out.println("Adresse: "+adresse);
    		System.out.println("Pays: "+pays);
    		System.out.println("Localite: "+localite);
    		
    		
    	
    }
    
    @FXML
    private void buttonActionPrevious() 
    {
    		GridPaneInfo1.setVisible(true);
    		GridPaneInfo2.setVisible(false);
    		previousButton.setVisible(false);
    		NextButton.setVisible(true);
    		NextButtonWin3.setVisible(false);
    } 
   
    
    @FXML
    private void buttonActionPreviousWin2() 
    {
    	GridPaneInfo2.setVisible(true);
		GridPaneInfo3.setVisible(false);
		NextButtonWin3.setVisible(true);
		NextButtonWin4.setVisible(false);
		previousButtonToWin2.setVisible(false);
		previousButton.setVisible(true);
    	
    }
    
    @FXML
    private void buttonActionPreviousWin3() 
    {
    		GridPaneInfo3.setVisible(true);
		GridPaneInfo4.setVisible(false);
		NextButtonWin4.setDisable(false);
		previousButtonToWin3.setVisible(false);
		previousButtonToWin2.setVisible(true);
 	
    }

    @FXML
    private void buttonActionToWin3() 
    {
    		
    		String CodePostal = TextFieldCP.getText();
    		String ICE_Nom = TextFieldICE_Nom.getText();
    		String ICE_Tel = TextFieldICE_Tel.getText();
    		String groupe_sanguin = ComboBox_GS.getValue();
    		String gender = null;
    		
    		if(RadioButtonFemale.isSelected()) 
    		{
    			gender = "Feminin";
    		}
    		else if(RadioButtonMale.isSelected())
    		{
    			 gender = "Masculin";
    		}
    		
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    		String dateVari = (DatePickerBirthday.getValue().format(formatter));
    		
    		LocalDate localDat = LocalDate.now();
    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    		String formattedDate = localDat.format(dtf);
    		
    		if(CodePostal.length()==0||ICE_Nom.length()==0||ICE_Tel.length()==0||groupe_sanguin.length()==0||gender.length()==0||dateVari.equals(formattedDate)) 
    		{
    			Alert alertus = new Alert(AlertType.ERROR);
        		alertus.setTitle("Erreur");
        		alertus.setHeaderText("Erreur");
        		alertus.setContentText("Tous les champs n'ont pas ete remplis correctement.");
        		alertus.showAndWait(); 
        		
    		}
    		else 
    		{
    			GridPaneInfo1.setVisible(false);
        		GridPaneInfo2.setVisible(false);
        		GridPaneInfo3.setVisible(true);
        		previousButton.setVisible(false);
        		previousButtonToWin2.setVisible(true);
        		NextButton.setVisible(false);
        		NextButtonWin3.setVisible(false);
        		NextButtonWin4.setVisible(true);
        		
    		}
    		
    		
    		System.out.println("Code postal: "+CodePostal);
    		System.out.println("ICE nom: "+ICE_Nom);
    		System.out.println("ICE tel: "+ICE_Tel);
    		System.out.println("Groupe sanguin: "+groupe_sanguin);
    		System.out.println("Genre: "+gender);
    		System.out.println("Date de naissance: "+dateVari);
    
    		
    } 
    
    @FXML 
    private void buttonActionToWin4()
    {
    		GridPaneInfo3.setVisible(false);
    		GridPaneInfo4.setVisible(true);
    		previousButtonToWin2.setVisible(false);
    		previousButtonToWin3.setVisible(true);
    		NextButtonWin4.setDisable(true);  		 		
    }
    
    @FXML
    private void handleRetour() 
    {
    	Stage stage = (Stage) BackToMainButton.getScene().getWindow();
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
    
   void addImage(Image i, StackPane pane)
    {
	   	int widthImg = 170;
       	int heightImg = 180;
	   
        PicturePatient = new ImageView();
        PicturePatient.setImage(i);
        PicturePatient.setFitWidth(widthImg);
        PicturePatient.setFitHeight(heightImg);
        pane.getChildren().add(PicturePatient);
    }
   
  @FXML
  private void mouseDragDropped(final DragEvent e) 
  {
        final Dragboard db = e.getDragboard();
        boolean success = false;
        if (db.hasFiles()) 
        {
            success = true;
            // Only get the first file from the list
            final File file = db.getFiles().get(0);
            Platform.runLater(new Runnable() 
            {
                @Override
                public void run() 
                {
                    System.out.println(file.getAbsolutePath());
                    try {
                        if(!contentPane.getChildren().isEmpty())
                        {
                            contentPane.getChildren().remove(0);
                        }
                        Image img = new Image(new FileInputStream(file.getAbsolutePath())); 
                        
                        addImage(img, contentPane);
                    } catch (FileNotFoundException ex) 
                    {
                        Logger.getLogger(CreatePatientController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
        e.setDropCompleted(success);
        e.consume();
    }
 

	@FXML
    private  void mouseDragOver(final DragEvent e) 
    {
        final Dragboard db = e.getDragboard();
 
        final boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".png")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpeg")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpg");
 
        if (db.hasFiles()) 
        {
            if (isAccepted) 
            {
                contentPane.setStyle("-fx-border-color: red;"
              + "-fx-border-width: 5;"
              + "-fx-background-color: #C6C6C6;"
              + "-fx-border-style: solid;");
                e.acceptTransferModes(TransferMode.COPY);
            }
        } 
        else 
        {
            e.consume();
        }
    }
  	
    @FXML
    void paneIsExited(DragEvent event) 
    {
    		contentPane.setStyle("-fx-border-color: #C6C6C6;");
    }
    
    private int recupIdLogin(String loginName) 
    {
    		String sql = "SELECT * FROM Login WHERE Login_nom = ? ";
    		int idLogin = 0;
    		try 
    		{
    			Class.forName("com.mysql.jdbc.Driver");
    			String url = "jdbc:mysql://localhost:3306/passbiomed_v3";
    			String user = "root";
    			String password = "Secret123";
    			
    			Connection connect = (Connection) DriverManager.getConnection(url, user, password);
    			ResultSet resultSet = null;
    			
    			PreparedStatement preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    			preparedStatement.setString(1, loginName);

    			resultSet = preparedStatement.executeQuery();
    			
    			if(resultSet.next())
    			{
    				idLogin = resultSet.getInt("IDLogin");
    				System.out.println("Id Login r�cup�r�.");	
    			}
    			
    			
    		}catch(Exception e) 
    		{
    			e.printStackTrace();
    		}
  
    		return idLogin;
    }
    
    private int recupIdPassBiomed(String registreNational) 
    {
    		String sql = "SELECT * FROM Passeport_biomed WHERE NumeroRegistreNational = ? ";
    		int idPassBiomed = 0;
    		try 
    		{
    			Class.forName("com.mysql.jdbc.Driver");
    			String url = "jdbc:mysql://localhost:3306/passbiomed_v3";
    			String user = "root";
    			String password = "Secret123";
    			
    			Connection connect = (Connection) DriverManager.getConnection(url, user, password);
    			ResultSet resultSet = null;
    			
    			PreparedStatement preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    			preparedStatement.setString(1, registreNational);

    			resultSet = preparedStatement.executeQuery();
    			
    			if(resultSet.next())
    			{
    				idPassBiomed = resultSet.getInt("IDPasseport_biomed");
    				System.out.println("ID passport biom�dical r�cup�r�.");	
    			}
    			
    			
    		}catch(Exception e) 
    		{
    			e.printStackTrace();
    		}
  
    		return idPassBiomed;
    }
    @FXML
    private void createUser()
    {
    		String nom = TextFieldNom.getText();
		String prenom = TextFieldPrenom.getText();
		String tel = TextFieldTel.getText();
		String adresse = TextFieldAdresse.getText();
		String pays = ComboBoxPays.getValue();
		String localite = TextFieldLocalite.getText();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		String dateVari = (DatePickerBirthday.getValue().format(formatter));
		
		String CodePostal = TextFieldCP.getText();
		String ICE_Nom = TextFieldICE_Nom.getText();
		String ICE_Tel = TextFieldICE_Tel.getText();
		String groupe_sanguin = ComboBox_GS.getValue();
		String gender = null;
		
		if(RadioButtonFemale.isSelected()) 
		{
			gender = "F�minin";
		}
		else if(RadioButtonMale.isSelected())
		{
			 gender = "Masculin";
		}
    		String adresseMail = mailAddress.getText();
    		String identifiant = logginName.getText();
    		String mdp = PasswordName.getText();
    		String RN = numeroRegistre.getText();
    		int flagConnexion = 2;
    		int IDlog = 0;
    		int IdPassbiomed = 0;
    		
    		System.out.println("Adresse mail: "+adresseMail);
    		System.out.println("Identifiant: "+identifiant);
    		System.out.println("Mot de passe: "+mdp);
    		
    		
    		if(adresseMail.length()==0 || identifiant.length()==0 || mdp.length()==0) 
    		{
    			Alert alertas = new Alert(AlertType.ERROR);
        		alertas.setTitle("Erreur");
        		alertas.setHeaderText("Erreur");
        		alertas.setContentText("Tous les champs n'ont pas �t� remplis correctement.");
        		alertas.showAndWait(); 
    		}
    		else 
    		{
    			String sqlLogin = "INSERT INTO Login (Login_nom, Login_password) VALUES (?,?)";
    			String sqlPassport = "INSERT INTO Passeport_biomed (NumeroRegistreNational) VALUES (?)";
    			String sqlPatient = "INSERT INTO Patient (Nom, Prenom, Telephone, Adresse, ICE_nom, ICE_telephone, Groupe_sanguin, Sexe, Date_naissance, Code_postal, Localite, Pays, Flag_connexion, Email, IDPasseport_biomed, IDLogin) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    			try 
    			{
    				Class.forName("com.mysql.jdbc.Driver");
    				System.out.println("Driver OK");
    				
    				String url = "jdbc:mysql://localhost:3306/passbiomed_v3";
    				String user = "root";
    				String password = "Secret123";
    				
    				Connection connect = (Connection) DriverManager.getConnection(url, user, password);
    				PreparedStatement prepStat = (PreparedStatement) connect.prepareStatement(sqlLogin);
    				
    				// On prepare le nouveau login va etre integre dans sa table
    				System.out.println("Login va etre ajoute");
    				prepStat.setString(1, identifiant);
    				prepStat.setString(2, mdp);
    				System.out.println("Login ajoute");
    				
    				// On pr�pare le nouveau passeportBiomed � �tre int�gr� dans sa table
    				PreparedStatement prepStat2 = (PreparedStatement) connect.prepareStatement(sqlPassport);
    				System.out.println("Entite passaportBioMed va etre inseree.");
    				prepStat2.setString(1, RN);
    				System.out.println("Entite cree.");
    				
    				// On execute 
    				prepStat.executeUpdate();
    				prepStat2.executeUpdate();
    				
    				// On r�cup�re les ID des tables login et PassBiomed vu que Patient contient des FK.
    				IDlog = recupIdLogin(identifiant);
    				IdPassbiomed = recupIdPassBiomed(RN);
    				
    				// Affichiage de verification de la recup des ID.
    				System.out.println(IDlog);
    				System.out.println(IdPassbiomed);
    				
    				// Preparation des donnees.
    				PreparedStatement prepStat3 = (PreparedStatement) connect.prepareStatement(sqlPatient);
    				System.out.println("Nouveau patient en cours de finalisation.");
    				prepStat3.setString(1, nom);
    				prepStat3.setString(2, prenom);
    				prepStat3.setString(3, tel);
    				prepStat3.setString(4, adresse);
    				prepStat3.setString(5, ICE_Nom);
    				prepStat3.setString(6, ICE_Tel);
    				prepStat3.setString(7, groupe_sanguin);
    				prepStat3.setString(8, gender);
    				prepStat3.setString(9, dateVari);
    				prepStat3.setString(10, CodePostal);
    				prepStat3.setString(11, localite);
    				prepStat3.setString(12, pays);
    				prepStat3.setInt(13, flagConnexion);
    				prepStat3.setString(14, adresseMail);
    				prepStat3.setInt(15, IdPassbiomed);
    				prepStat3.setInt(16, IDlog);
    				
    				// insertion des donnees.
    				prepStat3.executeUpdate();
    				System.out.println("Patient cree.");
    				
 
    				Alert alertos = new Alert(AlertType.INFORMATION);
            		alertos.setTitle("Succes");
            		alertos.setHeaderText("Succes");
            		alertos.setContentText("La creation s'est deroulee correctement.");
            		alertos.showAndWait(); 		
    				
    			}catch (Exception e) 
    			{
    				Alert alertis = new Alert(AlertType.ERROR);
            		alertis.setTitle("Erreur");
            		alertis.setHeaderText("Erreur");
            		alertis.setContentText("Une erreur est survenue lors de l'ajout dans la base de donnees.");
            		alertis.showAndWait(); 
    				
            		e.printStackTrace();
    			}
    			
    			// retour au menu
    			handleRetour();
    		}
    	
    }
    
}