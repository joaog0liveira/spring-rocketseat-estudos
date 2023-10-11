package br.com.javaestudos.todolist.filter;


import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.javaestudos.todolist.user.IUserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component // Toda classe que eu quero que o spring genrecie eu preciso colocar
// o @Component ou @RestController
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //Pegar a autenticação (Usuario e senha)
        var authorization = request.getHeader("Authorization");

        //substring = Extrair uma parte de uma string
        //trim para remover todos os espaços que tenho
        var authEncoded = authorization.substring("Basic".length()).trim();

        byte[] authDecode = Base64.getDecoder().decode(authEncoded);

        var authString = new String(authDecode);

        String[] credentials = authString.split(":");
        String username = credentials[0];
        String password = credentials[1];

        System.out.println("Auth");
        System.out.println(username);
        System.out.println(password);

        //Validar o usuario
        var user = this.userRepository.findByUsername(username);
        if(user == null) {
            response.sendError(401);
        } else {
            // Validar a senha
            var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            if(passwordVerify.verified == true) {
                filterChain.doFilter(request, response);
            } else {
                response.sendError(401);
            }

            // Tudo certo? Segue viagem


        }



    }
}

