package org.andsav.family_budget_manager;

/**
 * 
 * @author Andrii_Savka
 *
 */
public interface Profiles {
  public static final String 
      POSTGRES = "postgres", 
      HSQLDB = "hsqldb",
      JDBC = "jdbc",
      JPA = "jpa",
      DATAJPA = "datajpa",
      HEROKU = "heroku";

  public static final String ACTIVE_DB = POSTGRES;
  public static final String DB_IMPLEMENTATION = DATAJPA;
}
