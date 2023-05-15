package demo.shopping.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import demo.shopping.exception.AdminLoginNoException;

@Controller
public class BaseController {

	@ModelAttribute  
    public void isLogin(HttpSession session, HttpServletRequest request) throws AdminLoginNoException {      
       if(session.getAttribute("auser") == null){  
            throw new AdminLoginNoException("未登录！");
       }  
    } 
}
