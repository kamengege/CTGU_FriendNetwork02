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


	//1.��Ҫ����JdbcTemplate ʹ��ע��@Resource����set����
	@Resource
	private JdbcTemplate jdbcTemplate;

	public List<User> findAll() {
		String sql="select * from user";
		//return jdbcTemplate.queryForList(sql,User.class);
		return jdbcTemplate.query(sql, new UserRowMapper());
	}
	
	//�ڶ���ʵ��RowMapper�ö���,ֱ��ʹ�������ڲ���ķ�ʽ
	class UserRowMapper implements RowMapper<User>{
		//��һ������ rs  �ڶ����Ǵ���һ����ʼ Ĭ����0
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
			System.out.println("�����Ϊ0");
			return null;
		}
	}
	
	//����
	public void updateUser(User user){
		String sql="update user set name=? where age=?";
		jdbcTemplate.update(sql, user);
	}
	//ɾ��
	public void deleteUser(String age){
		String sql="delete from user where age=?";
		jdbcTemplate.update(sql, new Object[]{age});
	}
	//ע��
	public void registerUser(User user){
		String sql="insert into user(name,age)"
				+ " values(?,?)";
		jdbcTemplate.update(sql,new Object[]{
				user.getName(),user.getAge()
		});
	}
	
	
	public static void main(String[] args) {
		//ʵ��������
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
