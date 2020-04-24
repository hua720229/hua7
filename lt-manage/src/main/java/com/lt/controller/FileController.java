package com.lt.controller;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.lt.service.FileService;
import com.lt.vo.ImageVo;
@RestController
public class FileController {
 /**
  * 1.获取用户文件信息，包含文件的名称
  * 2.指定文件的上传路径 if()判断
  * 3.实现文件的上传
  * 
  */
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/pic/upload")
	public ImageVo picupload( MultipartFile uploadFile) {
		return fileService.picupload(uploadFile);
	}
	
	@RequestMapping("/file")//具体看file.jsp页面,以下代码做测试
	public String file(MultipartFile fileImage)throws Exception{
		//获取input标签中的name属性
		//获取图片的名称
		String filename = fileImage.getOriginalFilename();
		System.out.println("1:"+filename);
		//获取文件名字
		//String fileName = fileImage.getOriginalFilename();
		//定义文件夹的路径，创建文件，指定上传目录
		File fileDir = new File("E:/abc/image");
		if (!fileDir.exists()) {
			//创建文件夹
			fileDir.mkdirs();
		}
		//实现文件的上传
//		fileImage.transferTo(new File("E:/abc/image/"+fileName));
//		return "redirect:/file.jsp";
		String path = "E:/abc/image/" + filename;
		File file = new File(path);
		//利用工具API输出操作
		fileImage.transferTo(file);
		return "文件上传成功！！！";
	}
	
} 
