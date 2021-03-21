package pe.com.maprsoft.facturador.service;

import java.util.List;

import pe.com.maprsoft.facturador.model.Salon;

public interface SalonService {

	public List<Salon> list(String token) throws Exception;
	public List<Salon> listAll(String token) throws Exception;
	public void update(Salon salon) throws Exception;
	public void updateStatus(Salon salon) throws Exception;
	public void insert(Salon salon, String token) throws Exception;
	public void delete(Salon salon, String token) throws Exception;
}
