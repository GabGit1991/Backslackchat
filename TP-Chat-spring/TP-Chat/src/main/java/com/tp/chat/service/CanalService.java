package com.tp.chat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.chat.entity.Canal;
import com.tp.chat.repository.CanalRepository;

@Service
public class CanalService {

	@Autowired
	CanalRepository canalRepository;

	public Boolean addCanal(Canal canal) {
		if (canal.getName() != null) {
			canalRepository.save(canal);
			return true;
		} else {
			return false;
		}
	}

	public List<Canal> getAllCanals() {
		return canalRepository.findAll();
	}

	public Canal getCanalById(Integer id) {
		Optional<Canal> optionalCanal = canalRepository.findById(id);
		if (optionalCanal.isPresent()) {
			Canal foundCanal = optionalCanal.get();
			return foundCanal;
		} else {
			return new Canal();
		}
	}

	public Boolean deleteCanalById(Integer id) {
		Optional<Canal> optionalCanal = canalRepository.findById(id);
		if (optionalCanal.isPresent()) {
			canalRepository.deleteById(id);
			;
			return true;
		} else
			return false;
	}

	public Boolean updateCanalById(Integer id, Canal canal) {
		Optional<Canal> optionalCanal = canalRepository.findById(id);
		if (optionalCanal.isPresent() && canal.getId() == id) {
			canalRepository.save(null);
			return true;
		} else {
			return false;
		}
	}

}
