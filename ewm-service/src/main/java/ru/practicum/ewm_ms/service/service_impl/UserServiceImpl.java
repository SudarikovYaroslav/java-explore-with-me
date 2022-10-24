package ru.practicum.ewm_ms.service.service_impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm_ms.dto.user.NewUserDto;
import ru.practicum.ewm_ms.dto.user.UserDto;
import ru.practicum.ewm_ms.mappers.UserMapper;
import ru.practicum.ewm_ms.model.User;
import ru.practicum.ewm_ms.repository.UserRepository;
import ru.practicum.ewm_ms.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDto> findUsers(Long[] ids, Integer from, Integer size) {
        Pageable pageable = null;
        if (from != null && size != null) {
            pageable = PageRequest.of(from / size, size);
        }

        List<User> users;
        if (ids != null && ids.length > 0) {
            String idsStr = machIds(ids);
            if (pageable != null) {
                users = userRepository.findAll(idsStr, pageable).toList();
            } else {
                users = userRepository.findAll(idsStr);
            }
            return users.stream().map(UserMapper::toUserDto).collect(Collectors.toList());
        }

        if (pageable != null) {
            users = userRepository.findAll(pageable).toList();
        } else {
            users = userRepository.findAll();
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

    private String machIds(Long[] ids) {
        StringBuilder result = new StringBuilder();
        for (long id : ids) {
            result.append(id);
            result.append(",");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }
}