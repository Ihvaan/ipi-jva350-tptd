package com.ipi.jva350;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import com.ipi.jva350.service.SalarieAideADomicileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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

    @InjectMocks
    private SalarieAideADomicileService service;
    private SalarieAideADomicile salarie;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new SalarieAideADomicileService(repository);

        salarie = new SalarieAideADomicile();
        salarie.setNom("Test");
        salarie.setMoisEnCours(LocalDate.now());
        salarie.setJoursTravaillesAnneeNMoins1(15);
        salarie.setCongesPayesAcquisAnneeNMoins1(25);
        salarie.setCongesPayesPris(new LinkedHashSet<>());
        salarie.setMoisDebutContrat(LocalDate.now().minusYears(1));  // Initialiser moisDebutContrat
    }

    @Test
    void testAjouteConge_CasNormal() throws SalarieException {
        LocalDate dateDebut = LocalDate.now().plusDays(1);
        LocalDate dateFin = dateDebut.plusDays(25);
        when(repository.partCongesPrisTotauxAnneeNMoins1()).thenReturn(0.5);
        when(repository.save(any(SalarieAideADomicile.class))).thenReturn(salarie);

        service.ajouteConge(salarie, dateDebut, dateFin);

        verify(repository, times(1)).save(salarie);
        assertFalse(salarie.getCongesPayesPris().isEmpty());
        assertEquals(21, salarie.getCongesPayesPris().size());
    }

    @Test
    public void testAjouteConge_DatePassee() {
        LocalDate dateDebut = LocalDate.now().minusDays(1);
        LocalDate dateFin = LocalDate.now().plusDays(5);

        assertThrows(SalarieException.class, () ->
                        service.ajouteConge(salarie, dateDebut, dateFin),
                "Devrait lever une exception pour une date de début dans le passé"
        );
        verify(repository, never()).save(any());
    }

    @Test
    void testAjouteConge_PasDeDroitConges() {
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