package com.project.pbl3.controller;

import com.project.pbl3.model.teachers;
import com.project.pbl3.model.users;
import com.project.pbl3.model.users_roles;
import com.project.pbl3.repositories.RoleRepository;
import com.project.pbl3.repositories.UserRepository;
import com.project.pbl3.repositories.UserRoleRepository;
import com.project.pbl3.service.PasswordEncoder;
import com.project.pbl3.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class userController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/register")
    public String register(@ModelAttribute("users")users users){
        List<users> usersList = userRepository.findAll();
        for(users u: usersList){
            if(u.getUsername().compareTo(users.getUsername())==0)
                return "invalid";
        }
        if(users.getUsername()=="" || users.getPassword()==""){
            return "invalid";
        }


        /*teachers teachers = new teachers();
        teachers.setEmail(users.getUsername());
        teacherService.save(teachers);
        users.setTeacherId(teachers.getID());*/
        users.setPassword(PasswordEncoder.getEncodePass(users.getPassword()));
        users.setEnable(true);
        users.setTeacherId(0);
        userRepository.save(users);
        users_roles users_roles = new users_roles();
        users_roles.setUsers(users);
        users_roles.setRoles(roleRepository.getOne(3));
        userRoleRepository.save(users_roles);
        return "/register_success";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
