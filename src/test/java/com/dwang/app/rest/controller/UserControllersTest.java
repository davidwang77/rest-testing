package com.dwang.app.rest.controller;

import com.dwang.app.rest.models.User;
import com.dwang.app.rest.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserControllers.class)
public class UserControllersTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    User user;

    @BeforeEach
    public void setup(){

        user = User.builder()
                .firstName("john")
                .lastName("doe")
                .age(25)
                .description("test")
                .occupation("engineer")
                .build();

    }

    //Post Controller
    @Test
    public void saveEmployeeTest() throws Exception{
        // precondition
        given(userService.saveUser(any(User.class))).willReturn(user);

        // action
        ResultActions response = mockMvc.perform(post("/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        // verify
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",
                        is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(user.getLastName())))
                .andExpect(jsonPath("$.age",
                        is(user.getAge())));
    }

    //Get Controller
    @Test
    public void getEmployeeTest() throws Exception{
        // precondition
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(User.builder().id(2L).firstName("Sam").lastName("Curran").build());
        given(userService.getUsers()).willReturn(userList);

        // action
        ResultActions response = mockMvc.perform(get("/users"));

        // verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(userList.size())));

    }

    //get by Id controller
//    @Test
//    public void getByIdEmployeeTest() throws Exception{
//        // precondition
//        given(userService.getEmployeeById(employee.getId())).willReturn(Optional.of(employee));
//
//        // action
//        ResultActions response = mockMvc.perform(get("/api/employees/{id}", employee.getId()));
//
//        // verify
//        response.andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
//                .andExpect(jsonPath("$.lastName", is(employee.getLastName())))
//                .andExpect(jsonPath("$.email", is(employee.getEmail())));
//
//    }
//
//
//    //Update employee
//    @Test
//    public void updateEmployeeTest() throws Exception{
//        // precondition
//        given(employeeService.getEmployeeById(employee.getId())).willReturn(Optional.of(employee));
//        employee.setFirstName("Max");
//        employee.setEmail("max@gmail.com");
//        given(employeeService.updateEmployee(employee,employee.getId())).willReturn(employee);
//
//        // action
//        ResultActions response = mockMvc.perform(put("/api/employees/{id}", employee.getId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(employee)));
//
//        // verify
//        response.andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
//                .andExpect(jsonPath("$.email", is(employee.getEmail())));
//    }
//
//
//    // delete employee
//    @Test
//    public void deleteEmployeeTest() throws Exception{
//        // precondition
//        willDoNothing().given(employeeService).deleteEmployee(employee.getId());
//
//        // action
//        ResultActions response = mockMvc.perform(delete("/api/employees/{id}", employee.getId()));
//
//        // then - verify the output
//        response.andExpect(status().isOk())
//                .andDo(print());
//    }
}
