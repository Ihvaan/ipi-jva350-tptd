package com.ipi.jva350;

import com.ipi.jva350.model.Entreprise;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EntrepriseTest {

    @Test
    void testEstDansPlage_CasNormaux() {
        LocalDate debut = LocalDate.of(2024, 3, 1);
        LocalDate fin = LocalDate.of(2024, 3, 31);

        // Date dans la plage
        LocalDate date = LocalDate.of(2024, 3, 15);
        assertTrue(Entreprise.estDansPlage(date, debut, fin));

        // Date égale à la date de début
        assertTrue(Entreprise.estDansPlage(debut, debut, fin));

        // Date égale à la date de fin
        assertTrue(Entreprise.estDansPlage(fin, debut, fin));
    }

    @Test
    void testEstDansPlage_CasLimites() {
        LocalDate debut = LocalDate.of(2024, 3, 1);
        LocalDate fin = LocalDate.of(2024, 3, 31);

        // Date avant la plage
        LocalDate dateAvant = LocalDate.of(2024, 2, 28);
        assertFalse(Entreprise.estDansPlage(dateAvant, debut, fin));

        // Date après la plage
        LocalDate dateApres = LocalDate.of(2024, 4, 1);
        assertFalse(Entreprise.estDansPlage(dateApres, debut, fin));
    }

    @Test
    void testEstDansPlage_CasNull() {
        LocalDate debut = LocalDate.of(2024, 3, 1);
        LocalDate fin = LocalDate.of(2024, 3, 31);
        LocalDate date = LocalDate.of(2024, 3, 15);

        // Test avec date null
        assertThrows(IllegalArgumentException.class,
                () -> Entreprise.estDansPlage(null, debut, fin));

        // Test avec début null
        assertThrows(IllegalArgumentException.class,
                () -> Entreprise.estDansPlage(date, null, fin));

        // Test avec fin null
        assertThrows(IllegalArgumentException.class,
                () -> Entreprise.estDansPlage(date, debut, null));
    }

    @Test
    void testEstDansPlage_DatesInvalides() {
        LocalDate debut = LocalDate.of(2024, 3, 31);
        LocalDate fin = LocalDate.of(2024, 3, 1);
        LocalDate date = LocalDate.of(2024, 3, 15);

        // Test avec date de fin avant date de début
        assertThrows(IllegalArgumentException.class,
                () -> Entreprise.estDansPlage(date, debut, fin));
    }
    @ParameterizedTest
    @CsvSource({
            // Jours fériés fixes
            "2024-01-01, true",  // Jour de l'an
            "2024-05-01, true",  // Fête du Travail
            "2024-05-08, true",  // Victoire 1945
            "2024-07-14, true",  // Fête nationale
            "2024-08-15, true",  // Assomption
            "2024-11-01, true",  // Toussaint
            "2024-11-11, true",  // Armistice 1918
            "2024-12-25, true",  // Noël

            // Jours fériés mobiles 2024 (basés sur Pâques le 31 mars 2024)
            "2024-04-01, true",  // Lundi de Pâques
            "2024-05-09, true",  // Ascension (Pâques + 40 jours)
            "2024-05-20, true",  // Pentecôte (Pâques + 50 jours)

            // Jours non fériés
            "2024-01-02, false", // Lendemain du jour de l'an
            "2024-07-15, false", // Lendemain de la fête nationale
            "2024-12-26, false", // Lendemain de Noël

            // Cas limites
            "2024-02-29, false", // Année bissextile
            "2025-01-01, true"   // Année suivante
    })
    void testEstJourFerie(LocalDate date, boolean expectedResult) {
        assertEquals(expectedResult, Entreprise.estJourFerie(date),
                "Pour la date " + date + ", estJourFerie devrait retourner " + expectedResult);
    }

    @Test
    void testEstJourFerie_Null() {
        assertThrows(IllegalArgumentException.class, () -> Entreprise.estJourFerie(null),
                "estJourFerie devrait lever une exception pour une date null");
    }
    }