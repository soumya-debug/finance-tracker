package com.financetracker.controller;

import com.financetracker.dto.ExpenseDTO;
import com.financetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public List<ExpenseDTO> getAllExpenses(@AuthenticationPrincipal OAuth2User user) {
        Long userId = getUserId(user);
        return expenseService.getAllExpenses(userId);
    }

    @PostMapping
    public ExpenseDTO createExpense(@RequestBody ExpenseDTO dto,
                                    @AuthenticationPrincipal OAuth2User user) {
        Long userId = getUserId(user);
        return expenseService.createExpense(dto, userId);
    }

    @PutMapping("/{id}")
    public ExpenseDTO updateExpense(@PathVariable Long id,
                                    @RequestBody ExpenseDTO dto,
                                    @AuthenticationPrincipal OAuth2User user) {
        Long userId = getUserId(user);
        return expenseService.updateExpense(id, dto, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id,
                              @AuthenticationPrincipal OAuth2User user) {
        Long userId = getUserId(user);
        expenseService.deleteExpense(id, userId);
    }

    @GetMapping("/category")
    public List<ExpenseDTO> getByCategory(@RequestParam String category,
                                          @AuthenticationPrincipal OAuth2User user) {
        Long userId = getUserId(user);
        return expenseService.getExpensesByCategory(userId, category);
    }

    @GetMapping("/range")
    public List<ExpenseDTO> getByDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
                                           @AuthenticationPrincipal OAuth2User user) {
        Long userId = getUserId(user);
        return expenseService.getExpensesByDateRange(userId, start, end);
    }

    private Long getUserId(OAuth2User user) {
        // You’ll need to resolve user ID from OAuth2 email
        String email = user.getAttribute("email");
        // Implement logic to fetch userId from email
        return 1L; // Replace with actual user lookup
    }
}
