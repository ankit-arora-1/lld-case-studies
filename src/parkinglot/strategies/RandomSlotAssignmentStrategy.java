package parkinglot.strategies;

import parkinglot.models.Gate;
import parkinglot.models.ParkingSlot;
import parkinglot.models.VehicleType;

public class RandomSlotAssignmentStrategy implements SlotAssignmentStrategy{
    @Override
    public ParkingSlot getSlot(Gate gate, VehicleType vehicleType) {
        return null;
    }
}
