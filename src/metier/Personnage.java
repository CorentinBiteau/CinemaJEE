package metier;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the personnage database table.
 * 
 */
@Entity
@NamedQuery(name="Personnage.findAll", query="SELECT p FROM Personnage p")
public class Personnage implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonnagePK id;

	private String nomPers;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="NoFilm")
	private Film film;

	//bi-directional many-to-one association to Acteur
	@ManyToOne
	@JoinColumn(name="NoAct")
	private Acteur acteur;

	public Personnage() {
	}

	public PersonnagePK getId() {
		return this.id;
	}

	public void setId(PersonnagePK id) {
		this.id = id;
	}

	public String getNomPers() {
		return this.nomPers;
	}

	public void setNomPers(String nomPers) {
		this.nomPers = nomPers;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Acteur getActeur() {
		return this.acteur;
	}

	public void setActeur(Acteur acteur) {
		this.acteur = acteur;
	}

}