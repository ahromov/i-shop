package ua.lviv.lgs.shared;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryManager {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static EntityManagerFactory getEntityManagerFactory() {
	if (emf == null)
	    emf = Persistence.createEntityManagerFactory("IShopPersistence");
	return emf;
    }

    public static EntityManager getEntityManager() {
	if (em == null)
	    em = getEntityManagerFactory().createEntityManager();
	return em;
    }

}
