
package exercise;

import exercise.fakedatabase.UserFacadeFake;
import exercise.realdatabase.UserFacadeRealDB;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Rihards
 */
public class UserFacadeTestIT extends UserFacadeTest {
     IUserFacade facade;
  
  //Override in a derived class to provide an alternative Facade for these tests
  @Override
  public IUserFacade makeUserFacade(){
    return new UserFacadeRealDB("pu_localDB");

  }

  @Before
  public void setup() {
     facade = makeUserFacade();
  }
  
  @Test
  public void authenticateOK(){
    //Given (in setup)
    //When
    LoginStatus res = facade.verifyUser("Jan", "abcde");
    //Then
    assertThat(res,is(LoginStatus.OK));
  }
  
  @Test
  public void authenticateValidUserWrongPW(){
    //Given (in setup)
    //When
    LoginStatus res = facade.verifyUser("Jan", "kfjdlsjaf");
    //Then
    assertThat(res,is(LoginStatus.INVALID_PASSWORD));
  }
  
  @Test
  public void authenticateNonExistingUser(){
    //Given (in setup)

    //When
    LoginStatus res = facade.verifyUser("xxxx", "kfjdlsjaf");
    
//Then
    assertThat(res,is(LoginStatus.UNKNOWN_USER));
  }
}
