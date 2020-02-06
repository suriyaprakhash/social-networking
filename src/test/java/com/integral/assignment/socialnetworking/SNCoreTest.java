package com.integral.assignment.socialnetworking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.integral.assignment.socialnetworking.model.Post;

public class SNCoreTest {
	
	@BeforeAll
	public static void init() {
		System.out.println("before all");
	}
	
	@Test
	public void testGetMemory() {
		assertTrue(SNCore.getMemory() !=null);
	}
	
	@Test
	public void testPublisMsg() {
		//testPublish
		int res = SNCore.publishMsg("Alicer", "msg1");
		assertEquals(1, res);
		List<Post> postList=SNCore.getMemory().getTimelineMap().get("Alicer");
		assertEquals("msg1",postList.get(postList.size()-1).getMsg());
	}
	
	@Test
	public void testAliceFollowBobCharlie() {
		int folRes1=SNCore.follow("Alicem", "Bobm");
		assertEquals(1, folRes1);
		
		int folRes2=SNCore.follow("Alicem", "Charliem");
		assertEquals(1, folRes2);
		
		Set<String> followingSet=SNCore.getMemory().getFollowMap().get("Alicem");
		assertTrue(followingSet.contains("Bobm"));
		assertTrue(followingSet.contains("Charliem"));
	}
	
	@Test
	public void testCurUsrViewOtherTimeline() throws InterruptedException {
		// alice publish msg1
		int res1 = SNCore.publishMsg("Alicet", "a-msg1");
		assertEquals(1, res1);
		
		Thread.sleep(1000);
		// bob publish msg1
		int res2 = SNCore.publishMsg("Bobt", "b-msg1");
		assertEquals(1, res2);
		
		Thread.sleep(1000);
		// bob publish msg1
		int res3 = SNCore.publishMsg("Bobt", "b-msg2");
		assertEquals(1, res3);
		
		List<Post> postList=SNCore.viewTimeline("Alicet", "Bobt");
		assertEquals("b-msg2",postList.get(0).getMsg());
		assertEquals(2,postList.size());
	}
	
	@Test
	public void testSameUserAfterAliceFollowingBobTimelineOrder1() throws InterruptedException {
		
		// alice publish msg1
		int res1 = SNCore.publishMsg("Alice1", "a-msg1");
		assertEquals(1, res1);
		
		Thread.sleep(1000);
		// alice publish msg2
		int res2 = SNCore.publishMsg("Alice1", "a-msg2");
		assertEquals(1, res2);
		
		Thread.sleep(1000);
		int res3 = SNCore.publishMsg("Alice1", "a-msg3");
		assertEquals(1, res3);
		
		Thread.sleep(1000);
		// bob publish msg1
		int res4 = SNCore.publishMsg("Bob1", "b-msg1");
		assertEquals(1, res4);
		

		// alice follow bob
		int folRes1=SNCore.follow("Alice1", "Bob1");
		assertEquals(1, folRes1);
		
		
		List<Post> postList=SNCore.viewTimeline("Alice1", "Alice1");
		assertEquals("b-msg1",postList.get(0).getMsg());
	}
	
	@Test
	public void testSameUserAfterAliceFollowingBobTimelineOrder2() throws InterruptedException {
		
		//Map<String, List<Post>> t=snCore.memory.getTimelineMap();
		
		// alice publish msg1
		int res1 = SNCore.publishMsg("Alices", "a-msg1");
		assertEquals(1, res1);
		
		Thread.sleep(1000);
		// bob publish msg1
		int res4 = SNCore.publishMsg("Boby", "b-msg1");
		assertEquals(1, res4);
		
		Thread.sleep(1000);
		// alice publish msg2
		int res2 = SNCore.publishMsg("Alices", "a-msg2");
		assertEquals(1, res2);
		
		Thread.sleep(1000);
		int res3 = SNCore.publishMsg("Alices", "a-msg3");
		assertEquals(1, res3);
		
		// alice follow bob
		int folRes1=SNCore.follow("Alices", "Boby");
		assertEquals(1, folRes1);
		
		
		List<Post> postList=SNCore.viewTimeline("Alices", "Alices");
		assertEquals("a-msg3",postList.get(0).getMsg());
	}
	
	
	// Test cases
	// Feature: Publishing
	@Test
	public void testAlicePublishMsgSuccessAndViewTimeline() {
		//testPublish
		int res = SNCore.publishMsg("Alice", "I love the weather today");
		assertEquals(1, res);
		
		//test publish success
		List<Post> postList=SNCore.viewTimeline("Alice", "Alice");
		String lastMessagePublished = postList.get(0).getMsg();
		assertEquals( "I love the weather today", lastMessagePublished);
	}
	
	// Feature: Timeline
	@Test
	public void testAliceViewBobTimeline() {
		//testPublish
		int res1 = SNCore.publishMsg("Bob", "Darn! We lost!");
		assertEquals(1, res1);
		
		int res2 = SNCore.publishMsg("Bob", "Good game though");
		assertEquals(1, res2);
		
		//test publish success
		List<Post> postList=SNCore.viewTimeline("Alice", "Bob");
		assertEquals( 2, postList.size());
		assertEquals( "Good game though", postList.get(0).getMsg());
		assertEquals( "Darn! We lost!", postList.get(1).getMsg());
	}
	
	// Feature: Following
	@Test
	public void testCharlieFollowAliceBobTimeline() throws InterruptedException {
		//testPublish
		int res1 = SNCore.publishMsg("Alice", "I love the weather today");
		assertEquals(1, res1);
		
		Thread.sleep(1000);
		int res2 = SNCore.publishMsg("Bob", "Darn! We lost!");
		assertEquals(1, res2);
		
		Thread.sleep(1000);
		int res3 = SNCore.publishMsg("Bob", "Good game though");
		assertEquals(1, res3);
		
		Thread.sleep(1000);
		int res4 = SNCore.publishMsg("Charlie", "I'm in New York today! Anyone wants to have a coffee?");
		assertEquals(1, res4);
		
		
		// charlie follows alice 
		int folRes1=SNCore.follow("Charlie", "Alice");
		assertEquals(1, folRes1);
		
		// charlie follows bob 
		int folRes2=SNCore.follow("Charlie", "Bob");
		assertEquals(1, folRes2);
		
		
		//test publish success
		List<Post> postList=SNCore.viewTimeline("Charlie", "Charlie");
		//assertEquals( 7, postList.size());
		assertEquals( "I'm in New York today! Anyone wants to have a coffee?", postList.get(0).getMsg());
		assertEquals( "Good game though", postList.get(1).getMsg());
		assertEquals( "Darn! We lost!", postList.get(2).getMsg());
		assertEquals( "I love the weather today", postList.get(3).getMsg());
	}
	
	

}
