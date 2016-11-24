package exclusions;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import metier.*;

/**
 * Created by Vlad on 24/11/2016.
 */
public class CategorieExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> f) {
        return
                (f.getDeclaringClass() == Categorie.class && f.getName().equals("films"));

    }
}
