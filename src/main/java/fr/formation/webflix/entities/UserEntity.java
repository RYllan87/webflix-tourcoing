package fr.formation.webflix.entities;

import fr.formation.webflix.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Calendar;
import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	@NotBlank(message = "L'email doit être rempli. Ex: fatima@gmail.com")
	@Email(message = "Attention l'email n'est pas formatté correctement. Ex: jennifer@gmail.com")
	private String email;

	@Column(nullable = false)
	@Pattern(
			regexp = "[a-z0-9\\-_.@&$/]{8,100}",
			flags = {Pattern.Flag.DOTALL, Pattern.Flag.CASE_INSENSITIVE},
			message = "Le mot de passe doit comporter au minimum 8 caractères."
	)
	private String password;

	@Transient
	private String confirmPassword;

	@Temporal(TemporalType.DATE)
	private Calendar birthday;

	@Column(nullable = false, columnDefinition = "VARCHAR(10) default 'MR'")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(length = 100, nullable = false)
	private String firstname;
	@Column(length = 100, nullable = false)
	private String lastname;

	@Column(length = 100, nullable = false)
	private String country;

	private Calendar dateCreated;
	private Calendar dateModified;
	private Calendar dateDeleted;

	@OneToMany(mappedBy = "user")
	private Collection<ProfileEntity> profiles;
}
