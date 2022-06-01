package hu.nye.project.datingapp.security;

import java.util.Collections;
import java.util.Optional;

import hu.nye.project.datingapp.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom implementation of {@link UserDetailsService} to retrieve a given user
 * by its name.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Dummy implementation of a user look-up.
        // You should place a valid implementation here, which queries a user from the
        // database, then
        // populate the result of this method with the correct values.
        Optional<hu.nye.project.datingapp.entity.User> optionalUser = findUser(username);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        return new User(optionalUser.get().getUsername(), optionalUser.get().getPassword(), Collections.emptyList());
    }

    private Optional<hu.nye.project.datingapp.entity.User> findUser(String username) {
        return this.userRepository.findUserByUsername(username);
    }

}