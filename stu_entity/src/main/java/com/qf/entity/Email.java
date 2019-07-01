package com.qf.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author junjun
 * @date 2019/6/30 13:46
 */
@Data
public class Email implements Serializable{
    private String from;
    private String to;
    private String title;
    private String content;
    private Date date;
}
