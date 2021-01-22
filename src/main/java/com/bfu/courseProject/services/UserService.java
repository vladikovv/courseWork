package com.bfu.courseProject.services;

import com.bfu.courseProject.dtos.customer.UserRequestDTO;
import com.bfu.courseProject.dtos.customer.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAll();

    UserResponseDTO getById(Long id);

    UserResponseDTO create(UserRequestDTO userRequestDTO);

    void delete(Long id);

    UserResponseDTO edit(UserRequestDTO userRequestDTO, Long id);

}
