package com.hk.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.hk.config.SqlMapConfig;
import com.hk.dtos.LoginDto;

public class LoginDao extends SqlMapConfig{
	
private String namespace = "com.hk.login.";
	
	public LoginDto getLogin(String id, String password) {
		LoginDto dto = null;
		SqlSession sqlSession = null;
		
		try {
			Map<String, String> map = new HashMap<>();
			map.put("id", id);
			map.put("password", password);
			sqlSession = getSqlSessionFactory().openSession(false);
			dto = sqlSession.selectOne(namespace+"getLogin", map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return dto;
	}
	
	//회원 가입을 해보자
	public boolean insertUser(LoginDto dto) {
		int count  = 0;
		SqlSession sqlSession = null;
		
		try {
			sqlSession = getSqlSessionFactory().openSession(true);
			count = sqlSession.insert(namespace+"insertUser", dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return count>0?true:false;
	}
	
	//전체 회원을 조회해보자
	public List<LoginDto> getAllUserSelect(){
		List<LoginDto> list = new ArrayList<>();
		SqlSession sqlSession = null;
		
		try {
			sqlSession = getSqlSessionFactory().openSession(true);
			list = sqlSession.selectList(namespace+"getAllUserSelect", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return list;
	}
	
	//사용중인 회원 조회
	public List<LoginDto> getAllUser(){
		List<LoginDto> list = new ArrayList<>();
		SqlSession sqlSession = null;
		
		try {
			sqlSession = getSqlSessionFactory().openSession(true);
			list =  sqlSession.selectList(namespace+"",list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return list;
	}
	
	//회원 한명에 대한 상세 조회가 가능하게 하자
	
}
