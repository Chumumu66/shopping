package demo.shopping.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import demo.shopping.exception.UserLoginNoException;
@Controller
public class BaseBeforeController {

	@ModelAttribute  
    public void isLogin(HttpSession session) throws UserLoginNoException {
       if(session.getAttribute("bruser") == null){  
            throw new UserLoginNoException("未登录！");
       }  
    } 
}
