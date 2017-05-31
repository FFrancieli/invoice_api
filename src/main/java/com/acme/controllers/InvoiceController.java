package com.acme.controllers;

import com.acme.models.InvoicePayload;
import com.acme.models.InvoiceResponse;
import com.acme.services.InvoiceService;
import com.acme.validators.InvoicePayloadValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @InitBinder("invoicePayload")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new InvoicePayloadValidator());
    }

    private final InvoiceService service;

    @Autowired
    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InvoiceResponse> createInvoice(@Valid @RequestBody InvoicePayload payload) {
        InvoiceResponse invoice = service.createInvoice(payload);

        return new ResponseEntity<>(invoice, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InvoiceResponse>> retrieveInvoiceBy(
            @RequestParam(value = "customerId") Long customerId,
            @RequestParam(value = "addressId", required = false) String addressId,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "invoiceType", required = false) String invoiceType) {

        List<InvoiceResponse> invoices = service.findByFilter(customerId, addressId, month, invoiceType);

        if (invoices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
}
