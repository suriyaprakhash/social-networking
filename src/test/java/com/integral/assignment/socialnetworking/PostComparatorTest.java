package com.integral.assignment.socialnetworking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.integral.assignment.socialnetworking.model.Post;

@Deprecated
public class PostComparatorTest {

	@Test
	public void testPost() {
		Post p1= new Post("m1",new Date().getTime());
		Post p2= new Post("m2",new Date().getTime());
		Post p3= new Post("m3",new Date().getTime());
		Post p4= new Post("m4",new Date().getTime());
		
		List<Post> postList=new ArrayList<>();
		postList.add(p1);
		postList.add(p2);
		postList.add(p3);
		postList.add(p4);
		
		Collections.sort(postList, new PostCompartor());
	}
}
