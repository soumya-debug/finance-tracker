package com.financetracker.service;

import com.financetracker.dto.ExpenseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {
    ExpenseDTO createExpense(ExpenseDTO dto, Long userId);
    List<ExpenseDTO> getAllExpenses(Long userId);
    List<ExpenseDTO> getExpensesByCategory(Long userId, String category);
    List<ExpenseDTO> getExpensesByDateRange(Long userId, LocalDate start, LocalDate end);
    ExpenseDTO updateExpense(Long id, ExpenseDTO dto, Long userId);
    void deleteExpense(Long id, Long userId);
}

