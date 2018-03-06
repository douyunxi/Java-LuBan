package luban;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.haige.luban.LuBanApplication;
import com.haige.luban.dao.UserJpaDao;
import com.haige.luban.pojo.User;
import com.haige.luban.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.properties"})
@SpringBootTest(classes =LuBanApplication.class)
public class TestUser {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserJpaDao userJpaDao;
	
	@Test
	public void getUser() throws IOException {
		User user=userService.getUserById(Long.valueOf(2));
		System.out.println(user);
	}
}
