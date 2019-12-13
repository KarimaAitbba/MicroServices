package org.sid.service_compte.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.map.MultiValueMap;
import org.apache.http.HttpEntity;
import org.sid.service_compte.clients.GetAbonneeClient;
import org.sid.service_compte.dao.AbonneRepository;
import org.sid.service_compte.dao.CarteBancaireRepository;
import org.sid.service_compte.dao.CompteRepository;
import org.sid.service_compte.dao.OperationRepository;
import org.sid.service_compte.entities.Abonne;
import org.sid.service_compte.entities.CarteBancaire;
import org.sid.service_compte.entities.Compte;
import org.sid.service_compte.entities.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;



@Service
public class CompteService {
	@Autowired
	CompteRepository compteRepository;
	@Autowired
	AbonneRepository abonneRepository;
	@Autowired
	OperationRepository operationRepository;
	@Autowired
	CarteBancaireRepository carteBancaireRepository;
	

	@Autowired
	public GetAbonneeClient getAbonnee; 
	
	
	
	public List<Compte> findAllAccountsAbonne(long id)
	{
		Abonne a = getAbonnee.getAbonne(id);
		return compteRepository.findByAbonne(a);
    }
	
	public List<Operation> findAllOperations(Long idCompte)
	{
		Compte compte = compteRepository.findByIdCompte(idCompte);
		return compte.getOperations();
	}
	
	
	public CarteBancaire findAllCarteBancaire(Long idCompte)
	{
		Compte compte = compteRepository.findByIdCompte(idCompte);
		return  compte.getCarteBancaire();
	}
	public void DesactiveEtatCarte(String idCarte)
	{
		CarteBancaire carte = carteBancaireRepository.getOne(idCarte);
		
		 carte.setEtat("desactiver");
		
		 carteBancaireRepository.save(carte);
			
			
	}
	public void ActiveEtatCarte(String idCarte)
	{
		CarteBancaire carte = carteBancaireRepository.getOne(idCarte);
		
		 carte.setEtat("active");
		
		 carteBancaireRepository.save(carte);
			
			
	}
	
}
