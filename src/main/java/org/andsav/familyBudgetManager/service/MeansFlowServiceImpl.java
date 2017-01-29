package org.andsav.familyBudgetManager.service;

import java.time.LocalDateTime;
import java.util.List;

import org.andsav.familyBudgetManager.model.MeansFlow;
import org.andsav.familyBudgetManager.repository.MeansFlowRepository;
import org.andsav.familyBudgetManager.util.exception.ExceptionUtil;
import org.andsav.familyBudgetManager.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class MeansFlowServiceImpl implements MeansFlowService {
  
  @Autowired
  private MeansFlowRepository repository;
  
  @Override
  public MeansFlow save(MeansFlow meansFlow) {
    Assert.notNull(meansFlow, "meansFlow must not be null");
    return repository.save(meansFlow);
  }

  @Override
  public void delete(int id) throws NotFoundException {
    ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
  }

  @Override
  public void update(MeansFlow meansFlow) throws NotFoundException {
    Assert.notNull(meansFlow, "meansFlow must not be null"); 
    repository.save(meansFlow);
  }

  @Override
  public List<MeansFlow> getbyBudgetId(Integer budgetId) throws NotFoundException {
    return repository.getByBudgetId(budgetId);
  }

  @Override
  public List<MeansFlow> getBetweenDateByBudgetId(Integer budgetId, LocalDateTime startDate, LocalDateTime endDate) {
    return repository.getByBudgetIdBetweenDates(budgetId, startDate, endDate);
  }

  @Override
  public MeansFlow get(int meansFlowId) throws NotFoundException {
    return repository.get(meansFlowId);
  }

}
