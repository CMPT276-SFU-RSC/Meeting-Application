package group9.sfursmeetingapplication.repositoryTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import group9.sfursmeetingapplication.repositories.MediumRepository;
import group9.sfursmeetingapplication.models.Medium;
import java.util.List;

@DataJpaTest
public class MediumRepositoryTest {

    @Autowired
    private MediumRepository mediumRepository;

    @Test
    public void testFindByMid() {
        // Perform the findByMid operation
        List<Medium> mediums = mediumRepository.findBymid(1);
        assertEquals(0, mediums.size());

        Medium medium = new Medium(1,"Burnaby", false);
        mediumRepository.save(medium);

        mediums = mediumRepository.findBymid(1);
        assertEquals(1, mediums.size());
    }

    @Test
    public void testFindByPid() {
        // Perform the findByPid operation
        List<Medium> mediums = mediumRepository.findBypid(1);
        assertNotNull(mediums);
    }

}