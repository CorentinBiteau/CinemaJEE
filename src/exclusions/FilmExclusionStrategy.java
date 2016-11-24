package exclusions;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import metier.*;

public class FilmExclusionStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipClass(Class<?> arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return
                (f.getDeclaringClass() == Realisateur.class && f.getName().equals("films"))
                        || (f.getDeclaringClass() == Categorie.class && f.getName().equals("films"))
                        || (f.getDeclaringClass() == Personnage.class && f.getName().equals("film"))
                        || (f.getDeclaringClass() == Acteur.class && f.getName().equals("personnages"))
                ;
    }

}
