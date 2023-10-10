package br.com.javaestudos.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

    @Autowired // spring gerenciatodo ciclo de vida
    private IUserRepository userRepository;

     @PostMapping("/")
     public ResponseEntity create(@RequestBody UserModel userModel) {
         var user = this.userRepository.findByUsername(userModel.getUsername());

         if(user != null) {
             // Mensagem de erro
             // Status Code do Postman
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
         }

         var userCreated = this.userRepository.save(userModel);
         return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
     }

}
