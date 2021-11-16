//package be.digitalcity.laetitia.finalproject.util.dbfiller;
//
//import be.digitalcity.laetitia.finalproject.models.entities.Invitation;
//import be.digitalcity.laetitia.finalproject.models.entities.User;
//import be.digitalcity.laetitia.finalproject.repositories.*;
//import be.digitalcity.laetitia.finalproject.util.enums.STATUS;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class DBFiller implements InitializingBean{
//    private final UserRepository userRepository;
//    private final TeamRepository teamRepository;
//    private final GroupRepository groupRepository;
//    private final PasswordEncoder encoder;
//    private final AlertEventRepository alertEventRepository;
//    private final EventRepository eventRepository;
//    private final InvitationRepository invitationRepository;
//
//    public DBFiller(UserRepository userRepository, TeamRepository teamRepository, GroupRepository groupRepository, PasswordEncoder encoder, AlertEventRepository alertEventRepository, EventRepository eventRepository, InvitationRepository invitationRepository) {
//        this.userRepository = userRepository;
//        this.teamRepository = teamRepository;
//        this.groupRepository = groupRepository;
//        this.encoder = encoder;
//        this.alertEventRepository = alertEventRepository;
//        this.eventRepository = eventRepository;
//        this.invitationRepository = invitationRepository;
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        List<User> toInsert = List.of(
////                new User("john", encoder.encode("pass"), "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=687&q=80", teamRepository.getById(2L), groupRepository.getById(1L), true, true, true, true ),
////                new User("deborah", encoder.encode("pass"), "https://images.unsplash.com/photo-1489424731084-a5d8b219a5bb?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=687&q=80", teamRepository.getById(5L), groupRepository.getById(1L), true, true, true, true ),
////                new User("jules", encoder.encode("pass"), "https://images.unsplash.com/photo-1531427186611-ecfd6d936c79?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80", teamRepository.getById(6L), groupRepository.getById(1L), true, true, true, true ),
////                new User("alice", encoder.encode("pass"), "https://images.unsplash.com/photo-1580489944761-15a19d654956?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=761&q=80", teamRepository.getById(7L), groupRepository.getById(1L), true, true, true, true ),
////                new User("mary", encoder.encode("pass"), "https://images.unsplash.com/photo-1531123897727-8f129e1688ce?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=687&q=80", teamRepository.getById(8L), groupRepository.getById(1L), true, true, true, true ),
////                new User("eleanor", encoder.encode("pass"), "https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80", teamRepository.getById(9L), groupRepository.getById(1L), true, true, true, true )
//                new User("admin", encoder.encode("pass"), "https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80", teamRepository.getById(9L), groupRepository.getById(1L), true, true, true, true )
//        );
////
//        userRepository.saveAll(toInsert);
//
////        Invitation invitation = new Invitation();
////        invitation.setMessage("Hey let's go drink!");
////        invitation.setEvent(eventRepository.getById(2L));
////        invitation.setRecipient(userRepository.getById(1L));
////        invitation.setSender(userRepository.getById(2L));
////        invitation.setStatus(STATUS.PENDING);
////
////        invitationRepository.save(invitation);
////
////
////        Invitation invitation2 = new Invitation();
////        invitation2.setMessage("Hey let's go dance!");
////        invitation2.setEvent(eventRepository.getById(1L));
////        invitation2.setRecipient(userRepository.getById(2L));
////        invitation2.setSender(userRepository.getById(1L));
////        invitation2.setStatus(STATUS.PENDING);
////
////        invitationRepository.save(invitation2);
//    }
//}