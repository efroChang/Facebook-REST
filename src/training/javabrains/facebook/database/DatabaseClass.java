package training.javabrains.facebook.database;

import java.util.*;

import training.javabrains.facebook.data.Message;
import training.javabrains.facebook.data.Profile;

/**
 * Fake Database
 * 
 */
public class DatabaseClass {

	private static Map<Long, Message> messageMap = new HashMap<>();
	private static Map<String, Profile> profileMap = new HashMap<>();

	public static Map<Long, Message> getMessageMap() {
		return messageMap;
	}

	public static Map<String, Profile> getProfileMap() {
		return profileMap;
	}
}
