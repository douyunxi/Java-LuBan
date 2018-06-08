package luban;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.haige.luban.LuBanApplication;
import com.haige.luban.enums.EnumGoodsStatus;
import com.haige.luban.pojo.Goods;
import com.haige.luban.pojo.GoodsCategory;
import com.haige.luban.pojo.Supplier;
import com.haige.luban.service.GoodsService;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.properties"})
@SpringBootTest(classes =LuBanApplication.class)
public class TestGoods {
	
	@Autowired
	private GoodsService goodsService;
	
	@Test
	public void createGoods(){
		Goods goods=new Goods();
		goods.setName("测试商品");
		goods.setDescription("测试商品");
		goods.setPrice(new BigDecimal(100));
		goods.setCreateTime(new Date());
		goods.setUpdateTime(new Date());
		goods.setStatus(EnumGoodsStatus.NORMAL);
		GoodsCategory category=new GoodsCategory();
		category.setName("墙纸");
		category.setLevel(1);
		goods.setCategory(category);
		Supplier supplier=new Supplier();
		supplier.setName("某某工厂");
		supplier.setMobile("12345678901");
		goods.setSupplier(supplier);
		goodsService.addGoods(goods);
	}
	
	@Test
	public void findAll() {
		goodsService.findAllGoods(0, 10);
	}
}
