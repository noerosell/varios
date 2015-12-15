package useCases.UserWantsAuthenticate;

import Domain.*;
import Domain.authenticator.AuthenticationRepository;
import Domain.authenticator.Authenticator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by noe.rosell on 14/12/15.
 */
public class UserWantsAuthenticateTest {

    private UserRepository userRepository;
    private PermisionsRepository permisionsRepository;
    private AuthenticationRepository authenticationRepository;
    private UserWantsAuthenticate useCase;
    private UserWantsAuthenticateRequest mockedRequest;
    private Authenticator mockedAuthenticator;
    private User user;

    public static final String VALID_USERNAME = "user1";
    public static final String VALID_PASSWORD = "Password1";
    public static final String WRONG_PASSWORD = "password999";
    private boolean isAnAuthUser;

    @Before
    public void setUp()
    {
        userRepository=mock(UserRepository.class);
        permisionsRepository=mock(PermisionsRepository.class);
        authenticationRepository=mock(AuthenticationRepository.class);

        useCase=new UserWantsAuthenticate(userRepository,permisionsRepository,authenticationRepository);
        mockedAuthenticator=mock(Authenticator.class);
        user=mock(User.class);

    }

    @Test
    public void testUserWithRightCredentialsIsNotAuthenticated()
    {
        when(userRepository.getByLogin(VALID_USERNAME)).thenReturn(user);
        when(authenticationRepository.isEmpty()).thenReturn(true);
        when(mockedAuthenticator.authenticate(user,VALID_PASSWORD)).thenReturn(true);
        UserWantsAuthenticateRequest request=new UserWantsAuthenticateRequest(VALID_USERNAME,VALID_PASSWORD);
        UserWantsAuthenticateResponse response=useCase.execute(request, mockedAuthenticator);
        assertTrue(response.isAnAuthUser);
    }

    @Test
    public void testUserWithWrongCredentialsIsNotAuthenticated()
    {
        when(userRepository.getByLogin(VALID_USERNAME)).thenReturn(user);
        when(authenticationRepository.isEmpty()).thenReturn(true);
        when(mockedAuthenticator.authenticate(user,WRONG_PASSWORD)).thenReturn(false);
        UserWantsAuthenticateRequest request=new UserWantsAuthenticateRequest(VALID_USERNAME,WRONG_PASSWORD);
        UserWantsAuthenticateResponse response=useCase.execute(request, mockedAuthenticator);
        assertFalse(response.isAnAuthUser);
    }


}