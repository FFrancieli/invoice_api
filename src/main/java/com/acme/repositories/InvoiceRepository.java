package com.acme.repositories;

import com.acme.models.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long>, InvoiceRepositoryCustom {
    List<Invoice> findByCustomerId(Long customerId);
}