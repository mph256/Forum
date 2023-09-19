package com.mph.domain.util;

import java.util.regex.Pattern;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import java.net.URL;

public class Util {

	private static final String IMG_PATH = "/assets/img";

	private static final String IMG_REGEX = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

	private Util() {
	}

	public static void createDir(String fullPath) {

		File dir = new File(fullPath);

		if(!dir.exists())
			dir.mkdir();

	}

	public static File createDefaultProfilePicture(String login) {

		String path = getRealImgPath() + "/users";

		createDir(path);

		File profilePicture = new File(path + "/" + login + ".png");

		copyFile(getResourceFile("default_profile_picture.png"), profilePicture);

		return profilePicture;

	}

	public static File updateProfilePicture(File file, String fileName, String login) {

		String path = getRealImgPath() + "/users";

		File profilePicture = new File(path + "/" + login + fileName.substring(fileName.lastIndexOf(".")));

		copyFile(file, profilePicture);

		return profilePicture;

	}

	public static void copyFile(File src, File dest) {

		try {
			Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static boolean isImage(String name) {
		return Pattern.matches(IMG_REGEX, name);
	}

	public static File getResourceFile(String name) {

		URL url = Util.class.getClassLoader().getResource(name);

		if(url == null)
			throw new IllegalArgumentException();

		return new File(url.getFile());

	}

	public static String getRealImgPath() {

		return Util.class.getResource("/").getPath()
			.replace("/WEB-INF/classes/", "") 
			+ IMG_PATH;

	}

	public static File getProfilePictureByLogin(String login) {

		File profilePicture = null;

		String path = getRealImgPath() + "/users";

		createDir(path);

		File dir = new File(path);
		File[] files = dir.listFiles();

		for(File file : files){

			String name = file.getName();

			if(isImage(name) && login.equals(name.substring(0, name.lastIndexOf(".")))) {

				profilePicture = file;
				break;

			}

		}

		if(profilePicture == null)
			profilePicture = createDefaultProfilePicture(login);
 
		return profilePicture;

	}

}