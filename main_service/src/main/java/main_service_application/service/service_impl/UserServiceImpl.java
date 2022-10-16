package main_service_application.service.service_impl;

import main_service_application.dto.user.NewUserDto;
import main_service_application.dto.user.UserDto;
import main_service_application.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import main_service_application.model.User;
import main_service_application.mappers.UserMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import main_service_application.repository.UserRepository;
import main_service_application.service.UserService;
import main_service_application.util.NotFoundMessageGen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDto> findUsers(Long[] ids, Integer from, Integer size) {
        List<User> users;
        if (ids != null && ids.length > 0) {
            users = getUsersListByIds(ids);
        } else {
            Pageable pageable = PageRequest.of(from / size, size);
            users = userRepository.findAll(pageable).toList();
        }
        return users.stream().map(UserMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto postUser(NewUserDto dto) {
        User user = UserMapper.toModel(dto);
        user = userRepository.save(user);
        return UserMapper.toUserDto(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    private List<User> getUsersListByIds(Long[] ids) {
        List<User> result = new ArrayList<>();

        for (Long id : ids) {
            User user = userRepository.findById(id).orElse(null);
            if (user == null) {
                throw new UserNotFoundException(NotFoundMessageGen.getUserNotFoundMessage(id));
            }
            result.add(user);
        }
        return result;
    }
}