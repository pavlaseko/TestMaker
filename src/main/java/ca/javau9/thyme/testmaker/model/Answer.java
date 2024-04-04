package ca.javau9.thyme.testmaker.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Answer text is mandatory")
    private String answerText;

    @ManyToOne
    private Question question;

    public Answer () { }

    public Answer(String answerText, Question question) {
        this.answerText = answerText;
        this.question = question;
    }
}
