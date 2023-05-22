package demo.shopping.controller.before;

import javax.servlet.http.HttpSession;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import demo.shopping.exception.UserLoginNoException;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;

@Controller
public class BaseBeforeController {

    public String toString(String str) throws IOException {
        return StringUtils.substringAfter(StringUtils
                .substringBefore(new String(Files.readAllBytes(new ClassPathResource("messages.properties").getFile().toPath()))
                        .substring(new String(Files.readAllBytes(new ClassPathResource("messages.properties").getFile().toPath()))
                                .indexOf(str)),"\r\n"), "= ");
    }

	@ModelAttribute  
    public void isLogin(HttpSession session) throws UserLoginNoException, IOException {
       if(session.getAttribute("bruser") == null){  
            throw new UserLoginNoException(toString("baseController"));
       }  
    } 
}
