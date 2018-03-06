package luban;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.haige.luban.LuBanApplication;
import com.haige.luban.pojo.Area;
import com.haige.luban.pojo.Task;
import com.haige.luban.pojo.User;
import com.haige.luban.service.TaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.properties"})
@SpringBootTest(classes =LuBanApplication.class)
public class TestTask {
	
	@Autowired
	private TaskService taskService;
	
	@Test
	public void createTask(){
		Task task=new Task();
		task.setTitle("某小区项目");
		task.setContent("位于某校小区的墙纸项目");
		Area province=new Area();
		province.setId(Long.valueOf(110000));
		province.setName("北京市");
		Area city=new Area();
		city.setId(Long.valueOf(110101));
		city.setName("东城区");
		task.setProvince(province);
		task.setCity(city);
		task.setAddress("东四十条");
		User employer=new User();
		employer.setId(Long.valueOf(2));
		employer.setRealName("林伟");
		task.setEmployer(employer);
		taskService.addTask(task);
	}
	
	@Test
	public void findAll() {
		taskService.findAllTasks(0, 10);
	}
}
