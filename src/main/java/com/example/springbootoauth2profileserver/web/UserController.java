package com.example.springbootoauth2profileserver.web;

import com.example.springbootoauth2profileserver.service.UserService;
import com.example.springbootoauth2profileserver.web.dto.LoginDto;
import com.example.springbootoauth2profileserver.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/join")
    public String join(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);

        return "join";
    }

    @PostMapping("/user/join")
    public String doJoin(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result,
                         HttpServletRequest request, Errors errors) {
        try {
            userService.registerNewUserAccount(userDto);
        } catch (Exception ex) {
            return "join";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(WebRequest request, Model model) {
        LoginDto loginDto = new LoginDto();
        model.addAttribute("login", loginDto);
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute("login") @Valid LoginDto loginDto, BindingResult result,
                         HttpServletRequest request, Errors errors) {
        try {
            userService.login(loginDto);
        } catch (Exception ex) {
            return "login";
        }

        return "redirect:/info";
    }
}
