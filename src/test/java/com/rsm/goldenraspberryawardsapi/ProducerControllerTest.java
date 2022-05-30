package com.rsm.goldenraspberryawardsapi;

import com.rsm.goldenraspberryawardsapi.controllers.ProducerController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerControllerTest {

    private MockMvc mockMvc;
    private static final String PRODUCER_INTERVAL_BETWEEN_AWARDS = "/producer/interval-between-awards";

    @Autowired
    private ProducerController producerController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.producerController).build();
    }

    @Test
    public void Should_Get_Minimum_Award_Interval_Data_From_Winning_Producers() throws Exception {
        mockMvc.perform(get(PRODUCER_INTERVAL_BETWEEN_AWARDS)).andExpect(status().isOk())
                .andExpect(jsonPath("$.min[0].producer", is("Joel Silver")))
                .andExpect(jsonPath("$.min[0].interval", is(1)))
                .andExpect(jsonPath("$.min[0].previousWin", is(1990)))
                .andExpect(jsonPath("$.min[0].followingWin", is(1991)));
    }

    @Test
    public void Should_Get_Maximum_Award_Interval_Data_From_Winning_Producers() throws Exception {
        mockMvc.perform(get(PRODUCER_INTERVAL_BETWEEN_AWARDS)).andExpect(status().isOk())
                .andExpect(jsonPath("$.max[0].producer", is("Matthew Vaughn")))
                .andExpect(jsonPath("$.max[0].interval", is(13)))
                .andExpect(jsonPath("$.max[0].previousWin", is(2002)))
                .andExpect(jsonPath("$.max[0].followingWin", is(2015)));
    }
}
