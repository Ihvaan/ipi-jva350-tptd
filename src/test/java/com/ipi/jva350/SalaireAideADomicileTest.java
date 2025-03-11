package com.ipi.jva350;

import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SalaireAideADomicileTest {

    @Test
    void testSalarieAideADomicile() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        assertNotNull(salarie);
    }

    @Test
    void testSalarieAideADomicileStringLocalDateLocalDateDoubleDoubleDoubleDoubleDouble() {
        SalarieAideADomicile salarie = new SalarieAideADomicile("Toto", LocalDate.now(), LocalDate.now(), 0, 0, 0, 0, 0);
        assertNotNull(salarie);
        assertEquals("Toto", salarie.getNom());
    }

    @Test
    void testGetNom() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setNom("Toto");
        assertEquals("Toto", salarie.getNom());
    }

    @Test
    void testSetNom() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setNom("Toto");
        assertEquals("Toto", salarie.getNom());
    }

    @Test
    void testGetJoursTravaillesAnneeN() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeN(10);
        assertEquals(10, salarie.getJoursTravaillesAnneeN());
    }

    @Test
    void testSetJoursTravaillesAnneeN() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeN(10);
        assertEquals(10, salarie.getJoursTravaillesAnneeN());
    }

    @Test
    void testGetCongesPayesAcquisAnneeN() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setCongesPayesAcquisAnneeN(10);
        assertEquals(10, salarie.getCongesPayesAcquisAnneeN());
    }

    @Test
    void testSetCongesPayesAcquisAnneeN() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setCongesPayesAcquisAnneeN(10);
        assertEquals(10, salarie.getCongesPayesAcquisAnneeN());
    }

    @Test
    void testGetJoursTravaillesAnneeNMoins1() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(10);
        assertEquals(10, salarie.getJoursTravaillesAnneeNMoins1());
    }

    @Test
    void testSetJoursTravaillesAnneeNMoins1() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(10);
        assertEquals(10, salarie.getJoursTravaillesAnneeNMoins1());
    }

    @Test
    void testGetCongesPayesRestantAnneeNMoins1() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setCongesPayesAcquisAnneeNMoins1(20);
        salarie.setCongesPayesPrisAnneeNMoins1(10);
        assertEquals(10, salarie.getCongesPayesRestantAnneeNMoins1());
    }

    @Test
    void testGetCongesPayesRestantAnneeN() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setCongesPayesAcquisAnneeN(20);
        salarie.setCongesPayesPrisAnneeN(10);
        assertEquals(10, salarie.getCongesPayesRestantAnneeN());
    }

    @Test
    void testGetCongesPayesPrisAnneeN() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setCongesPayesAcquisAnneeN(10);
        salarie.ajouteConge(LocalDate.now(), LocalDate.now().plusDays(3));
        assertEquals(3, salarie.getCongesPayesPrisAnneeN());
    }

    @Test
    void testGetCongesPayesPrisAnneeNMoins1() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setCongesPayesAcquisAnneeNMoins1(10);
        salarie.ajouteConge(LocalDate.now().minusYears(1), LocalDate.now().minusYears(1).plusDays(3));
        assertEquals(3, salarie.getCongesPayesPrisAnneeNMoins1());
    }

    @Test
    void testAConsommeTousSesJoursDeConges() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeN(15);
        salarie.setJoursTravaillesAnneeNMoins1(10);
        salarie.ajouteConge(LocalDate.now(), LocalDate.now().plusDays(15));
        assertTrue(salarie.aConsommeTousSesJoursDeConges(), "Le salarié a consommé tous ses jours de congés");

        salarie.setJoursTravaillesAnneeN(15);
        salarie.setJoursTravaillesAnneeNMoins1(10);
        salarie.ajouteConge(LocalDate.now(), LocalDate.now().plusDays(10));
        assertFalse(salarie.aConsommeTousSesJoursDeConges(), "Le salarié n'a pas consommé tous ses jours de congés");
    }

    @Test
    void testAConsommeTousSesJoursDeCongesAnneePrecedente() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeN(15);
        salarie.setJoursTravaillesAnneeNMoins1(10);
        salarie.ajouteConge(LocalDate.now().minusYears(1), LocalDate.now().minusYears(1).plusDays(10));
        assertTrue(salarie.aConsommeTousSesJoursDeCongesAnneePrecedente(), "Le salarié a consommé tous ses jours de congés de l'année précédente");

        salarie.ajouteConge(LocalDate.now().minusYears(1), LocalDate.now().minusYears(1).plusDays(5));
        assertFalse(salarie.aConsommeTousSesJoursDeCongesAnneePrecedente(), "Le salarié n'a pas consommé tous ses jours de congés de l'année précédente");
    }

    @Test
    void testAConsommeTousSesJoursDeCongesAnneePrecedenteEtActuelle() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeN(15);
        salarie.setJoursTravaillesAnneeNMoins1(10);
        salarie.ajouteConge(LocalDate.now().minusYears(1), LocalDate.now().minusYears(1).plusDays(10));
        salarie.ajouteConge(LocalDate.now(), LocalDate.now().plusDays(5));
        assertTrue(salarie.aConsommeTousSesJoursDeCongesAnneePrecedenteEtActuelle(), "Le salarié a consommé tous ses jours de congés de l'année précédente et actuelle");

        salarie.setJoursTravaillesAnneeN(15);
        salarie.setJoursTravaillesAnneeNMoins1(10);
        salarie.ajouteConge(LocalDate.now().minusYears(1), LocalDate.now().minusYears(1).plusDays(10));
        salarie.ajouteConge(LocalDate.now(), LocalDate.now().plusDays(10));
        assertFalse(salarie.aConsommeTousSesJoursDeCongesAnneePrecedenteEtActuelle(), "Le salarié n'a pas consommé tous ses jours de congés de l'année précédente et actuelle");
    }

    @Test
    void testAConsommeTousSesJoursDeCongesAnneePrecedenteEtActuelleEtIlResteDesJours() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeN(15);
        salarie.setJoursTravaillesAnneeNMoins1(10);
        salarie.ajouteConge(LocalDate.now().minusYears(1), LocalDate.now().minusYears(1).plusDays(10));
        salarie.ajouteConge(LocalDate.now(), LocalDate.now().plusDays(5));
        assertFalse(salarie.aConsommeTousSesJoursDeCongesAnneePrecedenteEtActuelleEtIlResteDesJours(), "Le salarié n'a pas consommé tous ses jours de congés de l'année précédente et actuelle");

        salarie.setJoursTravaillesAnneeN(15);
        salarie.setJoursTravaillesAnneeNMoins1(10);
        salarie.ajouteConge(LocalDate.now().minusYears(1), LocalDate.now().minusYears(1).plusDays(10));
        salarie.ajouteConge(LocalDate.now(), LocalDate.now().plusDays(10));
        assertFalse(salarie.aConsommeTousSesJoursDeCongesAnneePrecedenteEtActuelleEtIlResteDesJours(), "Le salarié n'a pas consommé tous ses jours de congés de l'année précédente et actuelle");
    }

    @Test
    void testAConsommeTousSesJoursDeCongesAnneePrecedenteEtActuelleEtIlNeRestePlusDeJours() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeN(15);
        salarie.setJoursTravaillesAnneeNMoins1(10);
        salarie.ajouteConge(LocalDate.now().minusYears(1), LocalDate.now().minusYears(1).plusDays(10));
        salarie.ajouteConge(LocalDate.now(), LocalDate.now().plusDays(10));
        assertTrue(salarie.aConsommeTousSesJoursDeCongesAnneePrecedenteEtActuelleEtIlNeRestePlusDeJours(), "Le salarié a consommé tous ses jours de congés de l'année précédente et actuelle et il ne reste plus de jours");
    }

    @Test
    void testALegalementDroitADesCongesPayes() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeN(15);
        assertTrue(salarie.aLegalementDroitADesCongesPayes(), "Le salarié devrait avoir droit à des congés payés");

        salarie.setJoursTravaillesAnneeN(10);
        assertTrue(salarie.aLegalementDroitADesCongesPayes(), "Le salarié devrait avoir droit à des congés payés");

        salarie.setJoursTravaillesAnneeN(5);
        assertFalse(salarie.aLegalementDroitADesCongesPayes(), "Le salarié ne devrait pas avoir droit à des congés payés");

        salarie.setJoursTravaillesAnneeNMoins1(15);
        assertTrue(salarie.aLegalementDroitADesCongesPayes(), "Le salarié devrait avoir droit à des congés payés");

        salarie.setJoursTravaillesAnneeNMoins1(10);
        assertTrue(salarie.aLegalementDroitADesCongesPayes(), "Le salarié devrait avoir droit à des congés payés");

        salarie.setJoursTravaillesAnneeNMoins1(5);
        assertFalse(salarie.aLegalementDroitADesCongesPayes(), "Le salarié ne devrait pas avoir droit à des congés payés");
    }

    @ParameterizedTest
    @CsvSource({
            "2022-11-01, 2022-11-10, 8",
            "2022-12-01, 2022-12-25, 21"
    })
    public void testCalculeJoursDeCongeDecomptesPourPlage(String startDate, String endDate, int expectedDays) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        int actualDays = new SalarieAideADomicile().calculeJoursDeCongeDecomptesPourPlage(start, end).size();
        assertEquals(expectedDays, actualDays);
    }
}