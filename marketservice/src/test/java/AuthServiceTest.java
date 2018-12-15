import com.ctfs.qloudMarket.market_service.auth_service.pojo.AuthUser;
import com.ctfs.qloudMarket.market_service.auth_service.service.AuthService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/12/11
 * Time: 17:40
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class AuthServiceTest {
    private static Logger logger = LoggerFactory.getLogger(AuthServiceTest.class);
    private AuthService authService=new  AuthService();
    @Test
    public void createUser(){
        AuthUser authUser=new AuthUser();
        authUser.setId("unit_test1");
        authUser.setPassword("123456");
        try {
           logger.info("{}",authService.createUserOnAuth(authUser));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void userLogin(){
        AuthUser authUser=new AuthUser();
        authUser.setId("unit_test1");
        authUser.setPassword("123456");
        try {
            logger.info("{}",authService.getLoginToken(authUser));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void getJWT(){
//        AuthUser authUser=new AuthUser();
//        authUser.setId("unit_test1");
//        authUser.setPassword("123456");
        try {
            logger.info("{}",authService.queryToken("YWMt45-yXP2yEeintlt7kRtzLwAAAWfEOSpC0VJuQRjQnwr2PlptOUtbhC6G8CY"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
