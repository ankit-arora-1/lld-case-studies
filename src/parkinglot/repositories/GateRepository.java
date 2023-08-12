package parkinglot.repositories;

import parkinglot.models.Gate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GateRepository {
    private Map<Long, Gate> gates = new HashMap<>();

    public Optional<Gate> findGateById(Long id) {
        if(gates.containsKey(id)) {
            return Optional.of(gates.get(id));
        }

        return Optional.empty();
    }
}
