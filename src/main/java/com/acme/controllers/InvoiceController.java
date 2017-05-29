package com.acme.controllers;

import com.acme.models.InvoicePayload;
import com.acme.models.InvoiceResponse;
import com.acme.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService service;

    @Autowired
    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InvoiceResponse> createInvoice(@RequestBody InvoicePayload payload) {
        InvoiceResponse invoice = service.createInvoice(payload);

        return new ResponseEntity<>(invoice, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InvoiceResponse>> retrieveByCustomerId(@RequestParam(value = "customerId") long customerId) {
        List<InvoiceResponse> invoices = service.getByCustomerId(customerId);

        if (invoices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
}
