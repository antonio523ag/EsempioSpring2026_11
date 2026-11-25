package dev.antoniogrillo.primo;

import dev.antoniogrillo.primo.dto.persona.request.LoginRequestDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@ContextConfiguration(classes = PrimoApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class PersonaControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    protected void testLogin() throws Exception {
        LoginRequestDTO l=new LoginRequestDTO("nome1.cognome1@email.it","P4ssw0rd!1");
        String json = mapper.writeValueAsString(l);
        RequestBuilder builder= MockMvcRequestBuilders.post("/persone/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        ResultActions chiamataEffettuata=mockMvc.perform(builder);
        ResultMatcher matcher= MockMvcResultMatchers.status().isOk();
        chiamataEffettuata.andExpect(matcher);
    }

    @Test
    protected void testLoginEmailErrata() throws Exception{
        mockMvc.perform(getLoginBuilder("nome1.cognome1email.it","P4ssw0rd!1"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    protected void testLoginPasswordErrata() throws Exception{
        mockMvc.perform(getLoginBuilder("nome1.cognome1@email.it","P4ssw0rd!"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    protected void testGetAll() throws Exception{
        RequestBuilder builder = MockMvcRequestBuilders.get("/persone/all");
        ResultActions chiamataEffettuata=mockMvc.perform(builder);
        ResultMatcher matcher1=MockMvcResultMatchers.status().is2xxSuccessful();
        ResultMatcher matcher2=MockMvcResultMatchers.jsonPath("$").isArray();
        ResultMatcher matcher3=MockMvcResultMatchers.jsonPath("$").isNotEmpty();
        ResultMatcher matcher4=MockMvcResultMatchers.jsonPath("$[0].nome").value("Nome1");
       chiamataEffettuata.andExpect(matcher1)
               .andExpect(matcher2)
               .andExpect(matcher3)
               .andExpect(matcher4)
               .andDo(print());
    }

    @Test
    protected void testWhoAmI() throws Exception{
        String token=mockMvc.perform(getLoginBuilder("admin@admin.it","P4ssw0rd!1"))
                .andReturn().getResponse().getHeader("Authorization");

        mockMvc.perform(MockMvcRequestBuilders.get("/persone/WhoAmI/Step3")
                .header("Authorization","Bearer "+token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("ADMIN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cognome").value("ADMIN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("admin@admin.it"))
                .andDo(print());
    }


    private RequestBuilder getLoginBuilder(String email,String password){
        return MockMvcRequestBuilders.post("/persone/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new LoginRequestDTO(email,password)));
    }



}
