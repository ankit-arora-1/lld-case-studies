package parkinglot.models;

import java.util.List;

public class ParkingSlot extends BaseModel {
    private List<VehicleType> vehicleTypes;
    private ParkingSlotStatus parkingSlotStatus;
    private int slotNumber;
    private ParkingFloor parkingFloor;
}
