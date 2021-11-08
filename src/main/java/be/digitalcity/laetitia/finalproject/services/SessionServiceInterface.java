package be.digitalcity.laetitia.finalproject.services;

import be.digitalcity.laetitia.finalproject.models.dtos.UserDTO;
import be.digitalcity.laetitia.finalproject.models.forms.LoginForm;

public interface SessionServiceInterface {
    UserDTO login(LoginForm form);
}