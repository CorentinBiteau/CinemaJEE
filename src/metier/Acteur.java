package metier;

import java.io.Serializable;
import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the acteur database table.
 * 
 */
@Entity
@NamedQuery(name="Acteur.findAll", query="SELECT a FROM Acteur a")
public class Acteur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int noAct;

	@Temporal(TemporalType.DATE)
	private Date dateDeces;

	@Temporal(TemporalType.DATE)
	private Date dateNaiss;

	private String nomAct;

	private String prenAct;

	//bi-directional many-to-one association to Personnage
	@OneToMany(mappedBy="acteur")
	private List<Personnage> personnages;

	public Acteur() {
	}

	public int getNoAct() {
		return this.noAct;
	}

	public void setNoAct(int noAct) {
		this.noAct = noAct;
	}

	public Date getDateDeces() {
		return this.dateDeces;
	}

	public void setDateDeces(Date dateDeces) {
		this.dateDeces = dateDeces;
	}

	public Date getDateNaiss() {
		return this.dateNaiss;
	}

	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}

	public String getNomAct() {
		return this.nomAct;
	}

	public void setNomAct(String nomAct) {
		this.nomAct = nomAct;
	}

	public String getPrenAct() {
		return this.prenAct;
	}

	public void setPrenAct(String prenAct) {
		this.prenAct = prenAct;
	}

	public List<Personnage> getPersonnages() {
		return this.personnages;
	}

	public void setPersonnages(List<Personnage> personnages) {
		this.personnages = personnages;
	}

	public Personnage addPersonnage(Personnage personnage) {
		getPersonnages().add(personnage);
		personnage.setActeur(this);

		return personnage;
	}

	public Personnage removePersonnage(Personnage personnage) {
		getPersonnages().remove(personnage);
		personnage.setActeur(null);

		return personnage;
	}

}