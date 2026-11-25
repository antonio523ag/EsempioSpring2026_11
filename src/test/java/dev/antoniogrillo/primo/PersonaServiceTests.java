package dev.antoniogrillo.primo;

import dev.antoniogrillo.primo.entity.Persona;
import dev.antoniogrillo.primo.service.def.PersonaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = PrimoApplication.class)
public class PersonaServiceTests {

    @Autowired
    private PersonaService personaService;

    @Test
    protected void testLogin(){
        assertThat(personaService.login("admin@admin.it","P4ssw0rd!1"))
                .extracting(Persona::getDataNascita)
                .isEqualTo(LocalDate.of(2000,1,1));
    }
}
