package br.com.javaestudos.todolist.user;

import org.springframework.web.bind.annotation.*;

/**
 * Modificadores:
 * public
 * private
 * protected
 */
@RestController
@RequestMapping("/users")
public class UserController {


     @PostMapping("/")
     public void create(@RequestBody UserModel userModel) {
            System.out.println(userModel.name);
     }

}
