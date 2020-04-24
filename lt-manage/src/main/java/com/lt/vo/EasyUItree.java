package com.lt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUItree {
private Long id;
private String text;//节点的名字
private String state;//树形结构的展开或关闭的状态信息

}
