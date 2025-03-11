package com.ipi.jva350;

import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SalarieAideADomicileRepositoryTest {

    @Autowired
    private SalarieAideADomicileRepository repository;

    @Test
    void testFindByNom_ExistingEmployee() {
        String nomSalarie = "Dupont";
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setNom(nomSalarie);
        repository.save(salarie);

        SalarieAideADomicile found = repository.findByNom(nomSalarie);

        assertNotNull(found, "Le salarié devrait être trouvé");
        assertEquals(nomSalarie, found.getNom(), "Les noms devraient correspondre");
    }

    @Test
    void testFindByNom_NonExistingEmployee() {
        SalarieAideADomicile found = repository.findByNom("Inconnu");
        assertNull(found, "Aucun salarié ne devrait être trouvé");
    }

    @Test
    void testPartCongesPrisTotauxAnneeNMoins1() {
        SalarieAideADomicile salarie1 = new SalarieAideADomicile();
        salarie1.setCongesPayesAcquisAnneeNMoins1(20);
        salarie1.setCongesPayesPrisAnneeNMoins1(10);
        repository.save(salarie1);

        SalarieAideADomicile salarie2 = new SalarieAideADomicile();
        salarie2.setCongesPayesAcquisAnneeNMoins1(30);
        salarie2.setCongesPayesPrisAnneeNMoins1(15);
        repository.save(salarie2);

        Double partCongesPris = repository.partCongesPrisTotauxAnneeNMoins1();

        assertNotNull(partCongesPris, "La part des congés pris ne doit pas être null");
        assertEquals(0.5, partCongesPris, 0.01, "La part des congés pris doit être de 0.5 (50%)");
    }
}