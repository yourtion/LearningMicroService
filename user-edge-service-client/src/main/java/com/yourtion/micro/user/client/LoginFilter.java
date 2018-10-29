package com.yourtion.micro.user.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.yourtion.micro.user.dto.UserDTO;
import org.apache.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;

public abstract class LoginFilter implements Filter {

    protected abstract void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO);

    private static Cache<String, UserDTO> cache = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(3, TimeUnit.MINUTES)
            .build();

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;

        var token = request.getParameter("token");
        if (token == null || token.isBlank()) {
            var cookies = request.getCookies();
            if (cookies != null) {
                for (var c : cookies) {
                    if (c.getName().equals("token")) {
                        token = c.getValue();
                    }
                }
            }

        }

        UserDTO userDTO = null;
        if (token != null && !token.isBlank()) {
            userDTO = cache.getIfPresent(token);
            if (userDTO == null) {
                userDTO = requestUserInfo(token);
                if (userDTO != null) {
                    cache.put(token, userDTO);
                }
            }
        }

        if (userDTO == null) {
            response.sendRedirect("http://127.0.0.1:8080/user/login");
            return;
        }


        login(request, response, userDTO);

        filterChain.doFilter(request, response);
    }


    private UserDTO requestUserInfo(String token) {
        String url = "http://127.0.0.1:8082/user/authentication";

        var client = HttpClient.newHttpClient();
        var post = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .header("token", token).build();
        try {
            var response = client.send(post, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != HttpStatus.SC_OK) {
                throw new RuntimeException("request user info failed! statusLine:" + response.statusCode());
            }
            var userDTO = new ObjectMapper().readValue(response.body(), UserDTO.class);
            return userDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void destroy() {
    }
}
