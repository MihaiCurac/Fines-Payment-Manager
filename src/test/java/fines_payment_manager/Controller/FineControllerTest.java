package fines_payment_manager.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fines_payment_manager.Command.*;
import fines_payment_manager.Model.Driver;
import fines_payment_manager.Model.Fine;
import fines_payment_manager.Service.FineService;
import fines_payment_manager.Service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(FineController.class)
class FineControllerTest {

    @MockBean
    private CommandExecutor commandExecutor;
    @MockBean
    private AddFineCommand addFineCommand;
    @MockBean
    private UpdateFineCommand updateFineCommand;
    @MockBean
    private DeleteFineCommand deleteFineCommand;
    @MockBean
    private RegisterPaidFineCommand registerPaidFineCommand;
    @MockBean
    private FineService fineService;
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenAddFineWithValidCredentialsAndValidFine_thenReturnSuccessMessage() throws Exception {
        // given
        String email = "police@mail.com";
        String password = "1234";
        int driverId = 1;
        Driver driver = new Driver(1, "Mihai Curac", "Iuliu Maniu 1", "143243253241", "5020523524353");
        Fine fine = new Fine(5, driver, null, "Speeding", 100.0, "unpaid");
        given(userService.checkUser(email, password)).willReturn(1);

        ArgumentCaptor<AddFineCommand> addFineCommandArgumentCaptor = ArgumentCaptor.forClass(AddFineCommand.class);
        // when + then
        mockMvc.perform(post("/{email}/{password}/drivers/{driver_id}/fines", email, password, driverId)
                        .contentType(APPLICATION_JSON)
                        .content(asJsonString(fine)))
                .andExpect(status().isOk())
                .andExpect(content().string("Fine added successfully"));

        verify(commandExecutor).execute(addFineCommandArgumentCaptor.capture());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenAddFineWithInvalidLogin_thenReturnLoginFailedMessage() throws Exception {
        // given
        String email = "post1@gmail.com";
        String password = "cG9zdDE=";
        int driverId = 1;
        Driver driver = new Driver(1, "Mihai Curac", "Iuliu Maniu 1", "143243253241", "5020523524353");
        Fine fine = new Fine(5, driver, null, "Speeding", 100.0, "unpaid");
        given(userService.checkUser(email, password)).willReturn(0);

        mockMvc.perform(post("/{email}/{password}/drivers/{driver_id}/fines", email, password, driverId)
                        .contentType(APPLICATION_JSON)
                        .content(asJsonString(fine)))
                .andExpect(status().isOk())
                .andExpect(content().string("Login failed. You need a valid police employee account to access this resource."));
    }
}