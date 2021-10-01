package com.hk.test;

import java.util.List;
import java.util.Scanner;

import com.hk.daos.HkDao;
import com.hk.dtos.HkDto;

public class BoardMain {

	public static void main(String[] args) {
		HkDao dao = new HkDao();
//		
//		//글을 추가하자
////		HkDto dto = new HkDto();
////		dto.setId("hk");
////		dto.setTitle("두번째제목");
////		dto.setContent("두번째 내용");
////		boolean isS = dao.insertBoard(dto);
//		//생성자를 이용하자: 생성자 오버로딩
//		boolean isS = dao.insertBoard(new HkDto(0,"hk","세번쨰제목","세번째내용",null));
//		System.out.println("글추가 성공여부:"+isS);
//		
//		//글 삭제하기
//		boolean isSDel = dao.delBoard(2);
//		System.out.println("글삭제여부:"+isSDel);
//		
//		//글을 조회하는 기능을 쓰자!
//		List<HkDto>list = dao.getBoardList();
//		
//		
//		//결과출력하기
//		System.out.println("최종값 출력 내용");
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//			
//		}
//		
//		//글 수정하기
//		boolean isSUpdate = dao.updateBoard(new HkDto(11
//				,null,"제목수정","내용수정",null));
//		System.out.println("수정성공여부:"+isSUpdate);
		
		//글하나 조회하기
//		System.out.println("글하나 조회하기: 번호를 입력해라.");s
//		Scanner scan = new Scanner(System.in);
//		int seq = scan.nextInt();
//		HkDto dto2 = dao.getBoard(seq);
//		System.out.println(dto2);
		
		//게시판 프로그램 구현하기
		System.out.println("게시판입니다. [1.글목록 2.글상세조회 3.글추가 4.글수정 5.글삭제");
		Scanner scan = new Scanner(System.in);
		System.out.print("번호입력:");
		int selNum = scan.nextInt();
		HkDao dao2 = new HkDao();
		if(selNum ==1) {
			List<HkDto>lists = dao2.getBoardList();
			for(HkDto dto:lists) {
				System.out.println(dto.getSeq()
						//+"\t"+dto.get
						);
			}
		}else if(selNum ==2) {
			System.out.print("글번호입력:");
			int seq = scan.nextInt();
			HkDto dto = dao.getBoard(seq);
			System.out.println(dto);
		}else if(selNum ==3) {
			HkDto dto = new HkDto();
			System.out.println("작성자 이름:");
			dto.setId(scan.next());
			System.out.println("글 제목:");
			dto.setTitle(scan.next());
			System.out.println("글 내용:");
			dto.setContent(scan.next());
			boolean isS = dao.insertBoard(dto);
			System.out.println(isS?"글을 추가합니다.":"글 추가 실패");
		}else if(selNum ==4) {
			HkDto dto = new HkDto();
			System.out.println("작성자 이름:");
			dto.setId(scan.next());
			System.out.println("글 제목:");
			dto.setTitle(scan.next());
			System.out.println("글 내용:");
			dto.setContent(scan.next());
			boolean isS = dao.updateBoard(dto);
			System.out.println(isS?"글을 수정합니다.":"글 수정 실패");
		}else if(selNum ==5) {
//			System.out.println();
//			boolean isS = dao.delBoard(dto);
//			System.out.println(isS?"글을 삭제합니다.":"글 삭제 실패");
		}
	}

}
