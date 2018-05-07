package passbiomed.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Operation {
	private final StringProperty nomOperation;
	private final StringProperty commentaire;
	
	
	public Operation() {
		this(null, null);
	}

	public Operation(String nomOperation, String commentaire) {
		this.nomOperation = new SimpleStringProperty(nomOperation);
	    this.commentaire = new SimpleStringProperty(commentaire);
	}

	
	public String getNomOperation() {
        return this.nomOperationProperty().get();
    }

    public void setNomOperation(String nomOperation) {
        this.nomOperation.set(nomOperation);
    }

    public StringProperty nomOperationProperty() {
        return nomOperation;
    }
    
    
    public String getCommentaire() {
        return this.commentaireProperty().get();
    }

    public void setCommentaire(String commentaire) {
        this.commentaire.set(commentaire);
    }

    public StringProperty commentaireProperty() {
        return commentaire;
    }
}