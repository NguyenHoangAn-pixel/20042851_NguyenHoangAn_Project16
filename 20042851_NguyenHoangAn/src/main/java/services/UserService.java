package services;

import java.util.List;
import models.User;
import repositories.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean registerUser(String email, String password, String name) {
        User user = new User(email, password, name);
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
