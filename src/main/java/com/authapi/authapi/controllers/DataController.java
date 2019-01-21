package com.authapi.authapi.controllers;
/*Controller for showing user information
 * I get token from session and compare it with token in db, if there are not the same, then it will be returned default info(none)
 * */

import com.authapi.authapi.databaseEntities.User;
import com.authapi.authapi.repositories.UserRepository;
import com.authapi.authapi.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DataController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/userdata")
    public String getData(HttpServletRequest request, Model model) {

        User user = userRepository.findByUsername(UserDetailService.getCurrentUsername());
        HttpSession session = request.getSession();
        String req = (String) session.getAttribute("token");

        if (req.equals(user.getToken())) {
            model.addAttribute("user", user);
        } else
            model.addAttribute("user", new User("none", "none", "none", "none"));

        return "dataPage";
    }

}
