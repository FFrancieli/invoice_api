package com.acme.models;

import com.acme.parsers.TimestampParser;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class InvoiceTest {

    @Test
    public void buildsFromPayload() throws Exception {
        InvoicePayload payload = buildInvoicePayload();

        Invoice invoice = new Invoice(payload);

        assertThat(invoice.getCustomerId(), is(payload.getCustomerId()));
        assertThat(invoice.getAddressId(), is(payload.getAddressId()));
        assertThat(invoice.getType(), is(payload.getType()));
        assertThat(invoice.getTypeLocalized(), is(payload.getTypeLocalized()));
        assertThat(invoice.getAmount(), is(payload.getAmount()));
        assertThat(invoice.getVatAmount(), is(payload.getVatAmount()));
        assertThat(invoice.getTotal(), is(payload.getTotal()));
        assertThat(invoice.getPeriodDescription(), is(payload.getPeriodDescription()));

        assertThat(invoice.getPaymentDueDate(), is(TimestampParser.fromString(payload.getPaymentDueDate())));
        assertThat(invoice.getStartDate(), is(TimestampParser.fromString(payload.getStartDate())));
        assertThat(invoice.getEndDate(), is(TimestampParser.fromString(payload.getEndDate())));
    }

    @Test
    public void doesNotSetStartDateWhenItIsEmptyOnPayload() throws Exception {
        InvoicePayload payload = buildInvoicePayload();
        payload.setStartDate("");

        Invoice invoice = new Invoice(payload);

        assertThat(invoice.getStartDate(), is(nullValue()));
    }

    @Test
    public void doesNotSetStartDateWhenItIsNullOnPayload() throws Exception {
        InvoicePayload payload = buildInvoicePayload();
        payload.setStartDate(null);

        Invoice invoice = new Invoice(payload);

        assertThat(invoice.getStartDate(), is(nullValue()));
    }

    @Test
    public void doesNotSetEndDateWhenItIsEmptyOnPayload() throws Exception {
        InvoicePayload payload = buildInvoicePayload();
        payload.setEndDate("");

        Invoice invoice = new Invoice(payload);

        assertThat(invoice.getEndDate(), is(nullValue()));
    }

    @Test
    public void doesNotSetEndDateWhenItIsNullOnPayload() throws Exception {
        InvoicePayload payload = buildInvoicePayload();
        payload.setEndDate(null);

        Invoice invoice = new Invoice(payload);

        assertThat(invoice.getEndDate(), is(nullValue()));
    }

    private InvoicePayload buildInvoicePayload() {
        InvoicePayload payload = new InvoicePayload();

        payload.setCustomerId(1L);
        payload.setAddressId("4146J34FJ");
        payload.setType("ShopPurchase");
        payload.setTypeLocalized("Winkel aabkoop");
        payload.setAmount(new BigDecimal(165.29));
        payload.setVatAmount(new BigDecimal(34.71));
        payload.setTotal(new BigDecimal(200));
        payload.setPaymentDueDate("2017-01-20T00:00:00");
        payload.setStartDate("2017-01-01T00:00:00");
        payload.setEndDate("2017-12-20T00:00:00");
        payload.setPeriodDescription("periodDescription");

        return payload;
    }
}