package demo.shopping.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import demo.shopping.exception.AdminLoginNoException;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;

@Controller
public class BaseController {

    public String toString(String str) throws IOException {
        return StringUtils.substringAfter(StringUtils
                .substringBefore(new String(Files.readAllBytes(new ClassPathResource("templates/i18n/messages.properties").getFile().toPath()))
                        .substring(new String(Files.readAllBytes(new ClassPathResource("templates/i18n/messages.properties").getFile().toPath()))
                                .indexOf(str)),"\r\n"), "= ");
    }

	@ModelAttribute  
    public void isLogin(HttpSession session, HttpServletRequest request) throws AdminLoginNoException, IOException {
       if(session.getAttribute("auser") == null){  
            throw new AdminLoginNoException(toString("baseController"));
       }  
    } 
}
