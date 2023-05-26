package fines_payment_manager.Controller;

import fines_payment_manager.Command.AddDriverCommand;
import fines_payment_manager.Command.CommandExecutor;
import fines_payment_manager.Command.DeleteDriverCommand;
import fines_payment_manager.Command.UpdateDriverCommand;
import fines_payment_manager.Model.Driver;
import fines_payment_manager.Service.DriverService;
import fines_payment_manager.Service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DriverController.class)
class DriverControllerTest {

    @MockBean
    private CommandExecutor commandExecutor;
    @MockBean
    private AddDriverCommand addDriverCommand;
    @MockBean
    private UpdateDriverCommand updateDriverCommand;
    @MockBean
    private DeleteDriverCommand deleteDriverCommand;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DriverService driverService;
    @MockBean
    private UserService userService;

    @Test
    void whenGetDriver_thenReturnDriver() throws Exception {
        // given
        String email = "police1@gmail.com";
        String password = "cG9saWNlMQ==";
        int id = 1;
        Driver driver = new Driver(1, "Mihai Curac", "Iuliu Maniu 1", "143243253241", "5020523524353");
        given(userService.checkUser(email, password)).willReturn(1);
        given(driverService.getDriverStr(id)).willReturn(driver.toString());

        // when + then
        mockMvc.perform(get("/{email}/{password}/drivers/{id}", email, password, id))
                .andExpect(status().isOk())
                .andExpect(content().string(driver.toString()));
    }

    @Test
    void whenGetDriverWithInvalidLogin_thenReturnLoginFailedMessage() throws Exception {
        // given
        String email = "police1@gmail.com";
        String password = "wrong";
        int id = 1;
        given(userService.checkUser(email, password)).willReturn(0);

        // when + then
        mockMvc.perform(get("/{email}/{password}/drivers/{id}", email, password, id))
                .andExpect(status().isOk())
                .andExpect(content().string("Login failed. You need a valid police employee account to access this resource."));
    }

    @Test
    void whenGetDriverWithNonExistingId_thenReturnNotFoundMessage() throws Exception {
        // given
        String email = "police1@gmail.com";
        String password = "cG9saWNlMQ==";
        int id = 100;
        given(userService.checkUser(email, password)).willReturn(1);
        given(driverService.getDriverStr(id)).willReturn("Driver with id 100 not found");

        // when + then
        mockMvc.perform(get("/{email}/{password}/drivers/{id}", email, password, id))
                .andExpect(status().isOk())
                .andExpect(content().string("Driver with id 100 not found"));
    }

}