package com.hk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hk.daos.BoardDao;
import com.hk.dtos.BoardDto;

@WebServlet("/BoardController.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 원하는 쿠키 구하는 메서드
	public Cookie getCookie(String cookieName, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(cookieName)) {
				cookie = cookies[i];
			}
		}
		return cookie;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 1단계:command값 받기(어떤 요청인지 확인하기 위해)
		String command = request.getParameter("command");
		System.out.println("요청내용:" + command);

		// 2단계:dao객체 생성하기(DB에 연결해서 작업하기 위해 준비)
		BoardDao dao = BoardDao.getBoardDao();// 싱글턴 패턴으로 구현해보자

		// 3단계:command의 값을 확인하여 분기 실행(요청에 대한 처리)
		if (command.equals("main")) {// index페이지로 이동
			response.sendRedirect("index.jsp");
			
		} else if (command.equals("boardlist")) {// 글목록을 요청했을 경우

			// 요청된 페이지 번호 파라미터 받기
			String pnum = request.getParameter("pnum");

			
			// 특별한 페이지 요청이 없는 경우 1페이지로 셋팅
			if (pnum == null) {
				pnum = getCookie("pnum", request).getValue();
			} else {
				Cookie cookie = new Cookie("pnum", pnum);// 쿠키생성
				cookie.setMaxAge(10 * 60);// 쿠키의 유효기간
				response.addCookie(cookie);// 브라우저로 생성한 쿠키를 추가
			} 

			// 총 페이지 개수 구하기(5개씩 보여줄때 필요한 페이지수)
			int pcount = dao.getPCount();
			

			// 5단계:실행결과 담기(dto,list 등등)
			List<BoardDto> list = dao.getBoardList(pnum);// 글목록 구하기

			// 6단계:스코프에 객체 담기(key, value)
			request.setAttribute("list", list);
			// int기본타입 --wrapper클래스(Integer)--> Object참조타입
			request.setAttribute("pcount", pcount);

			// 7단계:페이지 이동
			// pageContext.forward("boardlist.jsp");
			RequestDispatcher dispatch = request.getRequestDispatcher("boardlist.jsp");
			dispatch.forward(request, response);

			// 스코프에 저장된 객체를 전달하지 못한다.
			// response.sendRedirect("boardlist.jsp");
		} else if (command.equals("insertform")) {// 글작성 폼이동
			response.sendRedirect("insertboard.jsp");
			
		} else if (command.equals("insertboard")) {// 작성된 글 추가하기
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			boolean isS = dao.insertBoard(new BoardDto(id, title, content));

			if (isS) {
				// pageContext.forward("HkController.do?command=boardlist");
				// RequestDispatcher
				// disptch=request.getRequestDispatcher("HkController.do?command=boardlist");
				// disptch.forward(request, response);
				response.sendRedirect("BoardController.do?command=boardlist");
			} else {
				request.setAttribute("msg", "글추가실패");
				// pageContext.forward("error.jsp");
				RequestDispatcher disptch = request.getRequestDispatcher("error.jsp");
				disptch.forward(request, response);

				// request.getRequestDispatcher("error.jsp").forward(request,
				// response);
			}
		} else if (command.equals("detailboard")) {// 상세보기
			String seq = request.getParameter("seq");
			int sseq = Integer.parseInt(seq);
			BoardDto dto = dao.getBoard(sseq);

			request.setAttribute("dto", dto);
			// pageContext.forward("detailboard.jsp");
			RequestDispatcher disptch = request.getRequestDispatcher("detailboard.jsp");
			disptch.forward(request, response);

		} else if (command.equals("muldel")) {
			String[] seqs = request.getParameterValues("chk");

			boolean isS = dao.mulDel(seqs);

			if (isS) {
				String jsTag = "<script type='text/javascript'>" + "alert('글을 삭제합니다.');"
						+ "location.href='BoardController.do?command=boardlist';" + "</script>";
				PrintWriter pw = response.getWriter();
				pw.print(jsTag);
			} else {
				request.setAttribute("msg", "글삭제 실패");
				// pageContext.forward("error.jsp");
				RequestDispatcher disptch = request.getRequestDispatcher("error.jsp");
				disptch.forward(request, response);
			}
		} else if (command.equals("updateform")) {// 수정폼 이동(수정할 내용 입력폼)
			String seq = request.getParameter("seq");

			BoardDto dto = dao.getBoard(Integer.parseInt(seq));

			request.setAttribute("dto", dto);
			// pageContext.forward("updateboard.jsp");
			RequestDispatcher disptch = request.getRequestDispatcher("updateboard.jsp");
			disptch.forward(request, response);
			
		} else if (command.equals("updateboard")) {// 수정하기(DB반영)
			String seq = request.getParameter("seq");

			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int sseq = Integer.parseInt(seq);

			boolean isS = dao.updateBoard(new BoardDto(sseq, title, content));

			if (isS) {
				response.sendRedirect("BoardController.do?command=detailboard&seq=" + seq);
			} else {
				request.setAttribute("msg", "글수정실패");
				// pageContext.forward("error.jsp");
				RequestDispatcher disptch = request.getRequestDispatcher("error.jsp");
				disptch.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
