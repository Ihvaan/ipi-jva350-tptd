package com.ipi.jva350;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import com.ipi.jva350.service.SalarieAideADomicileService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import static org.mockito.Mockito.*;

public class SalaireAideADomicileServiceTest {
    @Test
    public void testAjouteConge() throws SalarieException {
        SalarieAideADomicileRepository repository = mock(SalarieAideADomicileRepository.class);
        SalarieAideADomicileService service = new SalarieAideADomicileService(repository);
        SalarieAideADomicile salarie = new SalarieAideADomicile();

        service.ajouteConge(salarie, LocalDate.now(), LocalDate.now().plusDays(5));

        verify(repository, times(1)).save(salarie);
    }
}
