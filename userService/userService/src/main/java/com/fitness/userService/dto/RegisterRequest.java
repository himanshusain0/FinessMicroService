package com.fitness.userService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RegisterRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    private String email;
    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&]).+$",
            message = "Password must contain uppercase, lowercase, number and special character"
    )
    private String password ;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
    private String lastName;


}
