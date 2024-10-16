package com.kwakmunsu.login.controller;

import com.kwakmunsu.login.dto.JoinDTO;
import com.kwakmunsu.login.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@Controller
@Log4j2
public class JoinController {

    final private JoinService joinService;

    @GetMapping("/join")
    public String joinP() {
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO ) {

        log.info(joinDTO);

        joinService.signUp(joinDTO);
        return "redirect:/login";
    }
}
