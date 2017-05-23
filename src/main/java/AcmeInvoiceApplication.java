import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.acme")
public class AcmeInvoiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcmeInvoiceApplication.class, args);
	}
}
