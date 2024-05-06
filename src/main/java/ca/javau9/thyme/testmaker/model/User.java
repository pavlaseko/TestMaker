package ca.javau9.thyme.testmaker.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    private String password;

    private String role;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Test> tests = new ArrayList<>();

    public User(String name, String email, String password, String role) {
        this.nickname = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
