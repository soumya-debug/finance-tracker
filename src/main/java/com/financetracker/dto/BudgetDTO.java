package com.financetracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BudgetDTO {
    private Long id;
    private String category;
    private BigDecimal amount;
    private LocalDate startDate;
    private LocalDate endDate;

    public BudgetDTO(Long id, String category, BigDecimal amount, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public BudgetDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}

