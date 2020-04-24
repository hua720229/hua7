package com.lt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ImageVo {
  private Integer error;
  private String url;
  private Integer width;
  private Integer height;
  
  //指定图片上传失败的方法
  public static ImageVo fail() {
	  
	  return new ImageVo(1, null, null, null);
  }
}
