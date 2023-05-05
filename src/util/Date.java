package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Date {
	
	public static void main(String[] args) {
		System.out.println(parseToDateSql("2023-10-25"));
	}

	public static java.sql.Date parseToDateSql(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date parsedDate;
		try {
			parsedDate = format.parse(date);
			return new java.sql.Date(parsedDate.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
