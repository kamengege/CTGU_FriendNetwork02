package com.tedu.ctgu.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tedu.ctgu.pojo.User;
@Repository
public class UserDaoTest{
	
	
	public UserDaoTest() {
		super();
	}


	//1.需要引入JdbcTemplate 使用注解@Resource或者set方法
	@Resource
	private JdbcTemplate jdbcTemplate;

	public List<User> findAll() {
		String sql="select * from user";
		//return jdbcTemplate.queryForList(sql,User.class);
		return jdbcTemplate.query(sql, new UserRowMapper());
	}
	
	//第二种实现RowMapper让对象,直接使用匿名内部类的方式
	class UserRowMapper implements RowMapper<User>{
		//第一个参数 rs  第二个是从哪一个开始 默认是0
		public User mapRow(ResultSet arg0, int arg1) throws SQLException {
			User u=new User();
			u.setName(arg0.getString("name"));
			u.setAge(arg0.getString("age"));
			return u;
		}
		
	}
	
	public User findById(String age) {
		String sql="select * from user where age=?";
		try {
			return jdbcTemplate.queryForObject
					(sql, new Object[]{age}, new UserRowMapper());
		} catch (EmptyResultDataAccessException e) {
			System.out.println("结果集为0");
			return null;
		}
	}
	
	//更新
	public void updateUser(User user){
		String sql="update user set name=? where age=?";
		jdbcTemplate.update(sql, user);
	}
	//删除
	public void deleteUser(String age){
		String sql="delete from user where age=?";
		jdbcTemplate.update(sql, new Object[]{age});
	}
	//注册
	public void registerUser(User user){
		String sql="insert into user(name,age)"
				+ " values(?,?)";
		jdbcTemplate.update(sql,new Object[]{
				user.getName(),user.getAge()
		});
	}
	
	
	public static void main(String[] args) {
		//实例化容器
		ApplicationContext ac=new 
				ClassPathXmlApplicationContext(
						"com/tedu/ctgu/source/applicationContext.xml");
		UserDaoTest dao=ac.getBean("userDaoTest",
						UserDaoTest.class);
		User u=dao.findById("33");
//		User u1=new User();
//		u1.setName("lidachui");
//		u1.setAge("22");
//		dao.registerUser(u1);
		System.out.println(u);
	}
	
	
	
	
	
	

}
