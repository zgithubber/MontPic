package com.awt.montpic.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.awt.montpic.domain.Image;
import com.awt.montpic.domain.Mountain;

@Repository
public class MountainRepositoryImpl implements MountainRepository {

	@PersistenceContext
	private EntityManager em;
	
	public final String SEARCH_IMAGES = "SELECT im FROM Image im WHERE im.mountain.name = :mountainName";
	public final String SEARCH_MOUNTAINS = "SELECT m FROM Mountain m";
	public final String CHECK_IF_MOUNTAIN_EXISTS = "SELECT m FROM Mountain m WHERE m.name = :mountainName";
	
	@Override
	public void saveImages(Image image) {
		//em.persist(image);throws exception on duplicates
		
		em.merge(image);		
	}
	
	@Override
	public List<Image> getImages(String mountainName){
		
		TypedQuery<Image> query = em.createQuery(SEARCH_IMAGES, Image.class);
		query.setParameter("mountainName", mountainName);
		
		return query.getResultList();
	}

	@Override
	public List<Mountain> getAllMountains() {
		
		TypedQuery<Mountain> query = em.createQuery(SEARCH_MOUNTAINS, Mountain.class);
		
		return query.getResultList();
	}

	@Override
	public Boolean mountainNotInDB(String mountainName) {
		
		TypedQuery<Mountain> query = em.createQuery(CHECK_IF_MOUNTAIN_EXISTS, Mountain.class);
		query.setParameter("mountainName", mountainName);
		
		return query.getResultList().isEmpty();
	}

}
