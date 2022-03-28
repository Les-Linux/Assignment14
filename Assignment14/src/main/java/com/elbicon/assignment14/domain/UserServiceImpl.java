package com.elbicon.assignment14.domain;

import com.elbicon.assignment14.service.UserService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;


@Data
@Service
public class UserServiceImpl implements UserService {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
}
