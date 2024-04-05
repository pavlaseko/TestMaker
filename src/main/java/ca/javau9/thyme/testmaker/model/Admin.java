package ca.javau9.thyme.testmaker.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Test> tests = new ArrayList<>();

    public Admin(String name, String email, String password) {
        this.nickname = name;
        this.email = email;
        this.password = password;
    }

    public Admin() { }

    public void addTest(Test test) {
        if (test == null) {
            throw new IllegalArgumentException("Test cannot be null");
        }
        tests.add(test);
        test.setAdmin(this);
    }
}
