package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Location;

import java.util.List;

public interface LocationService {
    List<Location> findAll();
    List<Location> listAll();


}