package ca.javau9.thyme.testmaker.controller;
import ca.javau9.thyme.testmaker.model.SignUpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ca.javau9.thyme.testmaker.model.Admin;
import ca.javau9.thyme.testmaker.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ApiController {

    private final AdminService adminService;

    @Autowired
    public ApiController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
        Admin admin = adminService.getAdminByEmail(email);
        if (admin != null && password.equals(admin.getPassword())) {
            session.setAttribute("admin", admin);
            return ResponseEntity.ok().body("Login successful");
        } else {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest) {
        String nickname = signUpRequest.getNickname();
        String email = signUpRequest.getEmail();
        String password = signUpRequest.getPassword();

        if (adminService.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        Admin newAdmin = new Admin(nickname, email, password);
        adminService.createAdmin(newAdmin);

        return ResponseEntity.ok().body("Signup successful");
    }

    @GetMapping("/profile")
    public ResponseEntity<String> profilePage(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            return ResponseEntity.ok().body("Welcome to your profile, " + admin.getNickname());
        } else {
            return ResponseEntity.badRequest().body("Admin information not found");
        }
    }
}

