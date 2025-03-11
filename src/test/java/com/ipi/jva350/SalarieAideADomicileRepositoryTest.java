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
        // Given
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
    void testFindByNom_CaseSensitive() {
        String nomSalarie = "Dupont";
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setNom(nomSalarie);
        repository.save(salarie);

        SalarieAideADomicile found = repository.findByNom("dupont");

        assertNull(found, "La recherche devrait être sensible à la casse");
    }

    @Test
    void testFindByNom_Null() {
        SalarieAideADomicile found = repository.findByNom(null);

        assertNull(found, "Aucun salarié ne devrait être trouvé avec un nom null");
    }
}