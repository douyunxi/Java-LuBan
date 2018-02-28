package luban;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haige.luban.LuBanApplication;
import com.haige.luban.pojo.Area;
import com.haige.luban.service.AreaService;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.properties"})
@SpringBootTest(classes =LuBanApplication.class)
public class TestArea {
	
	@Autowired
	private AreaService areaService;
	
	@Test
	public void genArea() throws IOException {
		File file = new File("d:/areaJson.txt");  
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode json=objectMapper.readTree(file);
		System.out.println(json);
		if(json.isArray()) {
			System.out.println(json.size());
			for(JsonNode node:json) {
				System.out.println(node.get("id").toString()+","+node.get("name").toString());
				Area province=new Area();
				province.setId(node.get("id").asLong());
				province.setName(node.get("name").toString());
				province.setLevel(1);
				areaService.addArea(province);
				if(node.get("cityList").size()>0) {
					for(JsonNode city:node.get("cityList")) {
						System.out.println(city.get("id").toString()+","+city.get("name").toString()+","+city.get("pid").toString());
						Area areaCity=new Area();
						areaCity.setId(city.get("id").asLong());
						areaCity.setName(city.get("name").toString());
						areaCity.setParentId(city.get("pid").asLong());
						areaCity.setLevel(2);
						areaService.addArea(areaCity);
						if(city.get("districtList").size()>0) {
							for(JsonNode district:city.get("districtList")) {
								System.out.println(district.get("id").toString()+","+district.get("name").toString()+","+district.get("pid").toString());
								Area areaDistrict=new Area();
								areaDistrict.setId(district.get("id").asLong());
								areaDistrict.setName(district.get("name").toString());
								areaDistrict.setParentId(district.get("pid").asLong());
								areaDistrict.setLevel(3);
								areaService.addArea(areaDistrict);
							}
						}
					}
				}
			}
		}
	}
}
