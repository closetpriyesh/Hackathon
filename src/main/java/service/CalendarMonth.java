package service;

import java.util.HashMap;
import java.util.Map;

public class CalendarMonth {

	private CalendarMonth() {
	}

	private static Map<String, Integer> monthMap = new HashMap<String, Integer>() {
		{
			put("Jan", 0);
			put("Feb", 1);
			put("Mar", 2);
			put("Apr", 3);
			put("May", 4);
			put("Jun", 5);
			put("Jul", 6);
			put("Aug", 7);
			put("Sep", 8);
			put("Oct", 9);
			put("Nov", 10);
			put("Dec", 11);
		}
	};

	public static Map<String, Integer> getMonthMap() {
		return monthMap;
	}
}
