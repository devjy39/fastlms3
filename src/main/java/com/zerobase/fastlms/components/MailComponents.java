package com.zerobase.fastlms.components;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MailComponents {
    
    private final JavaMailSender javaMailSender;
    
    public void sendMailTest() {
    
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("satcop@naver.com");
        msg.setSubject("안녕하세요. 제로베이스 입니다.");
        msg.setText(" 안녕하세요. 제로베이스 입니다. 방갑습니다. ");
        
        javaMailSender.send(msg);
    }
    
    public boolean sendMail(String mail, String subject, String text) {
        try {
            javaMailSender.send(mimeMessage -> {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(mail);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(text, true);
            });
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean sendResetPasswordMail(String mail, String uuid) {
        String subject = "[fastlms] 비밀번호 초기화 메일 입니다. ";
        String text = "<p>fastlms 비밀번호 초기화 메일 입니다.<p>" +
                "<p>아래 링크를 클릭하셔서 비밀번호를 초기화 해주세요.</p>" +
                "<div><a target='_blank' href='http://localhost:8080/member/reset/password?id=" + uuid + "'> 비밀번호 초기화 링크 </a></div>";

        return sendMail(mail, subject, text);
    }

    public boolean sendAuthenticationMail(String mail, String uuid) {
        String subject = "fastlms 사이트 가입을 축하드립니다. ";
        String text = "<p>fastlms 사이트 가입을 축하드립니다.<p><p>아래 링크를 클릭하셔서 가입을 완료 하세요.</p>"
                + "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id=" + uuid + "'> 가입 완료 </a></div>";

        return sendMail(mail, subject, text);
    }
    

}
