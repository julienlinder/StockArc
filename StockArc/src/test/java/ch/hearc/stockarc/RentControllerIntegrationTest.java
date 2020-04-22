package ch.hearc.stockarc;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ch.hearc.stockarc.controller.RentController;
import ch.hearc.stockarc.model.Person;
import ch.hearc.stockarc.model.Rent;
import ch.hearc.stockarc.model.Tool;

@RunWith(SpringRunner.class)
@WebMvcTest(RentController.class)
public class RentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentController rentController;

    // tests
    @Test
    public void monTest1() throws Exception {
        // rent
        final List<Rent> rents;
        final List<Rent> rentsNotOver;
        final List<Tool> tools;
        final List<Person> people;

        // assertThat(0, is(not(1)));

        String expected = "Baeldung";
        String actual = "Baeldung";

        assertEquals(expected, actual);

        // mockMvc.perform(MockMvcRequestBuilders.get("/").andExpect(status().isOk())
        // .addExpect(model().attribute("rents"), equalTo(rents))
        // .addExpect(model().attribute("rentsNotOver"), equalTo(rentsNotOver))
        // .addExpect(model().attribute("tools"), equalTo(tools))
        // .addExpect(model().attribute("people"), equalTo(people)));
        // mockMvc.perform(get("/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        // registration

    }

}