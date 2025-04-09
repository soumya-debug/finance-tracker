package com.financetracker.mapper;

import com.financetracker.dto.ExpenseDTO;
import com.financetracker.model.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {
    public ExpenseDTO toDto(Expense expense) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(expense.getId());
        dto.setTitle(expense.getTitle());
        dto.setCategory(expense.getCategory());
        dto.setAmount(expense.getAmount());
        dto.setDate(expense.getDate());
        return dto;
    }

    public Expense toEntity(ExpenseDTO dto) {
        Expense e = new Expense();
        e.setTitle(dto.getTitle());
        e.setCategory(dto.getCategory());
        e.setAmount(dto.getAmount());
        e.setDate(dto.getDate());
        return e;
    }
}
