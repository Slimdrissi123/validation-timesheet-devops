package tn.esprit.spring.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        // Stubbing example for retrieveAllUsers method
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "John", "Doe", new Date(), Role.CHEF_DEPARTEMENT));
        users.add(new User(2L, "Alice", "Smith", new Date(), Role.INGENIEUR));

        when(userRepository.findAll()).thenReturn(users);
    }

    @Test
    public void testRetrieveAllUsers() {
        List<User> users = userService.retrieveAllUsers();
        assertEquals(2, users.size());
    }

    // Similarly, you can write tests for other methods like addUser, deleteUser, updateUser, and retrieveUser
}
