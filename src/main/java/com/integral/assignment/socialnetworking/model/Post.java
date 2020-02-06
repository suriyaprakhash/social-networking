package com.integral.assignment.socialnetworking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * This is the actual post itself. Here {@code date} is the time when the
 * {@code msg} is posted.
 * 
 * @author suriya
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	private String msg;
	private long date;
}
