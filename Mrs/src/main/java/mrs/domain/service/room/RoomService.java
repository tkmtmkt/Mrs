package mrs.domain.service.room;

import java.time.LocalDate;
import java.util.List;

import mrs.domain.model.MeetingRoom;
import mrs.domain.model.ReservableRoom;
import mrs.domain.repository.room.MeetingRoomRepository;
import mrs.domain.repository.room.ReservableRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoomService {
	@Autowired
	ReservableRoomRepository reservableRoomRepository;
	@Autowired
	MeetingRoomRepository meetingRoomRepository;

	public List<ReservableRoom> findReservableRooms(LocalDate date) {
		return reservableRoomRepository.findByReservableRoomId_reservedDateOrderByReservableRoomId_roomIdAsc(date);
	}

	public MeetingRoom findMeetingRoom(Integer roomId) {
		// �����������G���e�B�e�B�ɃZ�b�g
		MeetingRoom probe = new MeetingRoom();
		probe.setRoomId(roomId);

		// ������������Example�C���X�^���X�𐶐�
		Example<MeetingRoom> example = Example.of(probe);
		return meetingRoomRepository.findOne(example).get();
	}
}
