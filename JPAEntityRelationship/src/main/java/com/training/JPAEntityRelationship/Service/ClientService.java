package com.training.JPAEntityRelationship.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.JPAEntityRelationship.DTO.ClientDTO;
import com.training.JPAEntityRelationship.Models.Branch;
import com.training.JPAEntityRelationship.Models.Client;
import com.training.JPAEntityRelationship.Repository.BranchRepository;
import com.training.JPAEntityRelationship.Repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	public void addNewClientWithBranch(String clientJsonObject) {
		ClientDTO clientDTO = ClientDTO.newInstance(clientJsonObject);
		
		Branch branch = branchRepository.findByBranchId(clientDTO.getBranchId());
		
		Client newClientWithBranch = Client.builder()
				.clientName(clientDTO.getClientName())
				.branchId(branch)
				.build();
		
		clientRepository.save(newClientWithBranch);
	}
	
	public List<Client> findAllClients() {
		return clientRepository.findAll();
	}
	
	/*
	 * returns all clients managed by a particular branch
	 */
	public List<Client> findAllClientsByBranchId(String branchId) {
		if (branchId == null) return null;
		
		return clientRepository.findAllByBranchId(Long.valueOf(branchId));
	}
	
	public void deleteClientByClientId(String clientId) {
		if (clientId == null) return;
		
		clientRepository.deleteById(Long.valueOf(clientId));
	}
	
}
