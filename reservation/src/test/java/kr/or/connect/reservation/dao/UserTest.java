package kr.or.connect.reservation.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.dto.User;
import kr.or.connect.reservation.dto.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class UserTest {
	@Autowired
	UserDao dao;
	@Autowired
	UserRoleDao userRoleDao;
	
	@Test
	public void getUserTest() {
		User user = dao.getUserByEmail("dominsang@connect.co.kr");
		System.out.println(user);
	}
	
	@Test(expected = EmptyResultDataAccessException.class)
	public void EmptyTest() {
		User user = dao.getUserByEmail("dominsang@connect.co.1");
		
	}
	
	@Test
	public void show() {
		List<UserRole> list =userRoleDao.getRolesByEmail("carami@connect.co.kr");
		for(UserRole r:list)
			System.out.println(r);
	}
}
