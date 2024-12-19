/*
package mk.ukim.finki.mk.lab.repository.inMemory;

import mk.ukim.finki.mk.lab.boostrap.DataHolder;
import mk.ukim.finki.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryLocationRepository {


    public List<Location> findAll() {
        return locations;
    }

    public Optional<Location> findById(Long id) {
        return locations.stream().filter(location -> location.getId().equals(id)).findFirst();
    }
    public void deleteById(Long id) {
        List<Location> tempLocationsList = new ArrayList<>(locations);
        tempLocationsList.removeIf(location -> location.getId().equals(id));
        locations = tempLocationsList;
    }
    public void add(Location location) {
        List<Location> tempLocationsList = new ArrayList<>(locations);
        tempLocationsList.add(location);
        locations = tempLocationsList;
    }
}
*/
