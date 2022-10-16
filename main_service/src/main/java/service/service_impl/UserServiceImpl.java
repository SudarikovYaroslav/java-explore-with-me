package service.service_impl;

import dto.user.NewUserDto;
import dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    //TODO реализовать логику и репозиторий
    @Override
    public List<UserDto> findUsers(Integer[] ids, Integer from, Integer size) {
        return null;
    }

    //TODO реализовать логику и репозиторий
    @Override
    public UserDto postUser(NewUserDto dto) {
        return null;
    }

    //TODO реализовать логику и репозиторий
    @Override
    public void deleteUser(Long userId) {

    }
}
