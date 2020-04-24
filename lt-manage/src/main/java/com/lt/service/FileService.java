package com.lt.service;

import org.springframework.web.multipart.MultipartFile;

import com.lt.vo.ImageVo;

public interface FileService {

	ImageVo picupload(MultipartFile uploadFile);

}
