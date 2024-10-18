package br.com.gcd.systemwl.mapper;

import br.com.gcd.systemwl.dto.UserDto;
import br.com.gcd.systemwl.dto.UserPostDto;
import br.com.gcd.systemwl.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toUserEntity(UserPostDto userPostDto);

    UserPostDto toUserPostDto(UserEntity userEntity);

    UserDto toUserDto(UserEntity userEntity);

    List<UserDto> toListUserDto(List<UserEntity> userEntityList);
}
