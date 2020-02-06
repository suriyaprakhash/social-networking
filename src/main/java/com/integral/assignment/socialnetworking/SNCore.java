package com.integral.assignment.socialnetworking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.integral.assignment.socialnetworking.model.Memory;
import com.integral.assignment.socialnetworking.model.Post;


/**
 * This is the core class of the social networking app. This class loads the
 * memory for the first time when the class is intialized.
 * 
 * @author suriya
 *
 */
public class SNCore {

	private static Memory memory = new Memory();

	
	/**
	 * Used to see memory any time
	 * @return memory
	 */
	public static Memory getMemory() {
		return memory;
	}

	/**
	 * This publishes the msg into the memory
	 * 
	 * @param user
	 * @param msg
	 * @return 
	 * @return
	 */
	public static int publishMsg(String user, String msg) {
		Map<String, List<Post>> timelineMap = memory.getTimelineMap();
		List<Post> postList = timelineMap.get(user);
		// if the user timeline is not available add it to timeline
		if (postList == null) {
			postList = new ArrayList<Post>();
		}
		Post post = new Post(msg, new Date().getTime());
		//System.out.println(post.getMsg()+":"+post.getDate());
		postList.add(post);
		timelineMap.put(user, postList);
		return 1;
	}

	/**
	 * This sets the Set of user the current user is following.
	 * 
	 * @param curUser
	 * @param user
	 * @return
	 */
	public static int follow(String curUser, String user) {
		Map<String, Set<String>> userFollowingMap = memory.getFollowMap();
		Set<String> followingSet = userFollowingMap.get(curUser);
		if (followingSet == null) {
			followingSet = new HashSet<>();
		}
		followingSet.add(user);
		userFollowingMap.put(curUser, followingSet);
		return 1;
	}

	/**
	 * This displays both the current user's timeline as well as other users
	 * timeline with the recent post
	 * 
	 * @param curUser
	 * @param user
	 * @return
	 */
	public static List<Post> viewTimeline(String curUser, String user) {
		List<Post> postList = memory.getTimelineMap().get(user);
		if (!curUser.equals(user)) {
			Collections.reverse(postList);
		}
		// add following user post on own timeline
		else {
			// add post of the users whome the curUser follows
			Set<String> followingSet = memory.getFollowMap().get(curUser);
			// check if following someone to add their posts
			if(followingSet!=null) {
				followingSet.forEach(followingUser -> {
					postList.addAll(memory.getTimelineMap().get(followingUser));
				});
			}
			Collections.sort(postList, new PostCompartor());
		}
		return postList;
	}

}
