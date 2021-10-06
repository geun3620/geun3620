package com.hk.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.hk.config.SqlMapConfig;
import com.hk.dtos.CalDto;

public class CalDao extends SqlMapConfig{

	private String namespace = "com.hk.calboard.";
	
	public boolean calInsert(CalDto dto) {
		int count = 0;
		SqlSession sqlSession = null;
		
		try {
			sqlSession = getSqlSessionFactory().openSession(true);
			count = sqlSession.insert(namespace+"calInsert", dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return count>0?true:false;
	}
	
	public List<CalDto> getCalBoardList(String id, String ymd){
		List<CalDto> list =  null;
		SqlSession sqlSession = null;
		
		try {
			sqlSession = getSqlSessionFactory().openSession(true);
			//map을 사용할떄: 1. dto에 파라미터 이름이 존재하지 않으면서 맴퍼로 전달할 개수도 2개이상이고
			//			   2. 동적쿼리에 파라미터 전달시 사용한다.
			Map<String, String> map = new HashMap<>();
			map.put("id", id);
			map.put("ymd", ymd);
			list = sqlSession.selectList(namespace+"getCalBoardList",map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return list;
	}
	
	public boolean mulDel(String[] cseqs) {
		int count =0;
		SqlSession sqlSession = null;
		
		try {
			sqlSession = getSqlSessionFactory().openSession(true);
			Map<String, String[]>map = new HashMap<>();
			map.put("cseqs", cseqs);
			count = sqlSession.delete(namespace+"mulDel",map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return count>0?true:false;
	}
	
	public CalDto calDetail(int cseq) {
		CalDto dto = null;
		SqlSession sqlSession = null;
		
		try {
			sqlSession = getSqlSessionFactory().openSession(true);
			dto = sqlSession.selectOne(namespace+"calDetail", cseq);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return dto;
	}
	
	public boolean calUpdate(CalDto dto) {
		int count = 0;
		SqlSession sqlSession = null;
		
		try {
			sqlSession = getSqlSessionFactory().openSession(true);
			count = sqlSession.update(namespace+"calUpdate", dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return count>0?true:false;
	}
	
	public List<CalDto> calBoardListView(String id,String yyyyMM){
		List<CalDto> list = null;
		SqlSession sqlSession = null;
		
		try {
			Map<String , String>map = new HashMap<>();
			map.put("id", id);
			map.put("yyyyMM", yyyyMM);
			sqlSession = getSqlSessionFactory().openSession(true);
			list = sqlSession.selectList(namespace+"calBoardListView",map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return list;
	}
	
	public int calListCount(String id, String yyyyMMdd){
		int count=0;
		SqlSession sqlSession=null;
		
		try {
			Map<String, String>map=new HashMap<>();
			map.put("id", id);
			map.put("yyyyMMdd", yyyyMMdd);
			sqlSession=getSqlSessionFactory().openSession(true);
			count=sqlSession.selectOne(namespace+"calListCount", map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count;
	}
}
