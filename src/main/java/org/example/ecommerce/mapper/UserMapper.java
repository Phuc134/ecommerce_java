package org.example.ecommerce.mapper;

import org.example.ecommerce.dto.request.UserRequest;
import org.example.ecommerce.dto.response.UserResponse;
import org.example.ecommerce.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);
    UserResponse toUserResponse(User user);
}
