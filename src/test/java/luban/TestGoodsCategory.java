package luban;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.haige.luban.LuBanApplication;
import com.haige.luban.pojo.GoodsCategory;
import com.haige.luban.service.GoodsCategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.properties"})
@SpringBootTest(classes =LuBanApplication.class)
public class TestGoodsCategory {
	
	@Autowired
	private GoodsCategoryService goodsCategoryService;
	
	@Test
	public void createGoods(){
		GoodsCategory category=new GoodsCategory();
		category.setName("胶水");
		category.setLevel(1);
		goodsCategoryService.addGoodsCategory(category);
	}
	
	@Test
	public void findAll() {
		goodsCategoryService.findAllGoodsCategory(0, 10);
	}
}
