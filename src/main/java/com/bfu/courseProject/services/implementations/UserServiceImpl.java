package com.bfu.courseProject.services.implementations;

import com.bfu.courseProject.dtos.customer.UserRequestDTO;
import com.bfu.courseProject.dtos.customer.UserResponseDTO;
import com.bfu.courseProject.entities.User;
import com.bfu.courseProject.exceptions.UserNotFoundException;
import com.bfu.courseProject.exceptions.UsernameTakenException;
import com.bfu.courseProject.repositories.UserRepository;
import com.bfu.courseProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<UserResponseDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::responseFromCustomer)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getById(Long id) {
        User user =  userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Customer not found"));
        return UserResponseDTO.responseFromCustomer(user);
    }

    @Override
    @Transactional
    public UserResponseDTO create(UserRequestDTO userRequestDTO) {
        if (isNewEmailRegistered(userRequestDTO)) {
            throw new UsernameTakenException("Email is already registered");
        }

         User user = customerRequestDtoToCustomer(userRequestDTO);
         user.setCreationDate(Instant.now());

         String rawPassword = user.getPassword();
         String encryptedPassword = bCryptPasswordEncoder.encode(rawPassword);
         user.setPassword(encryptedPassword);

         userRepository.save(user);
         return UserResponseDTO.responseFromCustomer(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserResponseDTO edit(UserRequestDTO userRequestDTO, Long id) {
        User userToEdit = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Customer not found"));
        checkIfEmailExists(userRequestDTO, userToEdit);

        String password = bCryptPasswordEncoder.encode(userRequestDTO.getPassword());

        userToEdit.setPassword(password);
        userToEdit.setEmail(userRequestDTO.getEmail());
        userToEdit.setPhoneNumber(userRequestDTO.getPhoneNumber());
        userToEdit.setCity(userRequestDTO.getCity());
        userToEdit.setAdmin(userRequestDTO.getAdmin());
        userToEdit.setFirstName(userRequestDTO.getFirstName());
        userToEdit.setLastName(userRequestDTO.getLastName());

        userRepository.save(userToEdit);
        return UserResponseDTO.responseFromCustomer(userToEdit);
    }



    private User customerRequestDtoToCustomer(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setCity(userRequestDTO.getCity());
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        user.setAdmin(userRequestDTO.getAdmin());

        return user;
    }

    private boolean isNewEmailRegistered(UserRequestDTO userRequestDTO) {
        return userRepository.findByEmail(userRequestDTO.getEmail()).isPresent();
    }

    private boolean isNewEmailDifferent(UserRequestDTO userRequestDTO, User userToUpdate) {
        return !userToUpdate.getEmail().equalsIgnoreCase(userRequestDTO.getEmail());
    }

    private void checkIfEmailExists(UserRequestDTO userRequestDTO, User userToUpdate) {
        if (isNewEmailDifferent(userRequestDTO, userToUpdate) && isNewEmailRegistered(userRequestDTO)) {
            throw new UsernameTakenException("Email is already registered");
        }
    }




}
