package com.acme.models;

import com.acme.parsers.TimestampParser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "invoice_seq_gen")
    @SequenceGenerator(name = "invoice_seq_gen", sequenceName = "public.invoice_id_seq")
    private Long id;
    private Long customerId;
    private String addressId;
    private String type;
    private String typeLocalized;
    private BigDecimal amount;
    private BigDecimal vatAmount;
    private BigDecimal total;
    private String periodDescription;
    private Timestamp paymentDueDate;
    private Timestamp startDate;
    private Timestamp endDate;

    public Invoice() {
    }

    public Invoice(InvoicePayload payload) {
        this.customerId = payload.getCustomerId();
        this.addressId = payload.getAddressId();
        this.type = payload.getType();
        this.typeLocalized = payload.getTypeLocalized();
        this.amount = payload.getAmount();
        this.vatAmount = payload.getVatAmount();
        this.total = payload.getTotal();
        this.periodDescription = payload.getPeriodDescription();
        this.paymentDueDate = TimestampParser.fromString(payload.getPaymentDueDate());
        this.startDate = TimestampParser.fromString(payload.getStartDate());
        this.endDate = TimestampParser.fromString(payload.getEndDate());
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

    public void setId(long id) {
        this.id = id;
    }

    public void setCustomerId(long customerId) {
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

    public Long getId() {
        return id;
    }

    public String getPeriodDescription() {
        return periodDescription;
    }

    public Timestamp getPaymentDueDate() {
        return paymentDueDate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }
}
