package ch.hearc.stockarc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class StaticViewTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /*@Test
    public void getViewRentListAtHome() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(view().name("rent/list"));
    }*/

    @Test
    public void getViewPeopleListAtPeople_ThenOK() throws Exception {
        this.mockMvc.perform(get("/people")).andExpect(view().name("people/list"));
    }

    @Test
    public void getViewPeopleUniqueAtPeopleId_ThenKO() throws Exception {
        Assertions.assertThrows(NestedServletException.class, () -> {
            this.mockMvc.perform(get("/people/1")).andExpect(view().name("people/unique"));
        });
    }

}