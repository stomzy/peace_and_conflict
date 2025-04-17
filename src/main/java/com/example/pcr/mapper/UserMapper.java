package com.example.pcr.mapper;

import com.example.pcr.dto.reponse.UserResponseDTO;
import com.example.pcr.dto.request.UserRequestDTO;
import com.example.pcr.entity.User;

public class UserMapper {
    public static UserResponseDTO toUserResponseDTO(User user) {
        if (user == null) return null;
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());
        return dto;
    }

    public static User toUserEntity(UserRequestDTO dto) {
        if (dto == null) return null;
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword()); // make sure to encode it before saving!
        user.setRole(dto.getRole());
        user.setStatus(dto.getStatus());
        return user;
    }

}
