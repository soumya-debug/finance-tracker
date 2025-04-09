package com.financetracker.service;

import com.financetracker.dto.BudgetDTO;

import java.util.List;

public interface BudgetService {
    BudgetDTO createBudget(BudgetDTO dto, Long userId);
    List<BudgetDTO> getBudgets(Long userId);
    BudgetDTO updateBudget(Long id, BudgetDTO dto, Long userId);
    void deleteBudget(Long id, Long userId);
}

