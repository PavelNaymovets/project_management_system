package com.digdes.pms.auth.util;

import com.digdes.pms.exception.AuthException;
import com.digdes.pms.exception.HasDeletedStatusException;
import com.digdes.pms.model.employee.Employee;
import com.digdes.pms.model.employee.Role;
import com.digdes.pms.repository.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.digdes.pms.model.employee.EmployeeStatus.REMOTE;

@Service
@RequiredArgsConstructor
public class AuthEmployeeService implements UserDetailsService {
    private static final Logger authenticationLog = LoggerFactory.getLogger("auth-log");
    private final EmployeeRepository employeeRepository;
    private final MessageSource messageSource;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new AuthException(
                        messageSource.getMessage("authentication.login.password.incorrect", null, Locale.ENGLISH)));

        if (employee.getStatus().equals(REMOTE.getStatus())) {
            throw new HasDeletedStatusException(
                    messageSource.getMessage("employee.has.deleted.status", null, Locale.ENGLISH));
        }

        authenticationLog.debug(messageSource.getMessage(
                "authentication.user.successfully.load", null, Locale.ENGLISH) + login);

        return new org.springframework.security.core.userdetails.User(employee.getLogin(),
                employee.getPassword(),
                mapRolesToAuthorities(employee.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
