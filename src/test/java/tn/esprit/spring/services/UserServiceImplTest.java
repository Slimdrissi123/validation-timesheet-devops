package tn.esprit.spring.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;



    @Test
    public void testAddUser() {
        User user = new User(1L, "John", "Doe", new Date(), Role.CHEF_DEPARTEMENT);
        when(userRepository.save(user)).thenReturn(user);

        User addedUser = userService.addUser(user);
        assertNotNull(addedUser);
        assertEquals(user.getFirstName(), addedUser.getFirstName());
        assertEquals(user.getLastName(), addedUser.getLastName());
        assertEquals(user.getDateNaissance(), addedUser.getDateNaissance());
        assertEquals(user.getRole(), addedUser.getRole());
    }

    @Test
    public void testRetrieveUser() {
        // Stubbing example for retrieveUser method
        User user = new User(1L, "John", "Doe", new Date(), Role.CHEF_DEPARTEMENT);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User retrievedUser = userService.retrieveUser(String.valueOf(1L));
        assertNotNull(retrievedUser);
        assertEquals(user.getLastName(), retrievedUser.getLastName());
        assertEquals(user.getFirstName(), retrievedUser.getFirstName());
    }


 




    @Test
    public void testDeleteUser() {
        Long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);
        userService.deleteUser(String.valueOf(userId));
        verify(userRepository, times(1)).deleteById(userId);
    }
}
