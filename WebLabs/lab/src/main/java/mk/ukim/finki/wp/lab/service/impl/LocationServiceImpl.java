package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.LocationRepository;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepositoryJPA locationRepositoryJPA;

    public LocationServiceImpl(LocationRepositoryJPA locationRepositoryJPA) {
        this.locationRepositoryJPA = locationRepositoryJPA;
    }

    @Override
    public List<Location> findAll() {
        return locationRepositoryJPA.findAll();
    }

    @Override
    public List<Location> listAll() {
        return locationRepositoryJPA.findAll();
    }


}