package com.financetracker.service.impl;

import com.financetracker.dto.BudgetDTO;
import com.financetracker.mapper.BudgetMapper;
import com.financetracker.model.Budget;
import com.financetracker.model.User;
import com.financetracker.repository.BudgetRepository;
import com.financetracker.repository.UserRepository;
import com.financetracker.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetRepository budgetRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private BudgetMapper budgetMapper;

    @Override
    public BudgetDTO createBudget(BudgetDTO dto, Long userId) {
        Budget budget = budgetMapper.toEntity(dto);
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        budget.setUser(user);
        return budgetMapper.toDto(budgetRepo.save(budget));
    }

    @Override
    public List<BudgetDTO> getBudgets(Long userId) {
        return budgetRepo.findByUserId(userId).stream()
                .map(budgetMapper::toDto)
                .toList();
    }

    @Override
    public BudgetDTO updateBudget(Long id, BudgetDTO dto, Long userId) {
        Budget budget = budgetRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found"));
        if (!budget.getUser().getId().equals(userId))
            throw new RuntimeException("Unauthorized");

        budget.setCategory(dto.getCategory());
        budget.setAmount(dto.getAmount());
        budget.setStartDate(dto.getStartDate());
        budget.setEndDate(dto.getEndDate());

        return budgetMapper.toDto(budgetRepo.save(budget));
    }

    @Override
    public void deleteBudget(Long id, Long userId) {
        Budget budget = budgetRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found"));
        if (!budget.getUser().getId().equals(userId))
            throw new RuntimeException("Unauthorized");

        budgetRepo.delete(budget);
    }
}

