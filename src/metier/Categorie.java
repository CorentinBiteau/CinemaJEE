package metier;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categorie database table.
 * 
 */
@Entity
@NamedQuery(name="Categorie.findAll", query="SELECT c FROM Categorie c")
public class Categorie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codeCat;

	private String libelleCat;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="categorie")
	private List<Film> films;

	public Categorie() {
	}

	public String getCodeCat() {
		return this.codeCat;
	}

	public void setCodeCat(String codeCat) {
		this.codeCat = codeCat;
	}

	public String getLibelleCat() {
		return this.libelleCat;
	}

	public void setLibelleCat(String libelleCat) {
		this.libelleCat = libelleCat;
	}

	public List<Film> getFilms() {
		return this.films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public Film addFilm(Film film) {
		getFilms().add(film);
		film.setCategorie(this);

		return film;
	}

	public Film removeFilm(Film film) {
		getFilms().remove(film);
		film.setCategorie(null);

		return film;
	}

}