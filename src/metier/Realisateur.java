package metier;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the realisateur database table.
 * 
 */
@Entity
@NamedQuery(name="Realisateur.findAll", query="SELECT r FROM Realisateur r")
public class Realisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int noRea;

	private String nomRea;

	private String prenRea;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="realisateur")
	private List<Film> films;

	public Realisateur() {
	}

	public int getNoRea() {
		return this.noRea;
	}

	public void setNoRea(int noRea) {
		this.noRea = noRea;
	}

	public String getNomRea() {
		return this.nomRea;
	}

	public void setNomRea(String nomRea) {
		this.nomRea = nomRea;
	}

	public String getPrenRea() {
		return this.prenRea;
	}

	public void setPrenRea(String prenRea) {
		this.prenRea = prenRea;
	}

	public List<Film> getFilms() {
		return this.films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public Film addFilm(Film film) {
		getFilms().add(film);
		film.setRealisateur(this);

		return film;
	}

	public Film removeFilm(Film film) {
		getFilms().remove(film);
		film.setRealisateur(null);

		return film;
	}

}