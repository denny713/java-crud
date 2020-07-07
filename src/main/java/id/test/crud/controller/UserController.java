package id.test.crud.controller;

import id.test.crud.entity.User;
import id.test.crud.model.Response;
import id.test.crud.model.UserModel;
import id.test.crud.service.UserService;
import id.test.crud.util.Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class UserController {

    public static Response response = new Response();

    @Autowired
    private UserService userService;

    public User setUser(UserModel user) throws NoSuchAlgorithmException {
        User userSave = new User();
        userSave.setUsername(user.getUsername());
        userSave.setNama(user.getNama());
        userSave.setPassword(Crypt.encryptText(user.getPassword()));
        userSave.setStatus(user.getStatus());
        return userSave;
    }

    @GetMapping("/user")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAlluser();
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public User getByUsername(@PathVariable("id") String id) {
        return userService.getByUsername(id);
    }

    @PostMapping("/user/save")
    @ResponseBody
    public Response saveUser(@Valid @RequestBody UserModel user) {
        try {
            userService.saveUser(setUser(user));
            response.setResult(true);
            response.setMessage("Success Save Data");
        } catch (Exception f) {
            response.setResult(false);
            response.setMessage(f.getMessage());
        }
        return response;
    }

    @PostMapping("/user/update")
    @ResponseBody
    public Response updateUser(@Valid @RequestBody UserModel user) {
        try {
            User xuser = userService.getByUsername(user.getUsername());
            if (xuser != null) {
                userService.saveUser(setUser(user));
                response.setResult(true);
                response.setMessage("Success Update Data");
            } else {
                response.setResult(false);
                response.setMessage("User Not Found");
            }
        } catch (Exception e) {
            response.setResult(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/user/delete")
    @ResponseBody
    public Response deleteUser(@Valid @RequestBody String id) {
        try {
            User user = userService.getByUsername(id);
            if (user != null) {
                userService.deleteUser(user);
                response.setResult(true);
                response.setMessage("Success Delete Data");
            } else {
                response.setResult(false);
                response.setMessage("User Not Found");
            }
        } catch (Exception g) {
            response.setResult(false);
            response.setMessage(g.getMessage());
        }
        return response;
    }
}
