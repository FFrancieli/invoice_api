package com.acme.services;

import com.acme.models.Invoice;
import com.acme.models.InvoicePayload;
import com.acme.models.InvoiceResponse;
import com.acme.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final InvoiceRepository repository;

    @Autowired
    public InvoiceService(InvoiceRepository repository) {
        this.repository = repository;
    }

    public InvoiceResponse createInvoice(InvoicePayload payload) {
        Invoice invoice = new Invoice(payload);

        Invoice savedEntity = repository.save(invoice);

        return new InvoiceResponse(savedEntity);
    }

    public List<InvoiceResponse> getByCustomerId(Long customerId) {
        List<Invoice> invoices = repository.findByCustomerId(customerId);

        return entityToInvoiceResponse(invoices);
    }

    public List<InvoiceResponse> findByFilter(Long customerId, String addressId, Integer month, String invoiceType){
        Map<String, Object> query = buildQueryParameters(customerId, addressId, month, invoiceType);

        List<Invoice> invoices = repository.findByFilter(query);

        return entityToInvoiceResponse(invoices);
    }

    private Map<String, Object> buildQueryParameters(Long customerId, String addressId, Integer month, String invoiceType) {
        Map<String,Object> query = new HashMap<>();

        if (customerId != null) {
            query.put("customerId", customerId);
        }

        if (addressId != null) {
            query.put("addressId", addressId);
        }

        if (month != null) {
            query.put("startDate", month);
        }

        if (invoiceType != null) {
            query.put("type", invoiceType);
        }

        return query;
    }

    private List<InvoiceResponse> entityToInvoiceResponse(List<Invoice> invoices) {
        return invoices.stream().map(InvoiceResponse::new).collect(Collectors.toList());
    }
}