package passbiomed.model;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;

public class Trouble
{
	
	private final StringProperty nomUniversel;
	private final StringProperty nomCommun;
	private final StringProperty sousType;
	private final StringProperty masterType;
	private final StringProperty dateConsigner;
	private final IntegerProperty iDConsigner;
	private CheckBox important;
	private CheckBox actif;
	private int modifier;

	
	public Trouble() 
	{
		this(null,null,null,null,null, 0);
	}
	

	public Trouble(String nomUniversel, String nomCommun, String sousType, String masterType, String dateConsigner, int iDConsigner) 
	{
		this.nomUniversel = new SimpleStringProperty(nomUniversel);
	    this.nomCommun = new SimpleStringProperty(nomCommun);
	    this.sousType = new SimpleStringProperty(sousType);
	    this.masterType = new SimpleStringProperty(masterType);
	    this.dateConsigner = new SimpleStringProperty(dateConsigner);
	    this.iDConsigner = new SimpleIntegerProperty(iDConsigner);
	    this.important = new CheckBox();
	    this.actif = new CheckBox();
	}
	
	public String getNomUniversel() 
	{
        return this.nomUniverselProperty().get();
    }

    public void setNomUniversel(String nomUniversel) 
    {
        this.nomUniversel.set(nomUniversel);
    }

    public StringProperty nomUniverselProperty() 
    {
        return nomUniversel;
    }
	
	
    public String getNomCommun() 
    {
        return this.nomCommunProperty().get();
    }

    public void setNomCommun(String nomCommun) 
    {
        this.nomCommun.set(nomCommun);
    }

    public StringProperty nomCommunProperty() {
    	
        return nomCommun;
    }
    
    
    public String getSousType() 
    {
        return this.sousTypeProperty().get();
    }

    public void setSousType(String nomSousType) 
    {
        this.sousType.set(nomSousType);
    }

    public StringProperty sousTypeProperty() 
    {
        return sousType;
    }
        
    public String getMasterType() 
    {
        return this.masterTypeProperty().get();
    }

    public void setMasterType(String nomMasterType) 
    {
        this.masterType.set(nomMasterType);
    }

    public StringProperty masterTypeProperty() 
    {
        return masterType;
    }
    
    public String getDateConsigner() 
    {
        return this.dateConsignerProperty().get();
    }

    public void setDateConsigner(String dateConsigner) 
    {
        this.dateConsigner.set(dateConsigner);
    }

    public StringProperty dateConsignerProperty() 
    {
        return dateConsigner;
    }

	public CheckBox getImportant() 
	{
		return important;
	}

	public void setImportant(CheckBox important) 
	{
		this.important = important;
	}


	public CheckBox getActif() 
	{
		return actif;
	}


	public void setActif(CheckBox actif) 
	{
		this.actif = actif;
	}


	 public IntegerProperty iDConsignerProperty() 
	 {
	    return iDConsigner;
	 }
	
	 public int getiDConsigner() 
	 {
        return this.iDConsignerProperty().get();
     }

    public void setiDConsigner(int iDConsigner) 
    {
        this.iDConsigner.set(iDConsigner);
    }

	public int getModifier() 
	{
		return modifier;
	}
	
	public void setModifier(int modifier) 
	{
		this.modifier = modifier;
	}

}
