package main.controller;

import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiAuthController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/api/auth/check")
    public ResponseEntity authCheck(Model model){
        model.addAttribute("result", false);
        return new ResponseEntity(model, HttpStatus.OK);
    }


    //@GetMapping("/api/auth/captcha")

    //@GetMapping("/api/auth/logout")

    //@PostMapping("/api/auth/register")

    //@PostMapping("/api/auth/login")

    //@PostMapping("/api/auth/restore")

    //@PostMapping("/api/auth/password")
}
