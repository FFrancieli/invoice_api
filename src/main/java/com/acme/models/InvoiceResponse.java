package com.acme.models;

import com.acme.parsers.TimestampParser;

import java.math.BigDecimal;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class InvoiceResponse {

    private Long id;
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

    public InvoiceResponse() {
    }

    public InvoiceResponse(Invoice invoice) {
        this.id = invoice.getId();
        this.customerId = invoice.getCustomerId();
        this.addressId = invoice.getAddressId();
        this.type = invoice.getType();
        this.typeLocalized = invoice.getTypeLocalized();
        this.amount = invoice.getAmount();
        this.vatAmount = invoice.getVatAmount();
        this.total = invoice.getTotal();
        this.paymentDueDate = TimestampParser.toString(invoice.getPaymentDueDate(), ISO_LOCAL_DATE_TIME);
        this.startDate = TimestampParser.toString(invoice.getStartDate(), ISO_LOCAL_DATE_TIME);
        this.endDate = TimestampParser.toString(invoice.getEndDate(), ISO_LOCAL_DATE_TIME);
        this.periodDescription = invoice.getPeriodDescription();
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

    public String getPaymentDueDate() {
        return paymentDueDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getPeriodDescription() {
        return periodDescription;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
