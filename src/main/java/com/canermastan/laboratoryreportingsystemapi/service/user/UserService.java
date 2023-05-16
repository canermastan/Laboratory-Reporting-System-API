package com.canermastan.laboratoryreportingsystemapi.service.user;

import com.canermastan.laboratoryreportingsystemapi.entity.User;

public interface UserService {
    User findByEmail(String email);
}
