package ir.maktabsharif.command;

import ir.maktabsharif.dao.BaseModel.BaseModel;
import ir.maktabsharif.dao.UserDao;
import ir.maktabsharif.dto.user.UserRequestDto;
import ir.maktabsharif.dto.user.UserResponseDto;

public class UserCommand implements Commandable<UserDao,Integer, UserRequestDto, UserResponseDto>{


    @Override
    public UserDao requestToDao(UserRequestDto userRequestDto) {
        UserDao userDao = UserDao.builder()
                .email(userRequestDto.getEmail())
                .name(userRequestDto.getName())
                .rols(userRequestDto.getRols())
                .build();
        userDao.setId(userRequestDto.getId());
        return userDao;
    }

    @Override
    public UserResponseDto daoToResponse(UserDao userDao) {
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(userDao.getId())
                .email(userDao.getEmail())
                .name(userDao.getName())
                .rols(userDao.getRols())
                .build();

        return userResponseDto;
    }
}
