package org.andsav.familyBudgetManager.model.abstractentity;

/**
 * The {@code BaseEntity} abstract class is intended to be derived by
 * classes which objects state is to be persisted. Comprises following fields:
 * 
 * <blockquote><pre>
 * {@code Long} id
 * <pre></blockquote><p> 
 * 
 * 
 * @See {@link org.andsav.familyBudgetManager.model.abstractentity.NamedEntity}
 * @See {@link org.andsav.familyBudgetManager.model.abstractentity.DateInitUpdateEntity}
 * 
 * @author asavka
 *
 */
public abstract class BaseEntity {
  
  protected Long id;
  
  public BaseEntity() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  protected boolean isNew(){
    return id == null;
  }

  @Override
  public String toString() {
    return "id=" + id;
  }
  
}
