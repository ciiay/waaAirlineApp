package cs545.airline.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import cs545.airline.dao.FlightDao;
import cs545.airline.dto.FlightFilter;
import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;

@Named
@ApplicationScoped
@Transactional
public class FlightService {

	@Inject
	private FlightDao flightDao;

	// These services should be evaluated to reconsider which methods should be
	// public

	// CREATE MUST BE DONE THROUGH RELATED OBJECT
	// public void create(Flight flight) {
	// flightDao.create(flight);
	// }

	// DELETE MUST BE DONE THROUGH UPDATE ON RELATED OBJECT
	// public void delete(Flight flight) {
	// flightDao.delete(flight);
	// }

	public Flight update(Flight flight) {
		return flightDao.update(flight);
	}

	public Flight find(Flight flight) {
		return flightDao.findOne(flight.getId());
	}

	public List<Flight> findByNumber(String flightnr) {
		return flightDao.findByFlightnr(flightnr);
	}

	public List<Flight> findByAirline(Airline airline) {
		return flightDao.findByAirline(airline.getId());
	}

	public List<Flight> findByOrigin(Airport airport) {
		return flightDao.findByOrigin(airport.getId());
	}

	public List<Flight> findByDestination(Airport airport) {
		return flightDao.findByDestination(airport.getId());
	}

	public List<Flight> findByAirplane(Airplane airplane) {
		return flightDao.findByAirplane(airplane.getId());
	}

	public List<Flight> findByArrival(Date datetime) {
		return flightDao.findByArrival(datetime, datetime);
	}

	public List<Flight> findByArrivalBetween(Date datetimeFrom, Date datetimeTo) {
		return flightDao.findByArrivalBetween(datetimeFrom, datetimeFrom, datetimeTo, datetimeTo);
	}

	public List<Flight> findByDeparture(Date datetime) {
		return flightDao.findByDeparture(datetime, datetime);
	}

	public List<Flight> findByDepartureBetween(Date datetimeFrom, Date datetimeTo) {
		return flightDao.findByDepartureBetween(datetimeFrom, datetimeFrom, datetimeTo, datetimeTo);
	}

	public List<Flight> findAll() {
		return flightDao.findAll();
	}

	public List<Flight> searchFlight(FlightFilter ff) {
		List<Flight> sr = new ArrayList<Flight>();
		List<Flight> allFlights = flightDao.findAll();
		for(Flight f:allFlights) {
			if(f.getDepartureDate().contains(ff.getDepartureDate()) 
					&& f.getDepartureTime().contains(ff.getDepartureTime())
					&& f.getAirline().getName().contains(ff.getAirlineName())
					&& f.getOrigin().getName().contains(ff.getDepartureAirport())
					&& f.getDestination().getName().contains(ff.getArrivalAirport())) {
				sr.add(f);
			}
		}
		return sr;
	}
}
