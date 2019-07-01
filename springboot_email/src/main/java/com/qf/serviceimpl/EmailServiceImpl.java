package com.qf.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.entity.Email;
import com.qf.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;


/**
 * @author junjun
 * @date 2019/6/30 17:11
 */
@Service
public class EmailServiceImpl implements IEmailService{
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(Email email) {
        System.out.println(email);
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
            messageHelper.setFrom(email.getFrom());
            messageHelper.addTo(email.getTo(),"888");
            messageHelper.setSubject(email.getTitle());
            messageHelper.setText(email.getContent(),true);
            messageHelper.setSentDate(email.getDate());
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
