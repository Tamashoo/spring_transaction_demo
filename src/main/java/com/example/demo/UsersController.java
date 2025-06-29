package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
//    @Transactional
    public List<Users> createUser(@RequestBody UserRequest request) {
        List<Users> users;
        try {
            usersService.addUser(request.getUsername());
        } catch (RuntimeException e) {
            log.error("ユーザの追加に失敗");
        } finally {
            users = usersService.getAllUsers();
            log.info("ユーザ一覧取得");
        }
        return users;
    }

    @GetMapping
    public List<Users> listUsers() {
        return usersService.getAllUsers();
    }

    // リクエストボディ用クラス
    public static class UserRequest {
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
