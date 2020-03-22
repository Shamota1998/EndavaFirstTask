package annotationTests;
import com.endava.internship.s2020.springcoretask.annotations.NameSizeValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.validation.ConstraintValidatorContext;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class NameSizeValidatorTest {
    @Mock
    ConstraintValidatorContext context;
    @InjectMocks
    NameSizeValidator validator;
    @Test
    void shouldReturnFalseForInvalidName() {
        String name = "p";
        assertThat(validator.isValid(name, context)).isFalse();
    }
    @Test
    void shouldReturnTrueForValidName(){
        String name = "Panasonic";
        assertThat(validator.isValid(name,context)).isTrue();
    }
    @Test
    void shouldReturnFalseForNullName(){
        assertThat(validator.isValid(null, context)).isFalse();
    }
}