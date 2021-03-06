package com.whitesoft.pinmap.tests.unit.services;

import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.repositories.UsersRepository;
import com.whitesoft.pinmap.services.UserServiceImpl;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import com.whitesoft.pinmap.tests.TestDataFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by borisbondarenko on 21.12.15.
 *
 * Unit tests for {@link UserServiceImpl}.
 *
 * @author brzzbr
 */
public class UserServiceImplTest {

    // class to test
    protected UserServiceImpl userService = new UserServiceImpl();

    // dependencies
    protected UsersRepository usersRepository;

    @Before
    public void setup() {

        usersRepository = Mockito.mock(UsersRepository.class);
        ReflectionTestUtils.setField(userService, "usersRepository", usersRepository);
    }

    /**
     * Tests correct retrievement of user by login
     */
    @Test
    public void getUser() {

        // Assert
        User testUser = TestDataFactory.getValidTestUser();
        Mockito.when(usersRepository.findByUsername(testUser.getUsername())).thenReturn(testUser);

        // Act
        User user = userService.getUser(testUser.getUsername());

        // Arrange
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(user).isNotNull();
        softAssertions.assertThat(user.getUsername()).isEqualTo(testUser.getUsername());
        softAssertions.assertThat(user.getPassword()).isEqualTo(testUser.getPassword());
        softAssertions.assertAll();
    }

    /**
     * Tests null-result for incorrect login
     */
    @Test
    public void getUserWithNullResult() {

        // Assert
        String testLogin = "testLogin";
        Mockito.when(usersRepository.findByUsername(testLogin)).thenReturn(null);

        // Act
        User user = userService.getUser(testLogin);

        // Arrange
        assertThat(user).isNull();
    }

}
