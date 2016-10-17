package metier;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the film database table.
 * 
 */
@Entity
@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int noFilm;

	private int budget;

	@Temporal(TemporalType.DATE)
	private Date dateSortie;

	private int duree;

	private int montantRecette;

	private String titre;

	//bi-directional many-to-one association to Realisateur
	@ManyToOne
	@JoinColumn(name="NoRea")
	private Realisateur realisateur;

	//bi-directional many-to-one association to Categorie
	@ManyToOne
	@JoinColumn(name="CodeCat")
	private Categorie categorie;

	//bi-directional many-to-one association to Personnage
	@OneToMany(mappedBy="film")
	private List<Personnage> personnages;

	public Film() {
	}

	public int getNoFilm() {
		return this.noFilm;
	}

	public void setNoFilm(int noFilm) {
		this.noFilm = noFilm;
	}

	public int getBudget() {
		return this.budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public Date getDateSortie() {
		return this.dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public int getDuree() {
		return this.duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getMontantRecette() {
		return this.montantRecette;
	}

	public void setMontantRecette(int montantRecette) {
		this.montantRecette = montantRecette;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Realisateur getRealisateur() {
		return this.realisateur;
	}

	public void setRealisateur(Realisateur realisateur) {
		this.realisateur = realisateur;
	}

	public Categorie getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Personnage> getPersonnages() {
		return this.personnages;
	}

	public void setPersonnages(List<Personnage> personnages) {
		this.personnages = personnages;
	}

	public Personnage addPersonnage(Personnage personnage) {
		getPersonnages().add(personnage);
		personnage.setFilm(this);

		return personnage;
	}

	public Personnage removePersonnage(Personnage personnage) {
		getPersonnages().remove(personnage);
		personnage.setFilm(null);

		return personnage;
	}

}