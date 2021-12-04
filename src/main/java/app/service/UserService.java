package app.service;

import app.container.dto.UserDto;
import app.container.dto.request.UserRequestData;
import app.container.dto.response.ResponseDto;
import app.container.dto.response.StatusDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {
    ResponseDto addUser(UserRequestData userRequestData);

    ResponseDto editUser(UUID uuid, UserRequestData userRequestData);

    List<UserDto> getUsersList();

    ResponseDto deleteUser(UserRequestData userRequestData);

    StatusDto getStatusInfo();

}
