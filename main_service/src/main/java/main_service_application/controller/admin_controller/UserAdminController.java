package main_service_application.controller.admin_controller;

import main_service_application.dto.user.NewUserDto;
import main_service_application.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import main_service_application.service.UserService;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/users")
public class UserAdminController {

    private static final String DEFAULT_FROM = "0";
    private static final String DEFAULT_SIZE = "10";

    private final UserService userService;

    @GetMapping
    public List<UserDto> findUsers(@RequestParam Long[] ids,
                                   @RequestParam(defaultValue = DEFAULT_FROM) Integer from,
                                   @RequestParam(defaultValue = DEFAULT_SIZE) Integer size) {
        log.info("Searching Users ids: {}", Arrays.toString(ids));
        return userService.findUsers(ids, from, size);
    }

    @PostMapping
    public UserDto postUser(@RequestBody NewUserDto dto) {
        log.info("Post User {}", dto);
        return userService.postUser(dto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        log.info("Delete User id: {}", userId);
        userService.deleteUser(userId);
    }
}
