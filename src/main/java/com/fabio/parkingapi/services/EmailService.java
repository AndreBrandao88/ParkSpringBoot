package com.fabio.parkingapi.services;

import com.fabio.parkingapi.dtos.EmailDto;
import com.fabio.parkingapi.entities.EmailModel;
import com.fabio.parkingapi.entities.UserModel;
import com.fabio.parkingapi.entities.enums.StatusEmail;
import com.fabio.parkingapi.repositories.EmailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String remetente;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    public EmailDto sendEmail(EmailDto emailDto) {
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto,emailModel);
        emailModel.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            BeanUtils.copyProperties(emailRepository.save(emailModel), emailDto);
            return emailDto;
        }
    }

    public void sendNewPassword(String to,String title, String msg){
        var message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(title);
        message.setText(msg);
        emailSender.send(message);
    }

    public void sendNewPasswordEmail(UserModel userModel, String newPass){
        SimpleMailMessage sm = prepareNewPasswordEmail(userModel,newPass);
        sendNewPassword(userModel.getEmail(), sm.getSubject(),sm.getText());
    }

    protected SimpleMailMessage prepareNewPasswordEmail(UserModel userModel, String newPass){
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(userModel.getEmail());
        sm.setFrom(remetente);
        sm.setSubject("Solicitação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: "+ newPass);
        return sm;
    }


}
