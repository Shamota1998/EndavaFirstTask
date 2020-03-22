package handdryer;
import com.endava.internship.s2020.springcoretask.enums.SpeedType;
import com.endava.internship.s2020.springcoretask.handryerclasses.HandDryer;
import com.endava.internship.s2020.springcoretask.handryerclasses.Producer;
import com.endava.internship.s2020.springcoretask.handryerclasses.Speed;
import com.endava.internship.s2020.springcoretask.handryerclasses.Timer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.HashSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HandDryerTest {
    @Mock
    Speed speed;
    @Mock
    Timer timer;
    @Mock
    ConstraintViolation<Producer> violation;
    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    Validator validator;
    @InjectMocks
    HandDryer dryer;
    @Test
    void shouldReturnTimeForLowSpeed() {
        when(speed.getSpeedType()).thenReturn(SpeedType.LOW);
        when(timer.getTimeOfWork()).thenReturn("60s");
        assertThat(dryer.getInfo()).isEqualTo("Current speed = LOW\n" +
                "Time to dry = 60s");
    }
    @Test
    void shouldReturnValidInfoAboutProducer() {
        Producer producer = new Producer("Panasonic", LocalDate.of(2025, 12, 12));
        HashSet<ConstraintViolation<Producer>> set = new HashSet<>();
        set.add(violation);
        when(validator.validate(producer)).thenReturn(set);
        when(violation.getMessageTemplate()).thenReturn("Year should be in past");
        assertThat(dryer.getProducerDetails(producer)).isEqualTo("Year should be in past");
    }
    @Test
    void shouldReturnValidDataAboutProducer() {
        Producer producer = new Producer("Panasonic", LocalDate.of(2005, 12, 12));
        HashSet<ConstraintViolation<Producer>> set = new HashSet<>();
        when(validator.validate(producer)).thenReturn(set);
        assertThat(dryer.getProducerDetails(producer)).isEqualTo("Producer: Panasonic\n" +
                "Foundation year: 2005-12-12");
    }
}
