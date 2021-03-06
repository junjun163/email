package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author junjun
 * @date 2019/6/30 13:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String password;
    private String email;
    @TableField(exist = false)
    private String code;
}
