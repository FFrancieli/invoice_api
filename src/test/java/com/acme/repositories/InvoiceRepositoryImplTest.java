package com.acme.repositories;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class InvoiceRepositoryImplTest {

    private static final int MONTH = 4;

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query mockedQuery;

    private InvoiceRepositoryCustom customRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        when(entityManager.createQuery(anyString())).thenReturn(mockedQuery);

        customRepository = new InvoiceRepositoryImpl(entityManager);
    }

    @Test
    public void createsQuery() throws Exception {
        customRepository.findByFilter(getQueryParameters());

        verify(entityManager, times(1)).createQuery(anyString());
    }

    @Test
    public void createsQueryWithCorrectSqlStatement() throws Exception {
        ArgumentCaptor<String> sqlQueryCaptor = ArgumentCaptor.forClass(String.class);

        Map<String, Object> parameters = getQueryParameters();

        customRepository.findByFilter(parameters);

        verify(entityManager, times(1)).createQuery(sqlQueryCaptor.capture());

        assertThat(sqlQueryCaptor.getValue(), is("SELECT i FROM Invoice i WHERE some = :some AND id = :id "));
    }

    @Test
    public void createsQueryWithCorrectSqlStatementForMonthSearch() throws Exception {
        ArgumentCaptor<String> sqlQueryCaptor = ArgumentCaptor.forClass(String.class);

        Map<String, Object> parameters = getQueryParameters();
        parameters.put("startDate", MONTH);

        customRepository.findByFilter(parameters);

        verify(entityManager, times(1)).createQuery(sqlQueryCaptor.capture());

        String expectedQuery = "SELECT i FROM Invoice i WHERE some = :some AND id = :id AND EXTRACT(MONTH FROM startDate) = :startDate ";
        assertThat(sqlQueryCaptor.getValue(), is(expectedQuery));
    }

    @Test
    public void setsQueryParameters() throws Exception {
        Map<String, Object> queryParameters = getQueryParameters();
        customRepository.findByFilter(queryParameters);

        verify(mockedQuery, times(queryParameters.size())).setParameter(anyString(), any());
    }

    private Map<String, Object> getQueryParameters() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("some", "parameter");
        parameters.put("id", 12L);
        return parameters;
    }
}