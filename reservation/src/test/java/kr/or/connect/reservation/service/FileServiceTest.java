package kr.or.connect.reservation.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.connect.reservation.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class FileServiceTest {

	@Autowired
	private FileInfoService fileInfoService;
	
	@Test
	public void getFile() {	
		System.out.println(fileInfoService.getFileInfo(189));
	}
}
