package co.descubra.descubraapi.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.descubra.descubraapi.core.dto.EmailDTO;
import co.descubra.descubraapi.core.model.Administrator;
import co.descubra.descubraapi.core.model.Event;
import co.descubra.descubraapi.core.model.User;
import co.descubra.descubraapi.core.util.Util;
import co.descubra.descubraapi.exceptions.DataAlreadyExistsException;
import co.descubra.descubraapi.exceptions.DataNotFoundException;
import co.descubra.descubraapi.repository.AdministratorRepository;
import co.descubra.descubraapi.repository.EventRepository;
import co.descubra.descubraapi.repository.UserRepository;

@Service
public class AdministratorService {
	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private MailingService mailingService;

	public List<Administrator> getAllAdministrators() {
		return administratorRepository.findAll();
	}

	public Administrator getAdministrator(long admId) {
		Administrator administrator = administratorRepository.getOne(admId);
		if (administrator == null) {
			throw new DataNotFoundException("Administrator not found: " + admId);
		}
		return administrator;
	}

	public ResponseEntity<?> createAdministrator(Administrator administrator) throws DataAlreadyExistsException {
		for (Administrator ad : administratorRepository.findAll()) {
			if (ad.getUser().getEmail().equals(administrator.getUser().getEmail())) {
				throw new DataAlreadyExistsException("The username is already in use.");
			}
		}
		administrator.setUser(userRepository.save(administrator.getUser()));
		Administrator createdAdministrator = administratorRepository.save(administrator);
		new Thread(new Runnable(){  
				@Override   
				public void run(){  
					mailingService.send(administrator.getUser().getEmail(), Util.EMAIL_WELCOME_TITLE, Util.EMAIL_WELCOME_CONTENT);  
				}   
			}).start();
		
		return new ResponseEntity<Administrator>(createdAdministrator, HttpStatus.OK);
	}

	public void deleteAdministrator(long admId) {
		this.administratorRepository.deleteById(admId);
	}

	public List<Event> getAllEventsFromAdministrator(long admId) {
		Administrator adm = administratorRepository.getOne(admId);
		if (adm == null) {
			throw new DataNotFoundException("O administrador não existe!");
		}
		return adm.getEvents();
	}

	public ResponseEntity<?> createEventForASpecificAdministrator(long admId, Event event) {
		Administrator adm = administratorRepository.findById(admId).get();
		if (adm == null) {
			throw new DataNotFoundException("O administrador não existe!");
		}
		event.setAdministratorId(admId);
		event.setAdministrator(adm);
		event = eventRepository.save(event);
		return new ResponseEntity<Event>(event, HttpStatus.CREATED);
	}

	public ResponseEntity<?> updateEventForASpecificAdministrator(long admId, Event event, long eventsId) {
		Administrator adm = administratorRepository.findById(admId).get();
		if (adm == null) {
			throw new DataNotFoundException("O administrador não existe!");
		}
		event.setEventId(eventsId);
		event.setAdministratorId(admId);
		event.setAdministrator(adm);
		event = eventRepository.save(event);
		return new ResponseEntity<Event>(event, HttpStatus.CREATED);
	}

	public Set<Event> getInterestedEvents(long adminId) {
		Administrator admAux = administratorRepository.findById(adminId).get();
		if(admAux == null) throw new DataNotFoundException("O administrador ou evento não existe!");
		return admAux.getShowInterest();
		
	}

	public void recoveryPassword(EmailDTO email) {
		User userAux = userRepository.findByEmail(email.getEmail());
		if(userAux == null)
			throw new DataNotFoundException("Não existe usuário com este e-mail.");
		mailingService.send(email.getEmail(), Util.EMAIL_SUBJECT, Util.EMAIL_CONTENT+""+userAux.getPassword());
			
		
	}

}
