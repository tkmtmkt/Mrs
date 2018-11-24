package mrs.app.reservation;

import java.io.Serializable;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@EndTimeMustBeAfterStartTime(message = "�I�������͊J�n��������ɂ��Ă�������")
public class ReservationForm implements Serializable {
	@NotNull(message = "�K�{�ł�")
	@ThirtyMinutesUnit(message = "30���P�ʂœ��͂��Ă�������")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime startTime;

	@NotNull(message = "�K�{�ł�")
	@ThirtyMinutesUnit(message = "30���P�ʂœ��͂��Ă�������")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime endTime;

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
}