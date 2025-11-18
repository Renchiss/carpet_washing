package ru.rrishbuldin.carpet_washing.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rrishbuldin.carpet_washing.dto.auth.RegisterRequestDto;
import ru.rrishbuldin.carpet_washing.dto.user.UserDto;
import ru.rrishbuldin.carpet_washing.entity.Role;
import ru.rrishbuldin.carpet_washing.entity.User;
import ru.rrishbuldin.carpet_washing.security.enums.RoleName;
import ru.rrishbuldin.carpet_washing.exception.UserInteractionException;
import ru.rrishbuldin.carpet_washing.mapper.UserMapper;
import ru.rrishbuldin.carpet_washing.repository.RoleRepository;
import ru.rrishbuldin.carpet_washing.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findWithRolesByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Transactional
    public UserDto registerUser(RegisterRequestDto registerRequestDto) {
        if (userRepository.existsByLogin(registerRequestDto.getLogin())) {
            throw new UserInteractionException("User already exists");
        }

        Role role = roleRepository.findByName(RoleName.ROLE_USER);
        User newUser = User.builder()
                .login(registerRequestDto.getLogin())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .firstName(registerRequestDto.getFirstName())
                .lastName(registerRequestDto.getLastName())
                .role(role)
                .build();

        return userMapper.toDto(userRepository.save(newUser));
    }

    @Transactional
    public UserDto updateUserRole(Long userId, RoleName roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserInteractionException("User not found"));

        user.setRole(roleRepository.findByName(roleName));

        return userMapper.toDto(userRepository.save(user));
    }
}
