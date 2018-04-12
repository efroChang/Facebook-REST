package training.javabrains.facebook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import training.javabrains.facebook.data.Profile;
import training.javabrains.facebook.database.DatabaseClass;

public class ProfileService {

	private static Map<String, Profile> profileMap = DatabaseClass.getProfileMap(); // Fake database

	public ProfileService() {
	}

	// So it can only be created once.
	static {
		profileMap.put("emma", new Profile(1, "emma", "Emma", "Chang"));
		profileMap.put("roni", new Profile(2, "roni", "Roni", "Chang"));
	}

	public List<Profile> getAllProfiles() {

		return new ArrayList<>(profileMap.values());
	}

	public Profile getProfile(String profileName) {
		return profileMap.get(profileName);
	}

	public Profile addProfile(Profile profile) {
		profile.setId(profileMap.size() + 1);
		profileMap.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile updateProfile(Profile profile) {
		if (profile	.getProfileName()
					.isEmpty()) {
			return null;
		}

		profileMap.put(profile.getProfileName(), profile);
		return profile;

	}

	public Profile removeProfile(String profileName) {
		return profileMap.remove(profileName);
	}
}
