package com.acme.controllers;

import com.acme.models.InvoicePayload;
import com.acme.models.InvoiceResponse;
import com.acme.services.InvoiceService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class InvoiceControllerTest {

    @Mock
    InvoiceService service;

    InvoiceController controller;
    private InvoicePayload payload;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        controller = new InvoiceController(service);

        payload = generateInvoicePayload();
    }

    @Test
    public void savesInvoiceOnDatabase() throws Exception {
        controller.createInvoice(any(InvoicePayload.class));

        verify(service, times(1)).createInvoice(any(InvoicePayload.class));
    }

    @Test
    public void savesInvoiceWithInputPayload() throws Exception {
        ArgumentCaptor<InvoicePayload> payloadCaptor = ArgumentCaptor.forClass(InvoicePayload.class);

        controller.createInvoice(payload);

        verify(service, times(1)).createInvoice(payloadCaptor.capture());

        assertThat(payloadCaptor.getValue(), samePropertyValuesAs(payload));
    }

    @Test
    public void returnsHttpStatusCreatedWhenInvoiceIsSuccessfullyPosted() throws Exception {
        when(service.createInvoice(payload)).thenReturn(new ResponseEntity(HttpStatus.CREATED));

        ResponseEntity<InvoiceResponse> response = controller.createInvoice(payload);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
    }

    private InvoicePayload generateInvoicePayload() {
        InvoicePayload payload = new InvoicePayload();
        payload.setAmount(new BigDecimal("13.90"));
        payload.setCustomerId(23L);
        payload.setAddressId("1238F0azo2");
        payload.setTotal(new BigDecimal("15.90"));
        payload.setVatAmount(new BigDecimal("2.00"));
        payload.setType("type");
        payload.setTypeLocalized("Type localized");
        return payload;
    }
}