package com.acme.models;

import java.math.BigDecimal;

public class InvoiceResponse {

    private Long id;
    private Long customerId;
    private String addressId;
    private String type;
    private String typeLocalized;
    private BigDecimal amount;
    private BigDecimal vatAmount;
    private BigDecimal total;

    public InvoiceResponse(Invoice invoice) {
        this.id = invoice.getId();
        this.customerId = invoice.getCustomerId();
        this.addressId = invoice.getAddressId();
        this.type = invoice.getType();
        this.typeLocalized = invoice.getTypeLocalized();
        this.amount = invoice.getAmount();
        this.vatAmount = invoice.getVatAmount();
        this.total = invoice.getTotal();
    }

    public Long getId() {
        return id;
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
}
