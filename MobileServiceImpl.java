package com.abc.mobilestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.mobilestore.entity.Mobile;
import com.abc.mobilestore.exception.ResourceNotFoundException;
import com.abc.mobilestore.repository.MobileRepository;

@Service
public class MobileServiceImpl implements MobileService {
	
	@Autowired
	private MobileRepository mobileRepository;

	@Override
	public Mobile saveMobile(Mobile mobile) {
		
		return mobileRepository.save(mobile);
		
	}

	@Override
	public Mobile getMobileById(int mobileId) {
		
		Optional<Mobile> optionalMobile = mobileRepository.findById(mobileId);
		
		if(optionalMobile.isEmpty()) {
			throw new ResourceNotFoundException("Mobile not existing with id: "+mobileId);
		}
		Mobile mobile = optionalMobile.get();		
		return mobile;
	}

	@Override
	public List<Mobile> getAllMobiles() {
	
		List<Mobile> mobiles = mobileRepository.findAll();
		return mobiles;
	}
	@Override
	public Mobile updateMobile(Mobile mobile) {
		
		Optional<Mobile> optionalMobile = mobileRepository.findById(mobile.getMobileId());
		if (optionalMobile.isEmpty()) {
			throw new ResourceNotFoundException("Mobile not existing with id: " + mobile.getMobileId());
		}		
		mobileRepository.save(mobile);		
		return mobile;
	}
 
	@Override
	public void deleteMobile(int mobileId) {
 
		Optional<Mobile> optionalMobile = mobileRepository.findById(mobileId);
		if (optionalMobile.isEmpty()) {
			throw new ResourceNotFoundException("Mobile not existing with id: " + mobileId);
		}		
		Mobile mobile = optionalMobile.get();		
		mobileRepository.delete(mobile);
	}
	

}