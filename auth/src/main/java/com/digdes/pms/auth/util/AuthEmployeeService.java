package com.digdes.pms.auth.util;

import com.digdes.pms.exception.EmployeeHasDeletedStatusException;
import com.digdes.pms.model.employee.Employee;
import com.digdes.pms.model.employee.Role;
import com.digdes.pms.repository.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthEmployeeService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Работник с логином '%s' не найден", login)));

        if (employee.isStatus() == false) {
            throw new EmployeeHasDeletedStatusException("Нельзя аутентифицировать работника со статусом - удаленный");
        }

        return new org.springframework.security.core.userdetails.User(employee.getLogin(),
                employee.getPassword(),
                mapRolesToAuthorities(employee.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
