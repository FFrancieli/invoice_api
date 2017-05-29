package com.acme.models;

import java.math.BigDecimal;

public class InvoicePayload {

    private Long customerId;
    private String addressId;
    private String type;
    private String typeLocalized;
    private BigDecimal amount;
    private BigDecimal vatAmount;
    private BigDecimal total;
    private String paymentDueDate;
    private String startDate;
    private String endDate;
    private String periodDescription;


    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTypeLocalized(String typeLocalized) {
        this.typeLocalized = typeLocalized;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setVatAmount(BigDecimal vatAmount) {
        this.vatAmount = vatAmount;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getAddressId() {
        return addressId;
    }

    public String getType() {
        return type;
    }

    public String getTypeLocalized() {
        return typeLocalized;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getVatAmount() {
        return vatAmount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setPaymentDueDate(String paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setPeriodDescription(String periodDescription) {
        this.periodDescription = periodDescription;
    }

    public String getPeriodDescription() {
        return periodDescription;
    }

    public String getPaymentDueDate() {
        return paymentDueDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
