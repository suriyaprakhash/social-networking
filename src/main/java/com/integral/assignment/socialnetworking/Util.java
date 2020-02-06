package com.integral.assignment.socialnetworking;

import java.util.Date;

/**
 * This class has some of the utils for time converion
 * 
 * @author suriya
 *
 */
public class Util {

	public static long getTimeDiffInSec(long time) {
		long diff = new Date().getTime() - time;
		return diff / 1000 % 60;
	}

	public static long getTimeDiffInMin(long time) {
		long diff = new Date().getTime() - time;
		return diff / (60 * 1000) % 60;
	}

	public static long getTimeDiffInHrs(long time) {
		long diff = new Date().getTime() - time;
		return diff / (60 * 60 * 1000);
	}
}
