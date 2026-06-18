package com.placementtracker.placement_tracker.dto.response;

import com.placementtracker.placement_tracker.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private Long id;
    private String name;
    private String email;
    private Role role;
}
