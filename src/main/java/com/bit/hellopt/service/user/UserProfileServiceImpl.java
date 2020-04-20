package com.bit.hellopt.service.user;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bit.hellopt.commons.utils.S3Utils;
import com.bit.hellopt.data.ProfileMapper;
import com.bit.hellopt.data.UserXMLMapper;
import com.bit.hellopt.vo.user.ProfileVO;
import com.bit.hellopt.vo.user.User;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	ProfileMapper mapper;
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	S3Utils s3Utils;
	
	@Autowired
	UserXMLMapper userMapper;

	@Override
	public String insertProfile(User user, MultipartFile multipartFile) {
		
		ProfileVO profile = new ProfileVO();
		profile.setFkUserId(user.getUserId());
		profile.setOriginalFileName(multipartFile.getOriginalFilename());
		
		
		Calendar calendar = Calendar.getInstance(); 
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
		
		String storedName = generateRandomString() +  formatter.format(calendar.getTime()) + "." + multipartFile.getOriginalFilename().split("\\.")[1];
		
		profile.setStoredFileName(storedName);
		
		File file = new File(multipartFile.getOriginalFilename());
		
		try {
			multipartFile.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		s3Utils.uploadFile("profile/" + storedName, file);
		mapper.insertProfile(profile);
		
		/*
		 * try { file.transferTo(new File(rpath + storedName));
		 * profile.setStoredFileName(storedName); mapper.insertProfile(profile); } catch
		 * (IllegalStateException e) { e.printStackTrace(); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
		return storedName;
	}

	@Override
	public ProfileVO selectProfile(String userId) {
		return mapper.selectProfile(userId);
	}
	
	public String updateProfile(User user, MultipartFile multipartFile) {
		
		ProfileVO storedProfile = selectProfile(user.getUserId());
		
		ProfileVO profile = new ProfileVO();
		profile.setFkUserId(user.getUserId());
		profile.setOriginalFileName(multipartFile.getOriginalFilename());
		
		Calendar calendar = Calendar.getInstance(); 
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
		
		String storedName = generateRandomString() +  formatter.format(calendar.getTime()) + "." + multipartFile.getOriginalFilename().split("\\.")[1];
		profile.setStoredFileName(storedName);
		
		File file = new File(multipartFile.getOriginalFilename());
		
		try {
			multipartFile.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(storedProfile != null) {
			s3Utils.deleteFile("profile/" + storedProfile.getStoredFileName());
			s3Utils.uploadFile("profile/" + storedName, file);
			
			mapper.updateProfile(profile);
			user.setUserProfile(storedName);
			userMapper.updateUser(user);
		} else {
			s3Utils.uploadFile("profile/" + storedName, file);
			mapper.insertProfile(profile);
			
			user.setUserProfile(storedName);
			userMapper.updateUser(user);
		}
	
		
		
		return storedName;
	}

	public String generateRandomString() {
		int length = 18;
		boolean useLetter = true;
		boolean useNumbers = false;
		return RandomStringUtils.random(length, useLetter, useNumbers);
	}
}
