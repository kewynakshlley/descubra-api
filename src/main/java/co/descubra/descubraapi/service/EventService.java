package co.descubra.descubraapi.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import co.descubra.descubraapi.core.model.Event;
import co.descubra.descubraapi.core.model.EventFeedback;
import co.descubra.descubraapi.core.dto.EventFeedbackDTO;
import co.descubra.descubraapi.core.dto.EventRatingAVGByAdministratorDTO;
import co.descubra.descubraapi.core.dto.EventRatingAVGByCategoryDTO;
import co.descubra.descubraapi.core.dto.EventRatingAverageDTO;
import co.descubra.descubraapi.core.dto.InterestCountDTO;
import co.descubra.descubraapi.core.model.Administrator;
import co.descubra.descubraapi.core.model.AdministratorEventInterest;
import co.descubra.descubraapi.exceptions.CannotDeclareInterestForYourOwnEventException;
import co.descubra.descubraapi.exceptions.DataNotFoundException;
import co.descubra.descubraapi.repository.AdministratorRepository;
import co.descubra.descubraapi.repository.EventFeedBackRepository;
import co.descubra.descubraapi.repository.EventRepository;


@Service
public class EventService {
	
	@Autowired
	private EventFeedBackRepository eventFeedbackRepository;

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private AdministratorRepository administratorRepository ;

	public List<Event> getAllEvents() {
		return eventRepository.findAll();

	}
	
	
	/*public List<Event> getEventsByRatio(double radius, float longitude, float latitude) {
		return eventRepository.findByNamedParams(radius, longitude, latitude);
	}*/
	
	
	public ResponseEntity<?> getEvent(long eventId) {
		Event event = eventRepository.findById(eventId).get();
        return new ResponseEntity<Event>(event, HttpStatus.OK);
	}
	
	
	public ResponseEntity<?> createEvent(Event event) {
		
		Event createdEvent = eventRepository.save(event);
        return new ResponseEntity<Event>(createdEvent, HttpStatus.CREATED);
	}
	
	
	public void deleteEvent(@PathVariable long eventId) {
    	eventRepository.deleteById(eventId);
    }
	
	
	public ResponseEntity<?> updateEvent(long id, Event event) {
		event.setEventId(id);
		
        event = eventRepository.save(event);
        return new ResponseEntity<Event>(event, HttpStatus.CREATED);
	}


	public List<Event> getEventsFiltered(String city, String category) {
		// TODO Auto-generated method stub
		return eventRepository.findByNamedParams(city, category);
	}


	public List<Event> getEventsFilteredByCity(String city) {
		// TODO Auto-generated method stub
		return eventRepository.findByCity(city);
	}


	public List<Event> getEventsFilteredByCategory(String category) {
		// TODO Auto-generated method stub
		return eventRepository.findByCategory(category);
	}

	public ResponseEntity<?> showInterest(AdministratorEventInterest admnEvent) throws Exception {
		Administrator user = administratorRepository.findById(admnEvent.getAdministratorId()).get();
		Event eventAux = eventRepository.findById(admnEvent.getEventId()).get();
		if (user == null || eventAux == null) 
			throw new DataNotFoundException("O administrador ou evento não existe!");
		if(eventAux.getAdministratorId() == user.getAdministratorId())
			throw new CannotDeclareInterestForYourOwnEventException("Não é possível declarar interesse pelo próprio evento.");
		user.getShowInterest().add(eventAux);
		eventAux.getShowInterest().add(user);
		administratorRepository.save(user);
		eventRepository.save(eventAux);
		return new ResponseEntity<AdministratorEventInterest>(admnEvent, HttpStatus.CREATED);
	}

	public ResponseEntity<?> showInterestCancel(AdministratorEventInterest eventAdm) {
		Administrator user = administratorRepository.findById(eventAdm.getAdministratorId()).get();
		Event eventAux = eventRepository.findById(eventAdm.getEventId()).get();
		if (user == null || eventAux == null) {
			throw new DataNotFoundException("O administrador ou evento não existe!");
		}
		user.getShowInterest().remove(eventAux);
		eventAux.getShowInterest().remove(user);
		administratorRepository.save(user);
		eventRepository.save(eventAux);
		return new ResponseEntity<AdministratorEventInterest>(eventAdm, HttpStatus.CREATED);
	}


	public Set<Administrator> getInterestedList(long id) {
		Event eventAux = eventRepository.getOne(id);
		if(eventAux == null) throw new DataNotFoundException("O administrador ou evento não existe!");
		return eventAux.getShowInterest();
	}


	public ResponseEntity<?> giveFeedBack(EventFeedbackDTO eventFeedbackDTO) {
		Event event = eventRepository.findById(eventFeedbackDTO.getEventId()).get();
		if(event == null)
			throw new DataNotFoundException("O evento não existe!");
		EventFeedback efb = new EventFeedback();
		efb.setComment(eventFeedbackDTO.getComment());
		efb.setStars(eventFeedbackDTO.getStars());
		efb.setEvent(event);
		event.getEventFeedbacks().add(efb);
		eventRepository.save(event);
		eventFeedbackRepository.save(efb);
		return new ResponseEntity<EventFeedbackDTO>(eventFeedbackDTO, HttpStatus.CREATED);
	}

	public void removeFeedBack(long feedbackdId) {
		EventFeedback efb = eventFeedbackRepository.findById(feedbackdId).get();
		if(efb == null)
			throw new DataNotFoundException("O feedback não existe!");
		Event eventAux = efb.getEvent();
		eventAux.getEventFeedbacks().remove(efb);
		eventRepository.save(eventAux);
		eventFeedbackRepository.deleteById(feedbackdId);
		
	}

	public InterestCountDTO getInterestsNumber(long id) {
		InterestCountDTO ic = new InterestCountDTO();
		ic.setCount(eventRepository.countEventInterests(id));
		return ic;
	}


	public EventRatingAverageDTO getEventAverageRating(long id) {
		EventRatingAverageDTO era = new EventRatingAverageDTO();
		era.setEventAverage(eventRepository.eventRatingAverage(id));
		return era;
	}
	
	public EventRatingAVGByCategoryDTO getEventRatingAverageByCategory(String category) {
		EventRatingAVGByCategoryDTO erabc = new EventRatingAVGByCategoryDTO();
		erabc.setRatingAverage(eventRepository.eventRatingAverageByCategory(category));
		return erabc;
	}


	public EventRatingAVGByAdministratorDTO getEventRatingAverageByCategory(long adminId) {
		EventRatingAVGByAdministratorDTO eravga = new EventRatingAVGByAdministratorDTO();
		eravga.setRatingAverage(eventRepository.eventRatingAverageByAdministrator(adminId));
		return eravga;
	}

	public List<Event> getFutureEvents() {
		return eventRepository.futureEvents();
	}


	public List<Event> getPastEvents() {
		return eventRepository.pastEvents();
	}
	
	public List<Event> getCurrentEvents() {
		return eventRepository.currentEvents();
	}
	
	


	



}