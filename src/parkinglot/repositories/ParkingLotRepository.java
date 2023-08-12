package parkinglot.repositories;

import parkinglot.models.Gate;
import parkinglot.models.ParkingLot;

import java.util.Map;
import java.util.TreeMap;

public class ParkingLotRepository {
    private Map<Long, ParkingLot> parkingLots = new TreeMap<>();

    public ParkingLot getParkingLotForGate(Gate gate) {
        for(ParkingLot parkingLot: parkingLots.values()) {
            if(parkingLot.getGates().contains(gate)) {
                return parkingLot;
            }
        }

        return null;
    }
}
