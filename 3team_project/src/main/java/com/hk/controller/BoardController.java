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

	/*
	public Cookie getCookie(String cookieName, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(cookieName)) {
				cookie = cookies[i];
			}
		}
		return cookie;
	}*/

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		//1단계:command값 받기(어떤 요청인지 확인하기 위해)
		String command = request.getParameter("command");
		System.out.println("요청내용:" + command);

		
		BoardDao dao = BoardDao.getBoardDao();


		//3단계:command의 값을 확인하여 분기 실행(요청에 대한 처리)
		if (command.equals("main")) {
			response.sendRedirect("index.jsp");
			
		} else if (command.equals("boardlist")) {
			List<BoardDto> list=dao.getBoardList();
			request.setAttribute("list", list);
			RequestDispatcher disptch = request.getRequestDispatcher("boardlist.jsp");
			disptch.forward(request, response);

		} else if (command.equals("insertform")) {
			response.sendRedirect("insertboard.jsp");
			
		} else if (command.equals("insertboard")) {
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			boolean isS = dao.insertBoard(new BoardDto(id, title, content));

			if (isS) {
				response.sendRedirect("BoardController.do?command=boardlist");
			} else {
				request.setAttribute("msg", "글 추가 실패");
				RequestDispatcher disptch = request.getRequestDispatcher("error.jsp");
				disptch.forward(request, response);
			}
			
		} else if (command.equals("detailboard")) {
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

				
				String jsTag = "<script type='text/javascript'>" + "alert('삭제할 게시물을 선택하세요.');"
						+ "location.href='BoardController.do?command=boardlist';" + "</script>";
				PrintWriter pw = response.getWriter();
				pw.print(jsTag);
				
				
				/*
				request.setAttribute("msg", "글 삭제 실패");
				RequestDispatcher disptch = request.getRequestDispatcher("error.jsp");
				disptch.forward(request, response); 
				*/
				
			}
		} else if (command.equals("updateform")) {
			String seq = request.getParameter("seq");

			BoardDto dto = dao.getBoard(Integer.parseInt(seq));

			request.setAttribute("dto", dto);
			RequestDispatcher disptch = request.getRequestDispatcher("updateboard.jsp");
			disptch.forward(request, response);
			
		} else if (command.equals("updateboard")) {
			String seq = request.getParameter("seq");

			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int sseq = Integer.parseInt(seq);

			boolean isS = dao.updateBoard(new BoardDto(sseq, title, content));

			if (isS) {
				response.sendRedirect("BoardController.do?command=detailboard&seq=" + seq);
			} else {
				request.setAttribute("msg", "글 수정 실패");
				RequestDispatcher disptch = request.getRequestDispatcher("error.jsp");
				disptch.forward(request, response);
			}
			
			
			//여기 정근이오빠가 한거 데려가려고 잠깐 임시로 만든거임
		} else if (command.equals("chooseMain")) {
		RequestDispatcher disptch = request.getRequestDispatcher("chooseMain.jsp");
		disptch.forward(request, response);
		
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
