package com.ipi.jva350;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import com.ipi.jva350.service.SalarieAideADomicileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SalarieAideADomicileServiceTest {

    @Mock
    private SalarieAideADomicileRepository repository;

    private SalarieAideADomicileService service;
    private SalarieAideADomicile salarie;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new SalarieAideADomicileService(repository);

        // Préparation du salarié de test
        salarie = new SalarieAideADomicile();
        salarie.setNom("Test");
        salarie.setJoursTravaillesAnneeNMoins1(15);
        salarie.setCongesPayesAcquisAnneeNMoins1(25);
        salarie.setCongesPayesPris(new LinkedHashSet<>());
    }

    @Test
    void testAjouteConge_CasNormal() throws SalarieException {
        // Given
        LocalDate dateDebut = LocalDate.now().plusDays(1);
        LocalDate dateFin = dateDebut.plusDays(5);
        when(repository.save(any(SalarieAideADomicile.class))).thenReturn(salarie);

        // When
        service.ajouteConge(salarie, dateDebut, dateFin);

        // Then
        verify(repository, times(1)).save(salarie);
        assertFalse(salarie.getCongesPayesPris().isEmpty());
    }

    @Test
    void testAjouteConge_DatePassee() {
        // Given
        LocalDate dateDebut = LocalDate.now().minusDays(1);
        LocalDate dateFin = LocalDate.now().plusDays(5);

        // When & Then
        assertThrows(SalarieException.class, () ->
                        service.ajouteConge(salarie, dateDebut, dateFin),
                "Devrait lever une exception pour une date de début dans le passé"
        );
        verify(repository, never()).save(any());
    }

    @Test
    void testAjouteConge_PasDeDroitConges() {
        // Given
        salarie.setJoursTravaillesAnneeNMoins1(5); // Moins de 10 jours travaillés
        LocalDate dateDebut = LocalDate.now().plusDays(1);
        LocalDate dateFin = dateDebut.plusDays(5);

        // When & Then
        assertThrows(SalarieException.class, () ->
                        service.ajouteConge(salarie, dateDebut, dateFin),
                "Devrait lever une exception quand le salarié n'a pas droit aux congés"
        );
        verify(repository, never()).save(any());
    }
}