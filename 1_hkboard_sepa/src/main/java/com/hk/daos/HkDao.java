package com.hk.daos;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.dtos.HkDto;

public class HkDao {
	
	public HkDao() {
		//OracleDriver.class --> java 컴파일된 파일
		// 강제 객체 생성 -->오라클 로딩 실행
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1단계:드라이버 로딩 성공!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("1단계: 드라이버 롣이 실패이다..!");
		}
	}
	
	public void close(ResultSet rs, PreparedStatement psmt, Connection conn) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(psmt!=null) {
				psmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
			System.out.println("6단계 : DB닫기 성공!!");
		} catch (SQLException e) {
			System.out.println("6단계 : DB닫기 실패!!");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
		//2단계: DB연결
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hk";
		String password = "hk";
		return DriverManager.getConnection(url, user, password);
	}
	
	//1. 글 목록조회: select문 - 결과는 여러행을 반환한다. --> list를 반환.
	public List<HkDto> getBoardList(){
		List<HkDto> list = new ArrayList<HkDto>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT SEQ, ID, TITLE, CONTENT, REGDATE "
				+ " FROM HKBOARD "
				+ " ORDER BY REGDATE DESC ";
				
		
		try {
			conn = getConnection();
			System.out.println("2단계 : DB연결 성공!");
			psmt = conn.prepareStatement(sql);
			System.out.println("3단계 : 쿼리준비성공!");
			rs = psmt.executeQuery();//select문 실행할 때 사용함.
			System.out.println("4단계: 쿼리실행성공!!");
			while(rs.next()) { //rs[row,row,row,row,row..]
				HkDto dto = new HkDto();//필통
				//DB에서 검색된 결과를 java환경에 맞게 형변환하여 DTO에 저장한다.
				dto.setSeq(rs.getInt(1));//row[seq,id,title,content,regdate]
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				list.add(dto);
				System.out.println(dto);
			}
			System.out.println("5단계: DB결과 받기 성공!!");
			
			
		} catch (SQLException e) {
			System.out.println("JDBC실패: getBoardList():"+getClass());
			e.printStackTrace();
		}
		finally {
			close(rs, psmt, conn);
		}
		
		return list;
	}
	
	//2. 글 추가하기: 페이지에서 입력된 값을 받아서 DB에 추가한다.
	//			   insert문 실행: 반환값??? 없다.-->대신 추가된 행의 개수를 반환한다.
	// 			   파라미터 정의: 
	public boolean insertBoard(HkDto dto) { //hkDto--> id,title,content 담을 수 있음.
		int count = 0; //insert문에 의해 추가되는 행의 개수를 저장할 변수
		
		Connection conn = null;
		//Statement stmt = null; -> ?를 사용할 수 없다.
		PreparedStatement psmt = null;
		
		String sql = " INSERT INTO HKBOARD "
				+ " VALUES(HKBOARD_SEQ.NEXTVAL,? ,? ,? ,SYSDATE) ";
		
		try {
			conn = getConnection();
			System.out.println("2단계 : DB연결성공");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			System.out.println("3단계 : 쿼리준비성공");
			//inset,update,delete문에서든 executeUpdate()사용-->이유는 테이블을 수정하는거니깐
			//										반환값은-->수정된 row의 개수를 반환
			//select문은 executeQuery()->결과가 있어서 씀
			count = psmt.executeUpdate();
			System.out.println("4단계 : 쿼리실행성공");
		} catch (SQLException e) {
			System.out.println("JDBC실패:insertBoard()"+getClass());
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count>0?true:false;//삼항연산자 활용
	}
	
	//3. 글 상세조회: select문, 파라미터 받기??? --->Dbeaver에서 쿼리 작성해보면 안다.
	//		반환타입: 글하나에 대한 정보를 조회-> 1row를 반환해야함->1row를 저장하는 객체 DTO
	public HkDto getBoard(int seq) {
		HkDto dto = new HkDto();
		
		Connection conn = null;//연결객체 저장할 변수
		PreparedStatement psmt = null;//쿼리를 준비할 변수
		ResultSet rs = null;//DB의결과를 저장할 변수
		
		String sql = " SELECT SEQ,ID,TITLE,CONTENT,REGDATE "
				+ " FROM HKBOARD WHERE seq = ? ";
		
		try {
			conn=getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			rs = psmt.executeQuery();
			while(rs.next()) {
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
			}
			//while문이 종료된 후 이렇게 된다. -> HkDto[seq,id,title,contnet,regdate]
		} catch (SQLException e) {
			System.out.println("JDBC실페: getBoard():"+getClass());
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		
		return dto;
	}
	//4. 글 수정하기: update문, 파라미터 받기??? 
	//수정할 컬럼: 제목, 내용, 작성일
	//파라미터 받을 목록: seq, 제목, 내용
	public boolean updateBoard(HkDto dto) {
		int count=0;
		Connection conn = null;
		PreparedStatement psmt = null;
		
		String sql = " UPDATE HKBOARD "
				+ " SET TITLE=?, CONTENT=?, REGDATE=SYSDATE "
				+ " WHERE SEQ=? ";
		
		try {
			conn = getConnection();//2단계
			
			psmt = conn.prepareStatement(sql);//3단계
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getSeq());//3단계 완료
			
			count = psmt.executeUpdate();//4단계: 쿼리 실행 및 테이블의 수정된 행의 개수를 반환
			
			//5단계는 생략한다--> 쿼리 싱행 결과가 없기 때문에d
		} catch (SQLException e) {
			System.out.println("JDBC실페: updateBoard():"+getClass());
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		
		return count>0?true:false;
	}
	
	//5. 글 삭제하기: delete문, 파라미터 받기???
	public boolean delBoard(int seq) {
		int count=0;
		Connection conn = null;
		PreparedStatement psmt = null;
		
		String sql = " DELETE FROM HKBOARD WHERE SEQ=? ";
		
		try {
			conn = getConnection();//2단계: DB연결
			
			psmt=conn.prepareStatement(sql);//3단계: 쿼리준비
			psmt.setInt(1, seq);
			
			count = psmt.executeUpdate();//4단계: 쿼리 실행
			
			//5단계: 쿼리결과 받기--> 생략
			
			
		} catch (SQLException e) {
			System.out.println("JDBC실페: delBoard():"+getClass());
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		
		
		return count>0?true:false;
	}
}
