package com.acme.repositories;

import com.acme.models.Invoice;

import java.util.List;
import java.util.Map;

public interface InvoiceRepositoryCustom {
    List<Invoice> findByFilter(Map<String, Object> parameters);
}
