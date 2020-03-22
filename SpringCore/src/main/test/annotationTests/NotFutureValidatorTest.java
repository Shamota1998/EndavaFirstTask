package annotationTests;

import com.endava.internship.s2020.springcoretask.annotations.NameSizeValidator;
import com.endava.internship.s2020.springcoretask.annotations.NotFutureValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class NotFutureValidatorTest {
    @Mock
    ConstraintValidatorContext context;
    @InjectMocks
    NotFutureValidator validator;

    @Test
    public void shouldReturnTrueForPastDate() {
        assertThat(validator.isValid(LocalDate.of(1999,12,12), context)).isTrue();
    }

    @Test
    public void shouldReturnTrueForValidName(){
        assertThat(validator.isValid(LocalDate.of(2025, 12,12), context)).isFalse();
    }
}
