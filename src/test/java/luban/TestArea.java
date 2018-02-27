package luban;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haige.luban.pojo.Area;
import com.haige.luban.service.AreaService;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application-dev.properties"})
@SpringBootTest
public class TestArea {
	
	@Autowired
	private AreaService areaService;
	
	@Test
	public void genArea() {
		String strArea="";
		ObjectMapper mapper = new ObjectMapper();
		//mapper.readValue(src, Area.class);
		Area area=new Area();
		areaService.addArea(area);
	}
}
