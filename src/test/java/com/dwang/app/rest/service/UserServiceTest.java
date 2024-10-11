package com.dwang.app.rest.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.dwang.app.rest.models.User;
import com.dwang.app.rest.repo.UserRepo;
import com.dwang.app.rest.service.UserService;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    private User user;


    @BeforeEach
    public void setup(){

        user = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Cena")
                .build();

    }

    @Test
    public void saveUserTest(){
        // precondition
        given(userRepo.save(user)).willReturn(user);

        //action
        User savedUser = userService.saveUser(user);

        // verify the output
        System.out.println(savedUser);
        assertThat(savedUser).isNotNull();
    }

//    @Test
//    public void getUserById(){
//        // precondition
//        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));
//
//        // action
//        Employee existingEmployee = employeeService.getEmployeeById(employee.getId()).get();
//
//        // verify
//        System.out.println(existingEmployee);
//        assertThat(existingEmployee).isNotNull();
//
//    }


    @Test
    public void getAllUsers(){
        User user1 = User.builder()
                .id(2L)
                .firstName("Sam")
                .lastName("Curran")
                .build();

        // precondition
        given(userRepo.findAll()).willReturn(List.of(user,user1));

        // action
        List<User> userList = userService.getUsers();

        // verify
        System.out.println(userList);
        assertThat(userList).isNotNull();
        assertThat(userList.size()).isGreaterThan(1);
    }

//    @Test
//    @Order(4)
//    public void updateEmployee(){
//
//        // precondition
//        given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));
//        employee.setEmail("max@gmail.com");
//        employee.setFirstName("Max");
//        given(employeeRepository.save(employee)).willReturn(employee);
//
//        // action
//        Employee updatedEmployee = employeeService.updateEmployee(employee,employee.getId());
//
//        // verify
//        System.out.println(updatedEmployee);
//        assertThat(updatedEmployee.getEmail()).isEqualTo("max@gmail.com");
//        assertThat(updatedEmployee.getFirstName()).isEqualTo("Max");
//    }
//
//    @Test
//    @Order(5)
//    public void deleteEmployee(){
//
//        // precondition
//        willDoNothing().given(employeeRepository).deleteById(employee.getId());
//
//        // action
//        employeeService.deleteEmployee(employee.getId());
//
//        // verify
//        verify(employeeRepository, times(1)).deleteById(employee.getId());
//    }
}
