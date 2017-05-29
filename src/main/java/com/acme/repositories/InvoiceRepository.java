package com.acme.repositories;

import com.acme.models.Invoice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    List<Invoice> findByCustomerId(Long customerId);
}
