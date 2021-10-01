package com.hk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import com.hk.daos.CalDao;
import com.hk.dtos.CalDto;
import com.hk.dtos.LoginDto;
import com.hk.utils.Util;

@WebServlet("/CalController.do")
public class CalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger=Logger.getLogger(CalController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("controller에 요청");
		//요청의 대한 내용을 받는 코드
		String command=request.getParameter("command");
		
		//DB에 관련된 작업을 위한 객체 생성
		CalDao dao=new CalDao();
		
		//session객체 생성
		HttpSession session=request.getSession();
		
		if(command.equals("calendar")) {
			logger.info("요청command:calendar");
			if(session.getAttribute("ldto")==null) {
				response.sendRedirect("index.jsp?msg="+URLEncoder.encode("로그인이 필요합니다.", "utf-8"));
			}else {
				LoginDto dto=(LoginDto)session.getAttribute("ldto");
				
			   	String year=request.getParameter("year");
				String month=request.getParameter("month");
				
				//요청된 날짜가 없는 경우 --> 현재 날짜로 달력이 요청되는 경우(메인에서 처음 일정보기했을때)
				if(year==null) {
					Calendar cal=Calendar.getInstance();
					year=cal.get(Calendar.YEAR)+"";
					month=cal.get(Calendar.MONTH)+1+"";
				}
				
				//한달에 일일별 일정을 구하기 위한 파라미터 값: id, yyyyMM
				String id=dto.getId();
				String yyyyMM=year+Util.isTwo(month);//"202109"
				
				List<CalDto> list=dao.calBoardListView(id, yyyyMM);
				request.setAttribute("list", list);
				dispatch("calendar.jsp", request, response);
//				response.sendRedirect("calendar.jsp?year="+year+"&month="+month);
			}
		}else if(command.equals("calinsertform")) {
			logger.info("요청command:calinsertform");
//			String year=request.getParameter("year");
//			String month=request.getParameter("month");
//			String date=request.getParameter("date");
//			response.sendRedirect("calinsert.jsp?year="+year+"&month="+month+"&date="+date);
			if(session.getAttribute("ldto")==null) {//로그인이 안되어 있으면 로그인 페이지로 이동
				response.sendRedirect("index.jsp?msg="+URLEncoder.encode("로그인이 필요합니다.", "utf-8"));
			}else {
				dispatch("calinsert.jsp", request, response);				
			}
		}else if(command.equals("calinsert")) {
			logger.info("요청command:calinsert");
			String year=request.getParameter("year");
			String month=request.getParameter("month");
			String date=request.getParameter("date");
			String hour=request.getParameter("hour");
			String min=request.getParameter("min");
			//mdate는 12자로 생성해야 하므로 isTwo메서드를 이용해서 만들어준다.
			String mdate=year+Util.isTwo(month)+Util.isTwo(date)+Util.isTwo(hour)+Util.isTwo(min);
			String id=request.getParameter("id");
			String caltitle=request.getParameter("caltitle");
			String calcontent=request.getParameter("calcontent");
			
//			String y_m_d=request.getParameter("ymd");
//			String ymd=y_m_d.replaceAll("-", "");//2021-09-27 --> 20210927
//			String mdate2=ymd+isTwo(hour)+isTwo(min);
			
			boolean isS=dao.calInsert(new CalDto(0,id,caltitle,calcontent,mdate,null));
			if(isS) {
				response.sendRedirect("CalController.do?command=calendar&year="+year+"&month="+month);
			}else {
				response.sendRedirect("error.jsp?msg="+URLEncoder.encode("일정추가실패", "utf-8"));
			}
		}else if(command.equals("callist")) {
			
			if(session.getAttribute("ldto")==null) {
				response.sendRedirect("index.jsp");
			}else {
				//로그인중이니깐 세션에서 로그인 정보를 구한다.--> ID
				LoginDto ldto=(LoginDto)session.getAttribute("ldto");
				
				String year=request.getParameter("year");
				String month=request.getParameter("month");
				String date=request.getParameter("date");
				
				String id=ldto.getId();//ldto에서 id만 가져온다.
				String ymd=year+Util.isTwo(month)+Util.isTwo(date);//"20210927"
				
				List<CalDto>list=dao.getCalBoardList(id, ymd);
				
				request.setAttribute("list", list);
				dispatch("calboardlist.jsp", request, response);
//				response.sendRedirect("calboardlist.jsp");
			}
			
			
		}else if(command.equals("muldel")) {//일정 삭제하기
			//같은 name속성의 이름으로 value값 여러개 전달시 받는 방법: cseq[3,4,5,6,7,87]
			String[] cseqs=request.getParameterValues("cseq");
			
			//year,month,date는 일정목록으로 응답하기 위해 필요함
			String year=request.getParameter("year");
			String month=request.getParameter("month");
			String date=request.getParameter("date");
			boolean isS=dao.mulDel(cseqs);
			if(isS) {
				//글을 삭제하고 해당 일정목록으로 돌아가야함--> year, month, date값이 필요
				response.sendRedirect("CalController.do?command=callist"
						+"&year="+year+"&month="+month+"&date="+date);
			}else {
				response.sendRedirect("error.jsp?msg="
									+URLEncoder.encode("글삭제실패", "utf-8"));
			}
		}else if(command.equals("caldetail")) {//일정상세내용보기
			int cseq=Integer.parseInt(request.getParameter("cseq"));
			CalDto dto=dao.calDetail(cseq);
			
			request.setAttribute("dto", dto);
			dispatch("caldetail.jsp", request, response);
		}else if(command.equals("calupdateform")) {//일정수정폼이동
			int cseq=Integer.parseInt(request.getParameter("cseq"));
			CalDto dto=dao.calDetail(cseq);
			
			request.setAttribute("dto", dto);
			dispatch("calupdate.jsp", request, response);
		}else if(command.equals("calupdate")) {//일정수정하기
			int cseq=Integer.parseInt(request.getParameter("cseq"));
			String year=request.getParameter("year");
			String month=request.getParameter("month");
			String date=request.getParameter("date");
			String hour=request.getParameter("hour");
			String min=request.getParameter("min");
			//mdate는 12자로 생성해야 하므로 isTwo메서드를 이용해서 만들어준다.
			String mdate=year+Util.isTwo(month)+Util.isTwo(date)+Util.isTwo(hour)+Util.isTwo(min);
			
			String caltitle=request.getParameter("caltitle");
			String calcontent=request.getParameter("calcontent");
			
			boolean isS=dao.calUpdate(new CalDto(cseq, null, caltitle, calcontent, mdate, null));
			if(isS) {
				//글을 수정하고 해당 일정내용으로 돌아가고, 일정내용에서 목록으로 돌아갈때 필요한 값
				//                                         --> year, month, date값이 필요
				response.sendRedirect("CalController.do?command=caldetail"
						+"&cseq="+cseq+"&year="+year+"&month="+month+"&date="+date);
			}else {
				response.sendRedirect("error.jsp?msg="
									+URLEncoder.encode("글수정실패", "utf-8"));
			}
		}else if(command.equals("calCount")) {
			String year=request.getParameter("year");
			String month=request.getParameter("month");
			String date=request.getParameter("date");
			
			String yyyyMMdd=year+Util.isTwo(month)+Util.isTwo(date);//"20210923" 8자리
			String id=((LoginDto)session.getAttribute("ldto")).getId();//로그인 id
			
			int count=dao.calListCount(id, yyyyMMdd);
			logger.info("일정개수:"+count);
			//화면으로 출력하기--->$.ajax메서드에 success: function(obj){} -> obj가 받는다
			PrintWriter pw=response.getWriter();
			pw.print(count);
		}
		
	}//doGet()메서드 끝

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//forward 메서드 구현하기
	public void dispatch(String url, HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		RequestDispatcher dispatch=request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

//	//한자릿수를 두자릿수로 변환하는 메서드
//	public String isTwo(String s) {
//		return s.length()<2?"0"+s:s;
//	}
}




