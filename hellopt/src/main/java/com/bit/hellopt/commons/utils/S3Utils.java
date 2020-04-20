package com.bit.hellopt.commons.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;

@Service
public class S3Utils {
	
	// buketName
	private String bucketName = "hellopt-s3-storage";
	
	private AmazonS3 s3;
	
	public S3Utils() {
		this.s3 = AmazonS3ClientBuilder.standard()
				.withRegion(Regions.AP_NORTHEAST_2)
				.build();
	}
	
	public void uploadMultipart(String path, String key, MultipartFile multipartFile) {
		File file = new File(multipartFile.getOriginalFilename());
		try {
			multipartFile.transferTo(file);
			s3.putObject(this.bucketName, path + key, file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(AmazonServiceException e) {
			System.out.println(e);
		}
		System.out.println("Amazon S3 MultipartFile upload Done!");
	}
	
	public void uploadMultipart(String key, MultipartFile multipartFile) {
		File file = new File(multipartFile.getOriginalFilename());
		try {
			multipartFile.transferTo(file);
			s3.putObject(this.bucketName, key, file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(AmazonServiceException e) {
			System.out.println(e);
		}
		System.out.println("Amazon S3 MultipartFile upload Done!");
	}
	
	public void uploadFile(String key, File file) {
		try {
			s3.putObject(this.bucketName, key, file);
		} catch(AmazonServiceException e) {
			System.out.println(e);
		}
		System.out.println("Amazon S3 File upload Done!");
	}
	
	public S3Object downloadFile(String key) {
		try {
			S3Object s3obj = s3.getObject(bucketName, key);
			return s3obj;
			
		} catch(AmazonServiceException e) {
			System.out.println(e.getErrorMessage());
		}
		return null;
		
	}
	
	public void deleteFile(String key) {
		try {
			s3.deleteObject(bucketName, key);
		} catch (AmazonServiceException e) {
			System.out.println(e.getErrorMessage());
		}
	}
}
