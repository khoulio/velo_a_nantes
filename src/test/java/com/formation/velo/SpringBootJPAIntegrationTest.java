package com.formation.velo;

import com.formation.velo.controllers.UserController;
import com.formation.velo.model.User;
import com.formation.velo.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VeloApplication.class)
//@Sql({"classpath:data.sql"})
@Profile(value = "test")
@AutoConfigureMockMvc

public class SpringBootJPAIntegrationTest {

    @Autowired
    private UserRepository userRepository;


    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }



    @Test
    public void givenPersonsEntityRepository_whenSaveAndRetreiveEntity_thenOK() {

        //Given
        User user1 = User.builder().surname("Julie").name("Dupont").build();
        User user2 = User.builder().surname("Marie").name("Dalle").build();
        userRepository.save(user1);
        userRepository.save(user2);


        List<User> people = userRepository.findAll();
        assertNotNull(people);
        assertEquals(3, people.size());

    }




    @Test
    public void setTimeZone() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("senegal"));
        assertEquals(calendar.getTimeZone(), TimeZone.getTimeZone("GMT"));

    }


}
