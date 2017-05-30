package com.acme.repositories;

import com.acme.models.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long>, InvoiceRepositoryCustom {
}