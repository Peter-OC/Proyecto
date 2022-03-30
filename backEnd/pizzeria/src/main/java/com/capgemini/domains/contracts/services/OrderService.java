package com.capgemini.domains.contracts.services;

import java.util.List;

import com.capgemini.application.dtos.OrderDetailsDTO;
import com.capgemini.domains.entities.Order;

public interface OrderService extends ProjectionDomainService<Order, Integer>{
	public <T> List<T> getOrdered(Class <T> type);
	public <T> List<T> getInProcess(Class <T> type);
	public <T> List<T> getReady(Class <T> type);
	public <T> List<T> getSent(Class <T> type);
	public <T> List<T> getReceived(Class <T> type);
	public <T> List<T> getCanceled(Class <T> type);

}
