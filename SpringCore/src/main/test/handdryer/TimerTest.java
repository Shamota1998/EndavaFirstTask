package handdryer;

import com.endava.internship.s2020.springcoretask.enums.SpeedType;
import com.endava.internship.s2020.springcoretask.handryerclasses.Speed;
import com.endava.internship.s2020.springcoretask.handryerclasses.Timer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TimerTest {
    @Mock
    Speed speed;
    @InjectMocks
    Timer timer;

    @Test
    public void shouldReturnTimeForLowSpeed() {
        when(speed.getSpeedType()).thenReturn(SpeedType.LOW);
        assertThat(timer.getTimeOfWork()).isEqualTo("60s");
    }

    @Test
    public void shouldReturnTimeForMediumSpeed() {
        when(speed.getSpeedType()).thenReturn(SpeedType.MEDIUM);
        assertThat(timer.getTimeOfWork()).isEqualTo("40s");
    }

    @Test
    public void shouldReturnTimeForHighSpeed() {
        when(speed.getSpeedType()).thenReturn(SpeedType.HIGH);
        assertThat(timer.getTimeOfWork()).isEqualTo("20s");
    }
}
