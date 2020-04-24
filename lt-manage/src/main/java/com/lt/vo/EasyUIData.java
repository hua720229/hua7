package com.lt.vo;

import java.io.Serializable;
import java.util.List;

import com.lt.pojo.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
//展现数据表格
@Data
@Accessors
@NoArgsConstructor
@AllArgsConstructor
public class EasyUIData implements Serializable{
private Integer total;//记录总数
private List<Item>rows;//展现数据的集合
}
