package passbiomed.model;


public class Patient {
	
	private String nom;
	private String prenom;
	private String adresse;
	private String codePostal;
	private String ville;
	private String telephone;
	private String grpSanguin;
	private String sexe;
	private String pays;
	private String iCE_nom;
	private String iCE_telephone;
	private String dateNaissance;
	private int flagValidite;
	private int idPassBioMed;
	private int idLogin;
	private int flagConnexion;
	
	
	
	public int getFlagValidite() {
		return flagValidite;
	}
	public void setFlagValidite(int flagValidite) {
		this.flagValidite = flagValidite;
	}
	public int getIdPassBioMed() {
		return idPassBioMed;
	}
	public void setIdPassBioMed(int idPassBioMed) {
		this.idPassBioMed = idPassBioMed;
	}
	public int getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(int idLogin) {
		this.idLogin = idLogin;
	}
	public int getFlagConnexion() {
		return flagConnexion;
	}
	public void setFlagConnexion(int flagConnexion) {
		this.flagConnexion = flagConnexion;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getGrpSanguin() {
		return grpSanguin;
	}
	public void setGrpSanguin(String grpSanguin) {
		this.grpSanguin = grpSanguin;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getiCE_nom() {
		return iCE_nom;
	}
	public void setiCE_nom(String iCE_nom) {
		this.iCE_nom = iCE_nom;
	}
	public String getiCE_telephone() {
		return iCE_telephone;
	}
	public void setiCE_telephone(String iCE_telephone) {
		this.iCE_telephone = iCE_telephone;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

}