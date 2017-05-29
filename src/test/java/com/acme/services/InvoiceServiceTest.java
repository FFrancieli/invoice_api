package com.acme.services;

import com.acme.models.Invoice;
import com.acme.models.InvoicePayload;
import com.acme.models.InvoiceResponse;
import com.acme.repositories.InvoiceRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class InvoiceServiceTest {

    @Mock
    InvoiceRepository repository;

    InvoicePayload payload;
    Invoice invoice;

    InvoiceService service;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        payload = buildInvoicePayload();
        invoice = new Invoice(payload);

        when(repository.save(any(Invoice.class))).thenReturn(invoice);

        List<Invoice> invoices = Arrays.asList(buildInvoice(1L), buildInvoice(2L));
        when(repository.findByCustomerId(anyLong())).thenReturn(invoices);

        service = new InvoiceService(repository);
    }

    @Test
    public void savesInvoiceOnDatabase() throws Exception {
        service.createInvoice(payload);

        verify(repository, times(1)).save(any(Invoice.class));
    }

    @Test
    public void savesInvoiceWithCorrectData() throws Exception {
        ArgumentCaptor<Invoice> entityCaptor = ArgumentCaptor.forClass(Invoice.class);

        service.createInvoice(payload);

        verify(repository, times(1)).save(entityCaptor.capture());

        Invoice savedInvoice = entityCaptor.getValue();

        assertThat(savedInvoice.getCustomerId(), is(payload.getCustomerId()));
        assertThat(savedInvoice.getAddressId(), is(payload.getAddressId()));
        assertThat(savedInvoice.getType(), is(payload.getType()));
        assertThat(savedInvoice.getTypeLocalized(), is(payload.getTypeLocalized()));
        assertThat(savedInvoice.getAmount(), is(payload.getAmount()));
        assertThat(savedInvoice.getVatAmount(), is(payload.getVatAmount()));
        assertThat(savedInvoice.getTotal(), is(payload.getTotal()));
    }

    @Test
    public void returnsSavedInvoiceDetails() throws Exception {
        InvoiceResponse response = service.createInvoice(payload);

        InvoiceResponse expectedBody = new InvoiceResponse(invoice);

        assertThat(response, samePropertyValuesAs(expectedBody));
    }

    @Test
    public void retrievesInvoicesByCustomerId() throws Exception {
        service.getByCustomerId(anyLong());

        verify(repository, times(1)).findByCustomerId(anyLong());
    }

    @Test
    public void retrievesInvoicesForCorrectUserId() throws Exception {
        ArgumentCaptor<Long> customerIdCaptor = ArgumentCaptor.forClass(Long.class);

        service.getByCustomerId(987L);

        verify(repository, times(1)).findByCustomerId(customerIdCaptor.capture());

        assertThat(customerIdCaptor.getValue(), is(987L));
    }

    @Test
    public void returnsInvoicesFoundForCustomerId() throws Exception {
        List<InvoiceResponse> foundInvoices = service.getByCustomerId(anyLong());

        verify(repository, times(1)).findByCustomerId(anyLong());

        assertThat(foundInvoices, hasSize(2));
    }

    @Test
    public void returnsEmptyListWhenInvoicesAreNotFoundForCustomerId() throws Exception {
        when(repository.findByCustomerId(anyLong())).thenReturn(Collections.emptyList());

        List<InvoiceResponse> foundInvoices = service.getByCustomerId(anyLong());

        verify(repository, times(1)).findByCustomerId(anyLong());

        assertThat(foundInvoices, is(empty()));
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

    private Invoice buildInvoice(Long id) {
        Invoice invoice = new Invoice();

        invoice.setId(id);
        invoice.setCustomerId(1L);
        invoice.setAddressId("4146J34FJ");
        invoice.setType("ShopPurchase");
        invoice.setTypeLocalized("Winkel aabkoop");
        invoice.setAmount(new BigDecimal(165.29));
        invoice.setVatAmount(new BigDecimal(34.71));
        invoice.setTotal(new BigDecimal(200));
        invoice.setPaymentDueDate(new Timestamp(1415844000000L));
        invoice.setStartDate(new Timestamp(1415844000000L));
        invoice.setEndDate(new Timestamp(1415844000000L));
        invoice.setPeriodDescription("periodDescription");

        return invoice;
    }
}