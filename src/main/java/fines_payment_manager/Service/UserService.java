package fines_payment_manager.Service;

import fines_payment_manager.Model.User;
import fines_payment_manager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public String encodePassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public int checkUser(String email, String password) {
        User user = getUserByEmail(email);
        if (user == null) {
            return -1;
        }
        if (user.getPassword().equals(encodePassword(password))) {
            if(user.getRole().equals("police")){
                return 1;
            }
            else if(user.getRole().equals("post_office")){
                return 2;
            }
        }
        return 0;
    }

}
