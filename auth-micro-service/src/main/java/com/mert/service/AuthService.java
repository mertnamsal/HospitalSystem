package com.mert.service;

import com.mert.dto.request.LoginRequestDto;
import com.mert.dto.request.RegisterRequestDto;
import com.mert.dto.response.LoginResponseDto;
import com.mert.exception.AuthException;
import com.mert.exception.ErrorType;
import com.mert.mapper.IAuthMapper;
import com.mert.rabbitmq.model.CreateUser;
import com.mert.rabbitmq.producer.CreateUserProducer;
import com.mert.repository.IAuthRepository;
import com.mert.repository.entity.Auth;
import com.mert.utility.JwtTokenManager;
import com.mert.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

    private final IAuthRepository repository;
    private final CreateUserProducer createUserProducer;
    private final JwtTokenManager jwtTokenManager;

    public AuthService(IAuthRepository repository, CreateUserProducer createUserProducer, JwtTokenManager jwtTokenManager){
        super(repository);
        this.repository = repository;
        this.createUserProducer = createUserProducer;
        this.jwtTokenManager = jwtTokenManager;
    }

    public void register(RegisterRequestDto dto) {
        if(!dto.getPassword().equals(dto.getRepassword()))
            throw new AuthException(ErrorType.AUTH_PASSWORD_ERROR);

         if(repository.isUsername(dto.getUsername()))
             throw new AuthException(ErrorType.AUTH_USERNAME_ERROR);
         Auth auth =save(IAuthMapper.INSTANCE.toAuth(dto));
         createUserProducer.createSendMessage(CreateUser.builder()
                         .authid(auth.getId())
                         .email(auth.getEmail())
                         .username(auth.getUsername())
                         .date(dto.getDate())
                 .build());


    }

    public LoginResponseDto login(LoginRequestDto dto) {
        Optional<Auth> auth = repository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
        if(auth.isEmpty()){
           throw new AuthException(ErrorType.AUTH_LOGIN_ERROR);
        }
        Optional<String>token=jwtTokenManager.createToken(auth.get().getId());
        if(token.isEmpty())
            throw new AuthException(ErrorType.TOKEN_ERROR);

        return LoginResponseDto.builder()
                .token(token.get())
                .build();

    }
}
