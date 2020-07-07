package id.test.crud.service;

import id.test.crud.entity.User;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserService {

    public List<User> getAlluser();

    public User getByUsername(String username);

    public void saveUser(User user);

    public void deleteUser(User user);
}
