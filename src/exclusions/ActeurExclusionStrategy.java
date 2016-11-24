package exclusions;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import metier.*;

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
					|| (f.getDeclaringClass() == Film.class && f.getName().equals("personnages"))
					|| (f.getDeclaringClass() == Realisateur.class && f.getName().equals("films"))
				|| (f.getDeclaringClass() == Categorie.class && f.getName().equals("films"));
	}

}
