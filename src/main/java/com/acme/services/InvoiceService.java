package com.acme.services;

import com.acme.models.Invoice;
import com.acme.models.InvoicePayload;
import com.acme.models.InvoiceResponse;
import com.acme.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    private final InvoiceRepository repository;

    @Autowired
    public InvoiceService(InvoiceRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<InvoiceResponse> createInvoice(InvoicePayload payload) {
        Invoice invoice = new Invoice(payload);

        Invoice savedEntity = repository.save(invoice);

        return new ResponseEntity<>(new InvoiceResponse(savedEntity), HttpStatus.CREATED);
    }
}