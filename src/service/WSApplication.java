package service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// addresse de basse pour acc�der au WS
@ApplicationPath("/WS")
public class WSApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	
	public WSApplication() {
		singletons.add(new ActeursWService());
		singletons.add(new FilmsWService());
		singletons.add(new CategorieWService());
		singletons.add(new PersonnageWService());
		singletons.add(new RealisateurWService());
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		return empty;
	}
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
