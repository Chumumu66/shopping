package demo.shopping.controller.email;

import demo.shopping.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.async.DeferredResult;

import javax.mail.MessagingException;
import javax.validation.constraints.Email;
import java.util.concurrent.CompletableFuture;


@Controller
public class emailController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/resetPassword")
    public String toResetPassword(){
        return "email/resetPassword";
    }

    @PostMapping("/resetPassword")
    public DeferredResult<String> doResetPassword(@Email String email) throws MessagingException {
        DeferredResult<String> result = new DeferredResult<>();
        CompletableFuture<Void> future = emailService.resetPassword(email);
        future.whenComplete((v, error) -> {
            if(error != null){
                result.setErrorResult(error);
            }else{
                result.setResult("/email/emailSent");
            }
        });
        return result;
    }
}
