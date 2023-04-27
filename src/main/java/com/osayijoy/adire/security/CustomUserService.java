package com.osayijoy.adire.security;

import com.osayijoy.adire.entity.Designer;
import com.osayijoy.adire.exception.NotFoundException;
import com.osayijoy.adire.repository.DesignerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {
    private final DesignerRepository designerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("userService loadUserByUserName - email :: [{}] ::", email);
        Designer designer = designerRepository.findByEmail(email)
                .orElseThrow(
                        () -> {
                            throw new NotFoundException("User not found");
                        }
                );
        Collection<SimpleGrantedAuthority> authorities =
                Collections.singleton(new SimpleGrantedAuthority(designer.getRole()));
        return new org.springframework.security.core.userdetails.User(designer.getEmail(), designer.getPassword(), authorities);
    }
}
