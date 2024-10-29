package com.kwakmunsu.login.controller;

import com.kwakmunsu.login.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Log4j2
@RequiredArgsConstructor
@Controller
public class MainController {

    private final UserService userService;
    @GetMapping("/")
    public String mainP(Model model) {

        try {
            String id = userService.getUserId();
            String role = userService.getUserRole();

            // model 객체에 속성을 부여하면 앞 단에서 사용 가능하다. 해당 속성으로 조건을 걸어서 사용 가능.
            model.addAttribute("id",id);
            model.addAttribute("role",role); //
        } catch (Exception e) {
            log.info(e.getMessage());
            return "redirect:/";
        }




        return "main";
    }
}
