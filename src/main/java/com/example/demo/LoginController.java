package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;


    @Autowired
    private LoginService loginService;

//    @RequestMapping("/")
//    public String home() {
//        return "index";
//    }

    @RequestMapping("/login")
    public String index(Model model) {
        return "loginform";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String getLoginForm(){
        return "loginform";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String doLogin(@ModelAttribute(name = "loginForm") User user, Model model)
    {

        if(loginService.findUser(user.getUsername(),user.getPassword())!= null)
        {
            return "index";
        }
        else
        {
            model.addAttribute("invalidCredentials",true);
            return "loginform";
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String getRegisterForm(Model model)
    {
        model.addAttribute("user",new User());

        return "registerform";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@Valid User userForm, BindingResult result){

        if(result.hasErrors()){
            return "registerform";
        }

        String firstname = userForm.getFirstname();
        String lastname = userForm.getLastname();
        String username = userForm.getUsername();
        String password = userForm.getPassword();

        User user = new User(firstname,lastname,username,password);

        loginService.registerUser(user);
        return "loginform";
    }
}
