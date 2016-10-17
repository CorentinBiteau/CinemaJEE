package metier;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the personnage database table.
 * 
 */
@Embeddable
public class PersonnagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int noFilm;

	@Column(insertable=false, updatable=false)
	private int noAct;

	public PersonnagePK() {
	}
	public int getNoFilm() {
		return this.noFilm;
	}
	public void setNoFilm(int noFilm) {
		this.noFilm = noFilm;
	}
	public int getNoAct() {
		return this.noAct;
	}
	public void setNoAct(int noAct) {
		this.noAct = noAct;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonnagePK)) {
			return false;
		}
		PersonnagePK castOther = (PersonnagePK)other;
		return 
			(this.noFilm == castOther.noFilm)
			&& (this.noAct == castOther.noAct);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.noFilm;
		hash = hash * prime + this.noAct;
		
		return hash;
	}
}