package passbiomed.model;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Trouble {
	private final StringProperty nomUniversel;
	private final StringProperty nomCommun;
	private final StringProperty sousType;
	private final StringProperty masterType;
	
	public Trouble() {
		this(null,null,null,null);
	}
	
	public Trouble(String nomUniversel, String nomCommun, String sousType, String masterType) {
		this.nomUniversel = new SimpleStringProperty(nomUniversel);
	    this.nomCommun = new SimpleStringProperty(nomCommun);
	    this.sousType = new SimpleStringProperty(sousType);
	    this.masterType = new SimpleStringProperty(masterType);
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
	
	
    public String getNomCommun() {
        return this.nomCommunProperty().get();
    }

    public void setNomCommun(String nomCommun) {
        this.nomCommun.set(nomCommun);
    }

    public StringProperty nomCommunProperty() {
        return nomCommun;
    }
    
    
    public String getSousType() {
        return this.sousTypeProperty().get();
    }

    public void setSousType(String nomSousType) {
        this.sousType.set(nomSousType);
    }

    public StringProperty sousTypeProperty() {
        return sousType;
    }
    
    
    public String getMasterType() {
        return this.masterTypeProperty().get();
    }

    public void setMasterType(String nomMasterType) {
        this.masterType.set(nomMasterType);
    }

    public StringProperty masterTypeProperty() {
        return masterType;
    }
}
