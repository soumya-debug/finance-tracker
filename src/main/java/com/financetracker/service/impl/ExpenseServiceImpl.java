package com.financetracker.service.impl;

import com.financetracker.dto.ExpenseDTO;
import com.financetracker.mapper.ExpenseMapper;
import com.financetracker.model.Expense;
import com.financetracker.model.User;
import com.financetracker.repository.ExpenseRepository;
import com.financetracker.repository.UserRepository;
import com.financetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private ExpenseMapper expenseMapper;

    @Override
    public ExpenseDTO createExpense(ExpenseDTO dto, Long userId) {
        Expense expense = expenseMapper.toEntity(dto);
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        expense.setUser(user);
        return expenseMapper.toDto(expenseRepo.save(expense));
    }

    @Override
    public List<ExpenseDTO> getAllExpenses(Long userId) {
        return expenseRepo.findByUserId(userId).stream()
                .map(expenseMapper::toDto)
                .toList();
    }

    @Override
    public List<ExpenseDTO> getExpensesByCategory(Long userId, String category) {
        return expenseRepo.findByUserIdAndCategory(userId, category).stream()
                .map(expenseMapper::toDto)
                .toList();
    }

    @Override
    public List<ExpenseDTO> getExpensesByDateRange(Long userId, LocalDate start, LocalDate end) {
        return expenseRepo.findByUserIdAndDateBetween(userId, start, end).stream()
                .map(expenseMapper::toDto)
                .toList();
    }

    @Override
    public ExpenseDTO updateExpense(Long id, ExpenseDTO dto, Long userId) {
        Expense expense = expenseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        if (!expense.getUser().getId().equals(userId))
            throw new RuntimeException("Unauthorized");

        expense.setTitle(dto.getTitle());
        expense.setCategory(dto.getCategory());
        expense.setAmount(dto.getAmount());
        expense.setDate(dto.getDate());

        return expenseMapper.toDto(expenseRepo.save(expense));
    }

    @Override
    public void deleteExpense(Long id, Long userId) {
        Expense expense = expenseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        if (!expense.getUser().getId().equals(userId))
            throw new RuntimeException("Unauthorized");
        expenseRepo.delete(expense);
    }
}

