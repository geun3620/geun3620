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
	
	
	//1.�۸�� ��ȸ: select�� - ����� ������ ��ȯ --> list�� ��ȯ
		public List<BoardDao> getBoardList(String pnum){
			List<BoardDao> list=new ArrayList<BoardDao>();
			SqlSession sqlSession=null;
			
			try {
				//SqlSessionFactory��ü ����
				SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
				
				//SqlSessionFactory��ü�� ���� SqlSession��ü�� ���ؿ´�.
				//�̶� openSession(true)�� �����ϸ� autocommit-> true�� ����
				sqlSession=sqlSessionFactory.openSession(true);
				//selectList(����id)�����ϸ� ����� List�� ��ȯ�� �ش�.
				list=sqlSession.selectList(nameSpace+"boardlist", pnum);
			} catch (Exception e) {
				System.out.println("JDBC����:getBoardList():"+getClass());
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return list;
		}
		
		
		//2.���߰��ϱ�: ���������� �Էµ� ���� �޾Ƽ� DB�� �߰��Ѵ�.
		//           insert�� ����: ��ȯ��???����� ����.-->��� �߰��� ���� ������ ��ȯ�Ѵ�
		//           �Ķ���� ����: 
		public boolean insertBoard(BoardDto dto) {
			int count=0;//insert���� ���� �߰��Ǵ� ���� ������ ������ ����
			SqlSession sqlSession=null;
			
			try {
				sqlSession=getSqlSessionFactory().openSession(true);
				count=sqlSession.insert(nameSpace+"insertboard", dto );
			} catch (Exception e) {
				System.out.println("JDBC����:insertBoard():"+getClass());
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return count>0?true:false;//���׿����� Ȱ��
		}
		
		
		//3.�ۻ���ȸ: select��, �Ķ���� �ޱ�??? -->Dbeaver���� ���� �ۼ��غ��� �˾ƿ�
		//    ��ȯŸ��: ���ϳ��� ���� ���� ��ȸ--> 1row�� ��ȯ --> 1row�� �����ϴ� ��ü DTO
		public BoardDto getBoard(int seq) {
			BoardDto dto=new BoardDto();
			SqlSession sqlSession=null;
			try {
				sqlSession=getSqlSessionFactory().openSession(true);
				dto=sqlSession.selectOne(nameSpace+"getboard", seq);
			} catch (Exception e) {
				System.out.println("JDBC����:getBoard():"+getClass());
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return dto;
		}
		
		
		//4.�ۼ����ϱ�: update��, �Ķ���� �ޱ�???
		// ������ �÷�: ����, ����, �ۼ��� 
		// �Ķ���͹��� ���: seq,����, ����
		public boolean updateBoard(BoardDto dto) {
			int count=0;
			SqlSession sqlSession=null;
			try {
				sqlSession=getSqlSessionFactory().openSession(true);
				count=sqlSession.update(nameSpace+"updateboard", dto);
			} catch (Exception e) {
				System.out.println("JDBC����:updateBoard():"+getClass());
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return count>0?true:false;
		}
		
		
		//5.�ۻ����ϱ�: delete��, �Ķ���� �ޱ�??? 
		public boolean delBoard(int seq) {
			int count=0;
			SqlSession sqlSession=null;
			try {
				sqlSession=getSqlSessionFactory().openSession(true);
				count=sqlSession.delete(nameSpace+"delboard", seq);
			} catch (Exception e) {
				System.out.println("JDBC����:delBoard():"+getClass());
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return count>0?true:false;
		}
		
		//�� ������ �����ϱ�: delete�� , �Ķ���� �ޱ� seq ������--> �迭
		//Ʈ������ó��: ��� �����ϸ� ����!! �ϳ��� ���и� ����!! ó������
		//          setAutoCommit(), commit(), rollback() Ȱ��
		//Ʈ������ ó�� ����: 
		//       1. ��û �ѹ���  update��, insert��  ���� -> �۾��� ���� ������ ���
		//       2. ��û �ѹ���  delete�� ���� -> ��� �۾����� �������� ���� ���
		//          --> �Ķ���� ���� Ȯ���ؼ� ��� delete���� �������� ����
		//  --> batch����: ������ ���� �۾��� �ѹ��� �����ϴ� ����
		//      a+b , a-b (X)      a+b  a+b  a+b (O)  --> 1+3 , 3+5, 2+10..
		public boolean mulDel(String[] seqs) {
			int count=0;
			SqlSession sqlSession=null;
			
			try {
				//openSession(false): autocommit�� false����-> rollback�� �����ϰ�  
				sqlSession=getSqlSessionFactory().openSession(false);
				for (int i = 0; i < seqs.length; i++) {
					String seq=seqs[i];
					sqlSession.delete(nameSpace+"delboard", seq);
				}
				count=1;
				sqlSession.commit();//commit����: DB�� �ݿ�
			} catch (Exception e) {
				sqlSession.rollback();
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
			return count>0?true:false;
		}
		

}
