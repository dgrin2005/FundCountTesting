package ru.trustsoft.FundCountTesting.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import ru.trustsoft.FundCountTesting.Config.FixerApiProperties;
import ru.trustsoft.FundCountTesting.Dto.CurrencyRateDto;
import ru.trustsoft.FundCountTesting.Service.CurrencyRateService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyRateService currencyRateService;

    @Test
    public void getCurrencyProfit() throws Exception {
        CurrencyRateDto request = new CurrencyRateDto("2019-01-01", 1000);
        String requestJson = OBJECT_MAPPER.writeValueAsString(request);
         mockMvc.perform(post("/fixerapi/getCurrencyProfit").content(requestJson).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getBaseCurrency() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/fixerapi/getBaseCurrency"))
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Assert.assertEquals(FixerApiProperties.base, contentAsString);
    }

    @Test
    public void getCurrentCurrency() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/fixerapi/getCurrentCurrency"))
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Assert.assertEquals(FixerApiProperties.cur, contentAsString);
    }
}