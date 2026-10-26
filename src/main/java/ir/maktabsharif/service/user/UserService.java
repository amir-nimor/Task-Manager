package ir.maktabsharif.service.user;

import ir.maktabsharif.dao.UserDao;
import ir.maktabsharif.dto.user.UserRequestDto;
import ir.maktabsharif.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto save(UserRequestDto userRequesrDto);
    UserResponseDto update(Integer id , UserRequestDto userRequesrDto);
    Integer delete(Integer id);
    UserResponseDto findById(Integer id);
    List<UserResponseDto> findAll();
    UserResponseDto findBuEmail(String email);

}
