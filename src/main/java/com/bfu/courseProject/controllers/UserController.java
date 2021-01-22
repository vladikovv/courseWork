package com.bfu.courseProject.controllers;

import com.bfu.courseProject.dtos.customer.UserRequestDTO;
import com.bfu.courseProject.dtos.customer.UserResponseDTO;
import com.bfu.courseProject.services.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasAuthority('admin')")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/customers")
    public List<UserResponseDTO> getAllCustomers() {
        return userService.getAll();
    }

    @GetMapping("/customers/{customerId}")
    public UserResponseDTO getCustomerById(@PathVariable("customerId") @NotNull Long customerId) {
            return userService.getById(customerId);
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createCustomer(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.create(userRequestDTO);
    }

    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") @NotNull Long customerId) {
        userService.delete(customerId);
    }

    @PutMapping("/customers/{customerId}")
    public UserResponseDTO editCustomer(@RequestBody UserRequestDTO userRequestDTO,
                                        @PathVariable @NotNull Long customerId) {
        return userService.edit(userRequestDTO, customerId);
    }
}
