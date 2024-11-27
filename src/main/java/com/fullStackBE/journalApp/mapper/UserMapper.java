package com.fullStackBE.journalApp.mapper;

import com.fullStackBE.journalApp.dto.UserDTO;
import com.fullStackBE.journalApp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO mapUserToUserDTO(User user);
    User mapUserDTOToUser(UserDTO userDTO);

    List<UserDTO> mapUserToUserDTO(List<User>  user);
}
