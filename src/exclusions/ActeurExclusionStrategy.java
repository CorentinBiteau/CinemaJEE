package exclusions;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import metier.Categorie;
import metier.Film;
import metier.Personnage;

public class ActeurExclusionStrategy implements ExclusionStrategy {

	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		return 
				(f.getDeclaringClass() == Personnage.class && f.getName().equals("acteur"))
				|| (f.getDeclaringClass() == Film.class && (f.getName().equals("categorie") || f.getName().equals("personnages")))
				|| (f.getDeclaringClass() == Categorie.class && (f.getName().equals("films")))
				|| (f.getDeclaringClass() == Acteur.class && (f.getName().equals("personnages")))
				;
	}

}
