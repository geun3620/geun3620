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
	
	
	//1.글목록 조회: select문 - 결과는 여러행 반환 --> list를 반환
		public List<BoardDao> getBoardList(String pnum){
			List<BoardDao> list=new ArrayList<BoardDao>();
			SqlSession sqlSession=null;
			
			try {
				//SqlSessionFactory객체 구함
				SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
				
				//SqlSessionFactory객체로 부터 SqlSession객체를 구해온다.
				//이때 openSession(true)로 실행하면 autocommit-> true로 설정
				sqlSession=sqlSessionFactory.openSession(true);
				//selectList(쿼리id)실행하면 결과를 List로 반환해 준다.
				list=sqlSession.selectList(nameSpace+"boardlist", pnum);
			} catch (Exception e) {
				System.out.println("JDBC실패:getBoardList():"+getClass());
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return list;
		}
		
		
		//2.글추가하기: 페이지에서 입력된 값을 받아서 DB에 추가한다.
		//           insert문 실행: 반환값???결과값 없다.-->대신 추가된 행의 개수를 반환한다
		//           파라미터 정의: 
		public boolean insertBoard(BoardDto dto) {
			int count=0;//insert문에 의해 추가되는 행의 개수를 저장할 변수
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
			return count>0?true:false;//삼항연산자 활용
		}
		
		
		//3.글상세조회: select문, 파라미터 받기??? -->Dbeaver에서 쿼리 작성해보면 알아요
		//    반환타입: 글하나의 대한 정보 조회--> 1row를 반환 --> 1row를 저장하는 객체 DTO
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
		
		
		//4.글수정하기: update문, 파라미터 받기???
		// 수정할 컬럼: 제목, 내용, 작성일 
		// 파라미터받을 목록: seq,제목, 내용
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
		
		
		//5.글삭제하기: delete문, 파라미터 받기??? 
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
		
		//글 여러개 삭제하기: delete문 , 파라미터 받기 seq 여러개--> 배열
		//트랜젝션처리: 모두 성공하면 성공!! 하나라도 실패면 실패!! 처리하자
		//          setAutoCommit(), commit(), rollback() 활용
		//트랜젝션 처리 유형: 
		//       1. 요청 한번에  update문, insert문  실행 -> 작업의 수가 정해진 경우
		//       2. 요청 한번에  delete문 실행 -> 몇번 작업할지 정해지지 않은 경우
		//          --> 파라미터 값을 확인해서 몇번 delete문을 실행할지 결정
		//  --> batch개념: 동일한 여러 작업을 한번에 실행하는 개념
		//      a+b , a-b (X)      a+b  a+b  a+b (O)  --> 1+3 , 3+5, 2+10..
		public boolean mulDel(String[] seqs) {
			int count=0;
			SqlSession sqlSession=null;
			
			try {
				//openSession(false): autocommit을 false설정-> rollback을 가능하게  
				sqlSession=getSqlSessionFactory().openSession(false);
				for (int i = 0; i < seqs.length; i++) {
					String seq=seqs[i];
					sqlSession.delete(nameSpace+"delboard", seq);
				}
				count=1;
				sqlSession.commit();//commit실행: DB에 반영
			} catch (Exception e) {
				sqlSession.rollback();
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
			return count>0?true:false;
		}
		

}
