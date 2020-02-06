package com.integral.assignment.socialnetworking;

import java.util.Comparator;
import com.integral.assignment.socialnetworking.model.Post;

/**
 * This is the compartor class to compare the Post based on post time.
 * 
 * @author suriya
 *
 */
public class PostCompartor implements Comparator<Post> {

	@Override
	public int compare(Post p1, Post p2) {
//		if (p1.getDate().compareTo(p2.getDate()) == -1) {
//			return -1;
//		} else if (p1.getDate().compareTo(p2.getDate()) == 0) {
//			return 0;
//		}else
//			return 1;
		if(p1.getDate() < p2.getDate()) {
			return 1;
		}
		return -1;
	}

}
