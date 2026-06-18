package com.placementtracker.placement_tracker.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotNull
    @NotBlank
    @Length(min = 3)
    private String name;

    @Email
    @NotBlank
    private String email;

    @Length(min = 8)
    @Pattern (regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must contain at least 8 characters, one uppercase letter, one lowercase letter and one special character" )
    private String password;
}
