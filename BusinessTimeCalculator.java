package com.test.time;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;

public class BusinessTimeCalculator {

	public LocalDateTime getBusinessDateTime(Timestamp timestamp) {
		LocalDateTime localDateTime = timestamp.toLocalDateTime(); 
		return getBusinessDateTime(localDateTime);
	}
	
	public LocalDateTime getBusinessDateTime(LocalDateTime localDateTime) {
		
		System.out.println("localDateTime.getHour()= " + localDateTime.getHour());
		if(localDateTime.getHour() < 9 ||  localDateTime.getHour() > 17) {
			if(localDateTime.getHour() < 9) {
				localDateTime = localDateTime.minusDays(1);
			}
			localDateTime = localDateTime.withHour(17).withMinute(0).withSecond(0);
		}
		
		if(localDateTime.getDayOfWeek().equals(DayOfWeek.SATURDAY) || 
				localDateTime.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			localDateTime = localDateTime.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
		}
		System.out.println(localDateTime);
		return localDateTime;
	}
	
	
	
	public static void main(String[] args) {
		/*BusinessTimeCalculator btc = new BusinessTimeCalculator();
		java.util.Date date = new java.util.Date();
		Timestamp ts = new Timestamp(date.getTime());
		System.out.println(ts + "\n" + "********");
		LocalDateTime businessTime = btc.getBusinessDateTime(ts);
		System.out.println("Business Time- " + businessTime);*/
		
		LocalDateTime ldt = LocalDateTime.parse("2016-09-09T17:00:00.755");
		Duration difference = Duration.between(ldt, LocalDateTime.now());
		System.out.println(difference.toMinutes());
		System.out.println(Duration.ZERO);
//		Duration.
//		Period p = Period.between(ldt, endDateExclusive)
//		System.out.println(difference);
	}
	
	private Duration calculateTimeDifference(LocalDateTime startTime, LocalDateTime endTime) {
//		Duration.
		// if startDate = endDate then simple subtraction
		// else (17.00-startDateTime) + (endDateTime - 9.00) + (8*daysBetween(startTime, endTime))
		Duration duration = null;
		if(startTime.equals(endTime)) {
			return Duration.ZERO;
		} else if(startTime.toLocalDate().equals(endTime.toLocalDate())) {
			duration = Duration.between(startTime, endTime);
			return duration;
		} else {
			duration = Duration.between(startTime, endTime);
			long dayDifference = duration.toDays(); 
		}
		return duration;
	}
}
