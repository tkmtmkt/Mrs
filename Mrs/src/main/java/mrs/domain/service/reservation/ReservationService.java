package mrs.domain.service.reservation;

import java.util.List;
import java.util.Objects;

import mrs.domain.model.*;
import mrs.domain.repository.reservation.ReservationRepository;
import mrs.domain.repository.room.ReservableRoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationService {
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	ReservableRoomRepository reservableRoomRepository;

	public List<Reservation> findReservations(ReservableRoomId reservableRoomId) {
		return reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId);
	}

	public Reservation reserve(Reservation reservation) {
		ReservableRoomId reservableRoomId = reservation.getReservableRoom().getReservableRoomId();
		// �ߊσ��b�N
		ReservableRoom reservable = reservableRoomRepository.findOneForUpdateByReservableRoomId(reservableRoomId);
		if (reservable == null) {
			throw new UnavailableReservationException("���͂̓��t�E�����̑g�ݍ��킹�͗\��ł��܂���B");
		}
		// �d���`�F�b�N
		boolean overlap = reservationRepository
				.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId).stream()
				.anyMatch(x -> x.overlap(reservation));
		if (overlap) {
			throw new AlreadyReservedException("���͂̎��ԑт͂��łɗ\��ς݂ł��B");
		}
		// �\����̓o�^
		reservationRepository.save(reservation);
		return reservation;
	}

	@PreAuthorize("hasRole('ADMIN') or #reservation.user.userId == principal.user.userId")
	public void cancel(@P("reservation") Reservation reservation) {
		reservationRepository.delete(reservation);
	}

	public Reservation findOne(Integer reservationId) {
		// �����������G���e�B�e�B�ɃZ�b�g
		Reservation probe = new Reservation();
		probe.setReservationId(reservationId);

		// ������������Example�C���X�^���X�𐶐�
		Example<Reservation> example = Example.of(probe);
		return reservationRepository.findOne(example).get();
	}
}
