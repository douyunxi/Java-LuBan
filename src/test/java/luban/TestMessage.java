package luban;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.haige.luban.LuBanApplication;
import com.haige.luban.pojo.User;
import com.haige.luban.service.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.properties"})
@SpringBootTest(classes =LuBanApplication.class)
public class TestMessage {
	
	@Autowired
	private MessageService messageService;
	
	
	@Test
	public void find() {
		User user=new User();
		user.setId(Long.valueOf(67));
		messageService.getMessagesByReceiver(user);
	}
}
