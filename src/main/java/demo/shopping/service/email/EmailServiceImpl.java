package demo.shopping.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Service("emailService")
@Transactional
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender mailSender;

    Logger logger = Logger.getLogger("EmailController");

    @Async
    @Override
    public CompletableFuture<Void> resetPassword(String email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("17857415152@163.com");
        messageHelper.addTo(email);
        messageHelper.setSubject("重置您的密码");
        messageHelper.setText("请根据下面给出的提示重置您的密码", false);
        mailSender.send(message);
        CompletableFuture<Void> future = new CompletableFuture<Void>();
        future.complete(null);
        return future;
    }
}
