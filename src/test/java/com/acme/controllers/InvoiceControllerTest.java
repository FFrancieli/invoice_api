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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class InvoiceControllerTest {

    public static final long CUSTOMER_ID = 23L;
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
        when(service.createInvoice(payload)).thenReturn(new InvoiceResponse());

        ResponseEntity<InvoiceResponse> response = controller.createInvoice(payload);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
    }

    @Test
    public void getsInvoiceThatMatchFilterFromDatabase() throws Exception {
        controller.retrieveInvoiceBy(CUSTOMER_ID, "addressId");

        verify(service, times(1)).findByFilter(CUSTOMER_ID, "addressId");
    }

    @Test
    public void returnsHttpStatusOkWhenThereAreInvoicesAThatMatchFilter() throws Exception {
        when(service.findByFilter(anyLong(), anyString()))
                .thenReturn(Arrays.asList(buildInvoiceResponse(1L), buildInvoiceResponse(34L)));

        ResponseEntity<List<InvoiceResponse>> response = controller.retrieveInvoiceBy(anyLong(), anyString());

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void returnsInvoicesThatMatchFilter() throws Exception {
        when(service.findByFilter(anyLong(), anyString()))
                .thenReturn(Arrays.asList(buildInvoiceResponse(1L), buildInvoiceResponse(34L)));

        ResponseEntity<List<InvoiceResponse>> response = controller.retrieveInvoiceBy(anyLong(), anyString());

        assertThat(response.getBody(), hasSize(2));
    }

    @Test
    public void returnsHttpStatusNotFoundWhenThereIsNoInvoiceThatMatchesFilter() throws Exception {
        when(service.getByCustomerId(anyLong())).thenReturn(Collections.emptyList());

        ResponseEntity<List<InvoiceResponse>> response = controller.retrieveInvoiceBy(anyLong(), anyString());

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    private InvoicePayload generateInvoicePayload() {
        InvoicePayload payload = new InvoicePayload();
        payload.setAmount(new BigDecimal("13.90"));
        payload.setCustomerId(CUSTOMER_ID);
        payload.setAddressId("1238F0azo2");
        payload.setTotal(new BigDecimal("15.90"));
        payload.setVatAmount(new BigDecimal("2.00"));
        payload.setType("type");
        payload.setTypeLocalized("Type localized");
        return payload;
    }

    private InvoiceResponse buildInvoiceResponse(Long id) {
        InvoiceResponse response = new InvoiceResponse();

        response.setId(id);

        return response;
    }
}