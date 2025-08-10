package com.krishnakant.SecurityApp.SecurityApplication.services;

import com.krishnakant.SecurityApp.SecurityApplication.dto.SignUpDto;
import com.krishnakant.SecurityApp.SecurityApplication.dto.UserDto;
import com.krishnakant.SecurityApp.SecurityApplication.entities.User;
import com.krishnakant.SecurityApp.SecurityApplication.exceptions.ResourceNotFoundException;
import com.krishnakant.SecurityApp.SecurityApplication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("user with email " + username + " not found"));
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user with id "+ userId + " not found."));
    }

    public User getUserbyEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public UserDto signUp(SignUpDto signUpDto) {
        Optional<User> user = userRepository.findByEmail(signUpDto.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User with email already exists " + signUpDto.getEmail());
        }

        User toBeCreatedUser = modelMapper.map(signUpDto, User.class);
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));
        User savedUser = userRepository.save(toBeCreatedUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

    public User saveNewUser(User newUser) {
        return userRepository.save(newUser);
    }
}
