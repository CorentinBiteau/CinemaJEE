package exclusions;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import metier.Acteur;
import metier.Film;

public class FilmExclusionStrategy implements ExclusionStrategy{

	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		return true;
//				(f.getDeclaringClass() == Film.class && f.getName().equals("personnages"));
	}

}
