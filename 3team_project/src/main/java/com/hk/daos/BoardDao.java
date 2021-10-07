package com.hk.daos;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.hk.dtos.BoardDto;
import com.hk.config.SqlMapConfig;

public class BoardDao extends SqlMapConfig {
	
	private String nameSpace="com.hk.board.";

	private static BoardDao boardDao;
	private BoardDao() {}
	public static BoardDao getBoardDao() {
		if(boardDao==null) {
			boardDao=new BoardDao();
		}
		return boardDao;
	}
	
	
	//1.글목록 조회:
		public List<BoardDto> getBoardList(String pnum){
			List<BoardDto> list=new ArrayList<BoardDto>();
			SqlSession sqlSession=null;
			
			try {
				SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
				sqlSession=sqlSessionFactory.openSession(true);
				
				list=sqlSession.selectList(nameSpace+"boardlist",pnum);
			} catch (Exception e) {
				System.out.println("JDBC실패:getBoardList():"+getClass());
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			
			return list;
		}
		
		//글목록의 페이지 수 구하기
		public int getPCount(){
			int pcount=0;
			SqlSession sqlSession=null;
			
			try {
				SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
				
				sqlSession=sqlSessionFactory.openSession(true);
				pcount=sqlSession.selectOne(nameSpace+"pcount");
			} catch (Exception e) {
				System.out.println("JDBC실패:getPCount():"+getClass());
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return pcount;
		}
		
		
		//2. 글 추가하기
		public boolean insertBoard(BoardDto dto) {
			int count=0;
			SqlSession sqlSession=null;
			
			try {
				sqlSession=getSqlSessionFactory().openSession(true);
				count=sqlSession.insert(nameSpace+"insertboard", dto );
			} catch (Exception e) {
				System.out.println("JDBC실패:insertBoard():"+getClass());
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return count>0?true:false;
		}
		
		
		//3.글 상세조회
		public BoardDto getBoard(int seq) {
			BoardDto dto=new BoardDto();
			SqlSession sqlSession=null;
			try {
				sqlSession=getSqlSessionFactory().openSession(true);
				dto=sqlSession.selectOne(nameSpace+"getboard", seq);
			} catch (Exception e) {
				System.out.println("JDBC실패:getBoard():"+getClass());
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return dto;
		}
		
		
		//4.글 수정하기
		public boolean updateBoard(BoardDto dto) {
			int count=0;
			SqlSession sqlSession=null;
			try {
				sqlSession=getSqlSessionFactory().openSession(true);
				count=sqlSession.update(nameSpace+"updateboard", dto);
			} catch (Exception e) {
				System.out.println("JDBC실패:updateBoard():"+getClass());
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return count>0?true:false;
		}
		
		
		//5. 글 삭제하기
		public boolean delBoard(int seq) {
			int count=0;
			SqlSession sqlSession=null;
			try {
				sqlSession=getSqlSessionFactory().openSession(true);
				count=sqlSession.delete(nameSpace+"delboard", seq);
			} catch (Exception e) {
				System.out.println("JDBC실패:delBoard():"+getClass());
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return count>0?true:false;
		}
		
		
		//6. 글 여러개 삭제하기
		public boolean mulDel(String[] seqs) {
			int count=0;
			SqlSession sqlSession=null;
			
			try {
				sqlSession=getSqlSessionFactory().openSession(false);
				for (int i = 0; i < seqs.length; i++) {
					String seq=seqs[i];
					sqlSession.delete(nameSpace+"delboard", seq);
				}
				count=1;
				sqlSession.commit();
			} catch (Exception e) {
				sqlSession.rollback();
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
			return count>0?true:false;
		}
		

}
