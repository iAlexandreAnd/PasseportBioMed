package passbiomed.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Medicament {
	private final StringProperty nomUniversel;
	private final StringProperty nomCommercial;
	private final StringProperty nomChimique;
	
	
	
	public Medicament() {
		this(null, null, null);
	}
	
	public Medicament(String nomUniversel, String nomCommercial, String nomChimique) {
		this.nomUniversel = new SimpleStringProperty(nomUniversel);
        this.nomCommercial = new SimpleStringProperty(nomCommercial);
        this.nomChimique = new SimpleStringProperty(nomChimique);
	}
	
	public String getNomUniversel() {
        return this.nomUniverselProperty().get();
    }

    public void setNomUniversel(String nomUniversel) {
        this.nomUniversel.set(nomUniversel);
    }

    public StringProperty nomUniverselProperty() {
        return nomUniversel;
    }
    
    public String getNomCommercial() {
        return this.nomCommercialProperty().get();
    }

    public void setNomCommercial(String nomCommercial) {
        this.nomCommercial.set(nomCommercial);
    }

    public StringProperty nomCommercialProperty() {
        return nomCommercial;
    }
    
    public String getNomChimique() {
        return this.nomChimiqueProperty().get();
    }

    public void setNomChimique(String nomChimique) {
        this.nomChimique.set(nomChimique);
    }

    public StringProperty nomChimiqueProperty() {
        return nomChimique;
    }
}
