package app.service.impl;

import app.container.dto.UserDto;
import app.container.dto.request.UserRequestData;
import app.container.dto.response.ResponseDto;
import app.container.entities.UserEntity;
import app.container.enums.OperationStatuses;
import app.container.enums.OperationTypes;
import app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
@ExtendWith({MockitoExtension.class})
public class JUnitUserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    private final UserDto userDto = getUserDto();
    private final UserEntity user = getNewUser();
    private final UUID userId = UUID.randomUUID();
    private final UserRequestData userRequestData = getUserRequestData();

    private UserRequestData getUserRequestData() {
        return UserRequestData.builder()
                .name("Name")
                .phone("0123456").build();
    }

    @Before
    public void setup() {
        when(userRepository.save(any())).thenReturn(any());
    }

    @Test
    public void addUserSuccess() {
        ResponseDto responseDto = ResponseDto.builder()
                .operation_status(OperationStatuses.SUCCESS)
                .operation_type(OperationTypes.OPERATION_ADD)
                .build();
        Assertions.assertNotNull(userService.addUser(userRequestData));
        Assertions.assertEquals(userService.addUser(userRequestData), responseDto);
    }

    @Test
    public void editUserEmpty() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        Assertions.assertNotNull(userService.editUser(userId, userRequestData));
    }

    @Test
    public void editUserSuccess() {
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        Assertions.assertNotNull(userService.editUser(userId, userRequestData));
    }

    @Test
    public void getUserListSuccess() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        Assertions.assertNotNull(userService.getUsersList());
    }

    @Test
    public void deleteUser() {
        when(userRepository.findByPhone(anyString())).thenReturn(user);
        Assertions.assertNotNull(userService.deleteUser(userRequestData));
    }


    @Test
    public void getStatusInfoTest() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        Assertions.assertNotNull(userService.getStatusInfo());
        Assertions.assertEquals(200, userService.getStatusInfo().getStatus().value());
    }

    private UserDto getUserDto() {
        return new UserDto(userId, "UserName", "12345678");
    }

    private UserEntity getNewUser() {
        return new UserEntity(userId, "UserName", "12345678");
    }
}