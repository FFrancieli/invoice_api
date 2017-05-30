package com.acme.repositories;

import com.acme.models.Invoice;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class InvoiceRepositoryImpl implements InvoiceRepositoryCustom {

    private EntityManager entityManager;

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

            parameters.forEach((column, value) ->
                queryConditionalWrapper(stringBuilder, column));

            stringBuilder.delete(stringBuilder.length() - 5, stringBuilder.length() - 1);
        }

        return stringBuilder.toString();
    }

    private void queryConditionalWrapper(StringBuilder stringBuilder, String column) {
        if (isStartDateColumn(column)) {
            stringBuilder.append(extractMonthFromDateFieldWrapper(column));
        } else {
            stringBuilder.append(column + " = :" + column + " AND ");
        }
    }

    private String extractMonthFromDateFieldWrapper(String column) {
        return String.format("EXTRACT(MONTH FROM %s) = :%s AND ", column, column);
    }

    private boolean isStartDateColumn(String column) {
        return column.equals("startDate");
    }

    private void setQueryParameters(Query query, Map<String, Object> parameters) {
        parameters.forEach(query::setParameter);
    }
}
