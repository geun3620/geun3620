package com.hk.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.hk.dtos.CalDto;

public class Util {
	
	private String toDates;
	
	public void setToDates(String mDate) {
	    
		//mDate를 날짜형식으로 편집한다. yyyy-MM-dd hh:mm:ss
		String m=mDate.substring(0,4)+"-"	//year-
				+mDate.substring(4,6)+"-"	//year-month
				+mDate.substring(6,8)+" "	//year-month-date
				+mDate.substring(8,10)+":"	//year-month-date hh:
				+mDate.substring(10,12)+":00"; //year-month-date hh:mm:00
		//문자열을 --> date타입으로 변환
		Timestamp tm=Timestamp.valueOf(m);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy년MM월dd일 HH:mm");
		this.toDates=sdf.format(tm);
	}

	public String getToDates() {
		return toDates;
	}
	//한자리수를 두자리수로 변환하는 메서드
	   public static String isTwo(String s) {
	      return s.length()<2?"0"+s:s;
	   }
	   public static String fontColor(int dayOfWeek, int i){
			
			String color="";
			if((i+dayOfWeek-1)%7==0){//토요일
				color="blue";
			}else if((i+dayOfWeek-1)%7==1){ //일요일
				color="red";
			}
			else{
				color="black";
			}	
			return color;
		}

		//list에 mdate에서 일일과 달력에서의 i값과 비교해서 일치하면 제목 출력
		public static String getCalView(List<CalDto> list, int i){ 
			String d=Util.isTwo(i+"");// 5일 ---> 5 --> "05" 정수형의 숫자를 두자리 문자열로 변환	
			String titleList="";//"<p>title</p><p>title</p>"
			
			for(CalDto dto:list){
				// "202109281431"
				if(dto.getMdate().substring(6, 8).equals(d)){
					titleList+="<p class='ctitle'>"
							 +(dto.getCaltitle().length()>6?
								dto.getCaltitle().substring(0,6)+"..."
								:dto.getCaltitle())
							 +"</p>";  
				}
			}
			return titleList;
		}
}
