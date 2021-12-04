package app.controller;

import app.container.dto.UserDto;
import app.container.dto.request.UserRequestData;
import app.container.dto.response.ResponseDto;
import app.container.dto.response.StatusDto;
import app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @GetMapping("/user/list")
    public List<UserDto> getUsersData() {
        return userService.getUsersList();
    }

    @PostMapping("/user/add")
    public ResponseDto addUserData(@RequestBody UserRequestData userRequestData) {
        return userService.addUser(userRequestData);
    }

    @GetMapping("/user/edit")
    public ResponseDto editUserData(@PathVariable UUID uuid,
                                    @RequestBody UserRequestData userRequestData) {
        return userService.editUser(uuid, userRequestData);
    }

    @GetMapping("/user/delete")
    public ResponseDto deleteUserData(@RequestBody UserRequestData userRequestData) {
        return userService.deleteUser(userRequestData);
    }

    @GetMapping("/status")
    public StatusDto status() {
        final ResponseEntity<StatusDto> ok = ResponseEntity.ok(userService.getStatusInfo());
        return StatusDto.builder().status(ok.getStatusCode()).build();
    }
}
