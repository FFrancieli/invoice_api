package com.acme.repositories;

import com.acme.models.Invoice;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class InvoiceRepositoryImpl implements InvoiceRepositoryCustom {

    EntityManager entityManager;

    InvoiceRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Invoice> findByFilter(Map<String, Object> parameters) {
        Query query = entityManager.createQuery(buildQuery(parameters));

        setQueryParameters(query, parameters);

        return query.getResultList();
    }

    private String buildQuery(Map<String, Object> parameters) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SELECT i FROM Invoice i");

        if (! parameters.isEmpty()) {
            stringBuilder.append(" WHERE ");

            parameters.forEach((column, value) -> {
                stringBuilder.append(column + " = :" + column + " AND ");
            });

            stringBuilder.delete(stringBuilder.length() - 5, stringBuilder.length() - 1);
        }

        return stringBuilder.toString();
    }

    private void setQueryParameters(Query query, Map<String, Object> parameters) {
        parameters.forEach(query::setParameter);
    }
}
