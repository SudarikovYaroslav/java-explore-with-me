package controller.admin_controller;

import dto.user.NewUserDto;
import dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/users")
public class UserAdminController {

    private static final String DEFAULT_FROM = "0";
    private static final String DEFAULT_SIZE = "10";

    private final UserService userService;

    @GetMapping
    public List<UserDto> findUsers(@RequestParam Integer[] ids,
                                   @RequestParam(defaultValue = DEFAULT_FROM) Integer from,
                                   @RequestParam(defaultValue = DEFAULT_SIZE) Integer size) {
        return userService.findUsers(ids, from, size);
    }

    @PostMapping
    public UserDto postUser(@RequestBody NewUserDto dto) {
        return userService.postUser(dto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
