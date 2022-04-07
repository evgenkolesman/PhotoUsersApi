package ru.koleson.photousersapi.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AccountDetailRequestModel {
    @NotNull(message = "Firstname can`t be empty")
    private String firstName;

    @NotNull(message = "Lastname can`t be empty")
    private String lastName;

    @NotNull(message = "Email can`t be empty")
    @Email
    private String email;

    @NotNull(message = "Password can`t be empty")
    @Size(min = 8, max = 16)
    private String password;
}
