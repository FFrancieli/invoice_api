package services;

import com.acme.models.Invoice;
import com.acme.models.InvoicePayload;
import com.acme.models.InvoiceResponse;
import com.acme.repositories.InvoiceRepository;
import com.acme.services.InvoiceService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class InvoiceServiceTest {

    @Mock
    InvoiceRepository repository;

    InvoicePayload payload;
    Invoice invoice;

    InvoiceService service;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        payload = buildInvoicePayload();
        invoice = new Invoice(payload);

        when(repository.save(any(Invoice.class))).thenReturn(invoice);

        service = new InvoiceService(repository);
    }

    @Test
    public void savesInvoiceOnDatabase() throws Exception {
        service.createInvoice(payload);

        verify(repository, times(1)).save(any(Invoice.class));
    }

    @Test
    public void savesInvoiceWithCorrectData() throws Exception {
        ArgumentCaptor<Invoice> entityCaptor = ArgumentCaptor.forClass(Invoice.class);

        service.createInvoice(payload);

        verify(repository, times(1)).save(entityCaptor.capture());

        Invoice savedInvoice = entityCaptor.getValue();

        assertThat(savedInvoice.getCustomerId(), is(payload.getCustomerId()));
        assertThat(savedInvoice.getAddressId(), is(payload.getAddressId()));
        assertThat(savedInvoice.getType(), is(payload.getType()));
        assertThat(savedInvoice.getTypeLocalized(), is(payload.getTypeLocalized()));
        assertThat(savedInvoice.getAmount(), is(payload.getAmount()));
        assertThat(savedInvoice.getVatAmount(), is(payload.getVatAmount()));
        assertThat(savedInvoice.getTotal(), is(payload.getTotal()));
    }

    @Test
    public void returnsHttpStatusCreatedWhenInvoiceIsCreatedOnDatabase() throws Exception {
        ResponseEntity<InvoiceResponse> response = service.createInvoice(payload);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
    }

    @Test
    public void returnsSavedInvoiceDetails() throws Exception {
        ResponseEntity<InvoiceResponse> response = service.createInvoice(payload);

        InvoiceResponse expectedBody = new InvoiceResponse(invoice);

        assertThat(response.getBody(), samePropertyValuesAs(expectedBody));
    }

    private InvoicePayload buildInvoicePayload() {
        InvoicePayload payload = new InvoicePayload();

        payload.setCustomerId(1L);
        payload.setAddressId("4146J34FJ");
        payload.setType("ShopPurchase");
        payload.setTypeLocalized("Winkel aabkoop");
        payload.setAmount(new BigDecimal(165.29));
        payload.setVatAmount(new BigDecimal(34.71));
        payload.setTotal(new BigDecimal(200));

        return payload;
    }
}