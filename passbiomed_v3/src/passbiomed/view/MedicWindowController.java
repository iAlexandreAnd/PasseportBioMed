package passbiomed.view;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MedicWindowController 
{
    @FXML
    private GridPane borderGrid;

    @FXML
    private AnchorPane AnchorWindow;

    @FXML
    private TextField rechercheMedic;

    @FXML
    private JFXComboBox<?> comboBoxTypeMedic;

    @FXML
    private JFXButton boutonRechercheMedic;

    @FXML
    private Pane paneHeaderMedic;

    @FXML
    private TableView<?> tableauMedic;

    @FXML
    private JFXButton addToPatient;
    
    
	
}
