package com.qf.service;

import com.qf.entity.Email;

/**
 * @author junjun
 * @date 2019/6/30 14:12
 */
public interface IEmailService {
    void sendEmail(Email email);
}
