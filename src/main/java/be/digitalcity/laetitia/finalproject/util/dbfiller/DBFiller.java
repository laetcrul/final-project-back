package be.digitalcity.laetitia.finalproject.util.dbfiller;

import be.digitalcity.laetitia.finalproject.models.entities.Invitation;
import be.digitalcity.laetitia.finalproject.repositories.*;
import be.digitalcity.laetitia.finalproject.util.enums.STATUS;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DBFiller implements InitializingBean{
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final GroupRepository groupRepository;
    private final PasswordEncoder encoder;
    private final AlertEventRepository alertEventRepository;
    private final EventRepository eventRepository;
    private final InvitationRepository invitationRepository;

    public DBFiller(UserRepository userRepository, TeamRepository teamRepository, GroupRepository groupRepository, PasswordEncoder encoder, AlertEventRepository alertEventRepository, EventRepository eventRepository, InvitationRepository invitationRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.groupRepository = groupRepository;
        this.encoder = encoder;
        this.alertEventRepository = alertEventRepository;
        this.eventRepository = eventRepository;
        this.invitationRepository = invitationRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        List<User> toInsert = List.of(
//                new User("admin", encoder.encode("pass"), "https://actiformpro.fr/wp-content/uploads/2021/04/avatar-512.png", teamRepository.getById(2L), groupRepository.getById(2L), true, true, true, true ),
//                new User("user", encoder.encode("pass"), "https://audit-controle-interne.com/wp-content/uploads/2019/03/avatar-user-teacher-312a499a08079a12-512x512.png", teamRepository.getById(5L), groupRepository.getById(1L), true, true, true, true )
//        );
//
//        userRepository.saveAll(toInsert);

//        Invitation invitation = new Invitation();
//        invitation.setMessage("Hey let's go drink!");
//        invitation.setEvent(eventRepository.getById(2L));
//        invitation.setRecipient(userRepository.getById(1L));
//        invitation.setSender(userRepository.getById(2L));
//        invitation.setStatus(STATUS.PENDING);
//
//        invitationRepository.save(invitation);
//
//
//        Invitation invitation2 = new Invitation();
//        invitation2.setMessage("Hey let's go dance!");
//        invitation2.setEvent(eventRepository.getById(1L));
//        invitation2.setRecipient(userRepository.getById(2L));
//        invitation2.setSender(userRepository.getById(1L));
//        invitation2.setStatus(STATUS.PENDING);
//
//        invitationRepository.save(invitation2);
    }
}