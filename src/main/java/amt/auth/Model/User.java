package amt.auth.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;
    @Column(unique = true)
    @NonNull
    @Getter
    @Setter
    private String username;
    @Column
    @NonNull
    @Setter
    @Getter
    private String password;

    @Column
    @NonNull
    @Getter
    @Setter
    private String role;
}
