package com.integral.assignment.socialnetworking.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * This class acts as the memory of the social networking app. The
 * {@code followMap} has the currentUser and its Set User whome the current user
 * is following The {@code timelineMap} has the list of post of that particular
 * user in the map key.
 * 
 * @author suriya
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Memory {
	// Map<curUser,Set<followingUsers>>
	private Map<String, Set<String>> followMap = new HashMap<>();
	// Map<curUser,List<Posts(msg,time posted)>>
	private Map<String, List<Post>> timelineMap = new HashMap<>();

}
