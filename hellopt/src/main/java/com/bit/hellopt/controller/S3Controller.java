package com.bit.hellopt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.bit.hellopt.commons.utils.S3Utils;

@Controller
public class S3Controller {
	
	@Autowired
	S3Utils s3Utils;
	
	@GetMapping("/s3/{path}/{key:.+}")
	public void getS3Image(HttpServletResponse response, 
			@PathVariable String path, @PathVariable String key) {
		try {
			S3Object s3obj = s3Utils.downloadFile(path +"/"+ key);
			S3ObjectInputStream s3is = s3obj.getObjectContent();
			String extension = FilenameUtils.getExtension(s3obj.getKey());
			if(extension.equals("jpg")) {
				response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			} else if(extension.equals("png")) {
				response.setContentType(MediaType.IMAGE_PNG_VALUE);
			} else if(extension.equals("gif")) {
				response.setContentType(MediaType.IMAGE_GIF_VALUE);
			}
			
			IOUtils.copy(s3is, response.getOutputStream());
			s3is.close();
			response.getOutputStream().close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
