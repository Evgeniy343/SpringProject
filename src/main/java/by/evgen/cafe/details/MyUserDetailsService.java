package by.evgen.cafe.details;


import by.evgen.cafe.model.impl.CafeUserModel;
import by.evgen.cafe.repository.CafeUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final CafeUserRepository userRepository;

    public MyUserDetailsService(CafeUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CafeUserModel user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        user.setPassword(user.getPassword());
        return new MyUserDetails(user);
    }
}
