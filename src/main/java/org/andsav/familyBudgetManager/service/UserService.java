package org.andsav.familyBudgetManager.service;

import java.util.List;

import org.andsav.familyBudgetManager.model.User;
import org.andsav.familyBudgetManager.util.exception.NotFoundException;

public interface UserService {
  
  User save(User user);
  
  User get(int id) throws NotFoundException;
  
  void delete(int id) throws NotFoundException;
  
  void update(User user) throws NotFoundException;

  User getByEmail(String email) throws NotFoundException;
  
  List<User> getAll();
  
  List<User> getbyBudgetId(Integer budgetId);
  
}
