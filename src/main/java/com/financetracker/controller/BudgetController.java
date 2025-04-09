package com.financetracker.controller;

import com.financetracker.dto.BudgetDTO;
import com.financetracker.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping
    public List<BudgetDTO> getAllBudgets(@AuthenticationPrincipal OAuth2User user) {
        Long userId = getUserId(user);
        return budgetService.getBudgets(userId);
    }

    @PostMapping
    public BudgetDTO createBudget(@RequestBody BudgetDTO dto,
                                  @AuthenticationPrincipal OAuth2User user) {
        Long userId = getUserId(user);
        return budgetService.createBudget(dto, userId);
    }

    @PutMapping("/{id}")
    public BudgetDTO updateBudget(@PathVariable Long id,
                                  @RequestBody BudgetDTO dto,
                                  @AuthenticationPrincipal OAuth2User user) {
        Long userId = getUserId(user);
        return budgetService.updateBudget(id, dto, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteBudget(@PathVariable Long id,
                             @AuthenticationPrincipal OAuth2User user) {
        Long userId = getUserId(user);
        budgetService.deleteBudget(id, userId);
    }

    private Long getUserId(OAuth2User user) {
        String email = user.getAttribute("email");
        // Replace with logic to fetch userId by email
        return 1L;
    }
}

