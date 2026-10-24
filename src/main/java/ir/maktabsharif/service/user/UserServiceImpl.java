package ir.maktabsharif.service.user;

import ir.maktabsharif.command.Commandable;
import ir.maktabsharif.command.UserCommand;
import ir.maktabsharif.dao.UserDao;
import ir.maktabsharif.dto.user.UserRequestDto;
import ir.maktabsharif.dto.user.UserResponseDto;
import ir.maktabsharif.exception.RepositoryException;
import ir.maktabsharif.exception.ServiceException;
import ir.maktabsharif.exception.ValidationException;
import ir.maktabsharif.repository.user.UserRepository;
import ir.maktabsharif.util.Ruls;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService{


    private UserRepository userRepository;
    private UserCommand commandable;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
        this.commandable = new UserCommand();
    }


    private void validation(UserRequestDto userRequestDto){
        Ruls.check(userRequestDto.getEmail().isBlank(),"your email is empty");
        Ruls.check(userRequestDto.getName().isBlank(),"your name is empty");
    }

    @Override
    public UserResponseDto save(UserRequestDto userRequesrDto) {
       try {
           validation(userRequesrDto);
           UserDao userDao = commandable.requestToDao(userRequesrDto);
           return commandable.daoToResponse(userRepository.create(userDao));
       }catch (ValidationException | RepositoryException e){
           throw new ServiceException("your operation is failed => "+e.getMessage());
       }
    }

    @Override
    public UserResponseDto update(Integer id, UserRequestDto userRequesrDto) {
        try {
            validation(userRequesrDto);
            UserDao userDao = commandable.requestToDao(userRequesrDto);
            return commandable.daoToResponse(userRepository.update(id,userDao));
        }catch (ValidationException | RepositoryException e){
            throw new ServiceException("your operation is failed => "+e.getMessage());
        }
    }

    @Override
    public Integer delete(Integer id) {
        try {
            return userRepository.delete(id);
        }catch (RepositoryException e){
            throw new ServiceException("your operation is failed => "+e.getMessage());
        }
    }

    @Override
    public UserResponseDto findById(Integer id) {
        try {
            return commandable.daoToResponse(userRepository.read(id));
        }catch (RepositoryException e){
            throw new ServiceException("your operation is failed => "+e.getMessage());
        }
    }

    @Override
    public List<UserResponseDto> findAll() {
        try {
            List<UserDao> userDaos =  userRepository.findAll();
            List<UserResponseDto> userResponseDtos = new ArrayList<>();
            for (UserDao u : userDaos){
                userResponseDtos.add(commandable.daoToResponse(u));
            }
            return userResponseDtos;
        }catch (ValidationException | RepositoryException e){
            throw new ServiceException("your operation is failed => "+e.getMessage());
        }
    }
}
