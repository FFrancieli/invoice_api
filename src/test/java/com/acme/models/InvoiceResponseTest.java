package com.acme.models;

import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class InvoiceResponseTest {

    @Test
    public void buildsFromInvoiceEntity() throws Exception {
        Invoice invoice = buildInvoiceEntity();

        InvoiceResponse response = new InvoiceResponse(invoice);

        assertThat(response.getId(), is(invoice.getId()));
        assertThat(response.getCustomerId(), is(invoice.getCustomerId()));
        assertThat(response.getAddressId(), is(invoice.getAddressId()));
        assertThat(response.getType(), is(invoice.getType()));
        assertThat(response.getTypeLocalized(), is(invoice.getTypeLocalized()));
        assertThat(response.getAmount(), is(invoice.getAmount()));
        assertThat(response.getVatAmount(), is(invoice.getVatAmount()));
        assertThat(response.getTotal(), is(invoice.getTotal()));
        assertThat(response.getPaymentDueDate(), is("2014-11-13T00:00:00"));
        assertThat(response.getStartDate(), is("2014-11-13T00:00:00"));
        assertThat(response.getEndDate(), is("2014-11-13T00:00:00"));
        assertThat(response.getPeriodDescription(), is(invoice.getPeriodDescription()));
    }

    private Invoice buildInvoiceEntity() {
        Invoice invoice = new Invoice();

        invoice.setId(190L);
        invoice.setCustomerId(98L);
        invoice.setAddressId("9834of0f1");
        invoice.setType("type");
        invoice.setTypeLocalized("typeLocalized");
        invoice.setAmount(new BigDecimal("150.99"));
        invoice.setVatAmount(new BigDecimal("1.01"));
        invoice.setTotal(new BigDecimal("152.00"));
        invoice.setPaymentDueDate(new Timestamp(1415844000000L));
        invoice.setEndDate(new Timestamp(1415844000000L));
        invoice.setStartDate(new Timestamp(1415844000000L));

        return invoice;
    }
}
