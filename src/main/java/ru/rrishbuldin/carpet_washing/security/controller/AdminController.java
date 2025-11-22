package ru.rrishbuldin.carpet_washing.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.rrishbuldin.carpet_washing.modules.user.dto.UserDto;
import ru.rrishbuldin.carpet_washing.security.enums.RoleName;
import ru.rrishbuldin.carpet_washing.security.service.CustomUserDetailService;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CustomUserDetailService customUserDetailService;

    @PatchMapping("/user_role_update")
    @PreAuthorize("hasPermission(null, 'MANAGE_USERS')")
    public ResponseEntity<?> userUpdate(@RequestParam Long userId,
                                        @RequestParam RoleName roleName) {
        UserDto userDto = customUserDetailService.updateUserRole(userId, roleName);
        return ResponseEntity.ok(userDto);
    }
}
