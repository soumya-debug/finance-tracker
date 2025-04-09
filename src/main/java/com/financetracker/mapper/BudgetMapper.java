package com.financetracker.mapper;

import com.financetracker.dto.BudgetDTO;
import com.financetracker.model.Budget;
import org.springframework.stereotype.Component;

@Component
public class BudgetMapper {
    public BudgetDTO toDto(Budget budget) {
        BudgetDTO dto = new BudgetDTO();
        dto.setId(budget.getId());
        dto.setCategory(budget.getCategory());
        dto.setAmount(budget.getAmount());
        dto.setStartDate(budget.getStartDate());
        dto.setEndDate(budget.getEndDate());
        return dto;
    }

    public Budget toEntity(BudgetDTO dto) {
        Budget budget = new Budget();
        budget.setCategory(dto.getCategory());
        budget.setAmount(dto.getAmount());
        budget.setStartDate(dto.getStartDate());
        budget.setEndDate(dto.getEndDate());
        return budget;
    }
}

