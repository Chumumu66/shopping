package demo.shopping.service.email;

import javax.mail.MessagingException;
import java.util.concurrent.CompletableFuture;

public interface EmailService {
    public CompletableFuture<Void> resetPassword(String email) throws MessagingException;
}
