package es.restaurant.EatApp.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.restaurant.EatApp.models.repositories.TableDao;
import es.restaurant.EatApp.models.repositories.WaiterDao;

public class ObserverManager {
//Clase terrible, preguntar a Luis
	private List<Waiter> waiters;
	private Map<Integer,Table> tables;
	
	public ObserverManager() {
		this.waiters = new ArrayList<Waiter>();
		this.waiters = WaiterDao.getWaiterDao().getWaiters();
		this.tables = new HashMap<Integer,Table>();
		this.tables = TableDao.getTableDao().getTables();
	}

	public void initObservers() {
		this.waiters = WaiterDao.getWaiterDao().getWaiters();
		this.tables = TableDao.getTableDao().getTables();
		waiterObserveTables();
	}

	private void waiterObserveTables() {
		for(Waiter w : this.waiters) {
			w.addObserver(this.tables.values());
		}
	}

	public void notify(int table) {
		this.tables.get(table).askForHelp();
	}
}
