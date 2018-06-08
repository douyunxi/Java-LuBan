package luban;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.haige.luban.LuBanApplication;
import com.haige.luban.pojo.Cart;
import com.haige.luban.service.CartService;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.properties"})
@SpringBootTest(classes =LuBanApplication.class)
public class TestCart {
	
	@Autowired
	private CartService cartService;
	
	@Test
	public void addCart() {
		Cart cart = new Cart();
		cartService.addCart(cart);
	}
}
