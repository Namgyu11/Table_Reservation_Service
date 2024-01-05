package com.example.table_reservation_service.auth.security;

import static com.example.table_reservation_service.global.type.ErrorCode.*;

import com.example.table_reservation_service.global.type.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtExceptionFilter  extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        }catch (JwtException e){
            String message = e.getMessage();

            if(message.equals(TOKEN_TIME_OUT.getDescription())){
                setResponse(response, TOKEN_TIME_OUT);
            }else if(message.equals(JWT_TOKEN_WRONG_TYPE.getDescription())){
                setResponse(response, JWT_TOKEN_WRONG_TYPE);
            }else if(message.equals(UNSUPPORTED_TOKEN.getDescription())){
                setResponse(response,UNSUPPORTED_TOKEN);
            }else if(message.equals(WRONG_TOKEN.getDescription())){
                setResponse(response, WRONG_TOKEN);
            }else {
                setResponse(response, INVALID_ACCESS_TOKEN);
            }
        }
    }

    //한글 출력을 위해 getWriter() 사용
    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        map.put("errorCode", errorCode);
        map.put("errorMessage", errorCode.getDescription());

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getStatusCode());

        log.error("jwt token error -> {}", errorCode);
        response.getWriter().println(objectMapper.writeValueAsString(map));

    }
}
