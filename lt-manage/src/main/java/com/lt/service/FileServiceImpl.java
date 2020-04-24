package com.lt.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.lt.vo.ImageVo;
@Service
@PropertySource("classpath:/properties/image.properties")//磁盘路径和虚拟域名的指定位置
public class FileServiceImpl implements FileService{
	//通过@Value动态赋值(path磁盘路径)和(urlPath虚拟域名)
	@Value("${image.path}")
	private String path;
	@Value("${image.urlPath}")
	private String urlPath;

	@Override
	public ImageVo picupload(MultipartFile uploadFile) {
		//ImageVo imageVo = new ImageVo();
		//获取文件的名称 .jpg等判断图片的格式
		String Filename = uploadFile.getOriginalFilename();
		
		Filename = Filename.toLowerCase();
		if(!Filename.matches("^.+\\.(jpg|png|gif)$")) {
			//imageVo.setError(1);//格式有误 2为正常
			return ImageVo.fail();
		}
		try {
			BufferedImage bufferedImage=ImageIO.read(uploadFile.getInputStream());
			int height=bufferedImage.getHeight();
			int width=bufferedImage.getWidth();
			if(width==0||height==0) {
				//imageVo.setError(1);
				return ImageVo.fail();
			}
			//实现分文件存储
			String dateDir = new SimpleDateFormat("yyyy/MM/dd/")
					.format(new Date());
			//准备文件夹
			String direFilePath = path+dateDir; //磁盘路径+时间 路径在上边定义了
			File file = new File(direFilePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			//使用UUID定义文件名称uuid.jpg等
			String uuid=UUID.randomUUID().toString();
			//获取图片类型
			String fileType = Filename.substring(Filename.lastIndexOf("."));
			//拼接新的文件名称
			String realFileName = uuid + fileType;
			//完成文件上传
			String realFilePath = direFilePath + realFileName;
			uploadFile.transferTo(new File(realFilePath));
			//拼接真实的url 实现数据的回显
			String url = urlPath + dateDir + realFileName;
			//将文件信息回传给页面
			ImageVo imageVo = new ImageVo(0, url, width, height);
			return imageVo;
		} catch (Exception e) {
			e.printStackTrace();
			return ImageVo.fail();
		}
	}
}
