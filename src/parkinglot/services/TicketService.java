package parkinglot.services;

import parkinglot.dtos.IssueTicketRequestDto;
import parkinglot.exceptions.GateNotFoundException;
import parkinglot.models.*;
import parkinglot.repositories.GateRepository;
import parkinglot.repositories.ParkingLotRepository;
import parkinglot.repositories.TicketRepository;
import parkinglot.repositories.VehicleRepository;
import parkinglot.strategies.SlotAssignmentStrategy;
import parkinglot.strategies.SlotAssignmentStrategyFactory;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public TicketService(
            GateRepository gateRepository,
            VehicleRepository vehicleRepository,
            ParkingLotRepository parkingLotRepository,
            TicketRepository ticketRepository
    ) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(VehicleType vehicleType,
                              String vehicleNumber,
                              String vehicleOwnerName,
                              Long gateId) throws GateNotFoundException {
        // Create a ticket object
        // Assign spot
        // Assign time
        // Save to DB
        // return the created object

        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        // Get Gate model from gate id
        Optional<Gate> gateOp = gateRepository.findGateById(gateId);

        if(!gateOp.isPresent()) {
            throw new GateNotFoundException();
        }

        Gate gate = gateOp.get();
        ticket.setGate(gate);
        ticket.setGeneratedBy(gate.getOperator());

        Vehicle savedVehicle;
        Optional<Vehicle> vehicleOptional = vehicleRepository
                .getVehicleByNumber(vehicleNumber);

        if(!vehicleOptional.isPresent()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleType(vehicleType);
            vehicle.setNumber(vehicleNumber);
            vehicle.setOwnerName(vehicleOwnerName);

            savedVehicle = vehicleRepository.saveVehicle(vehicle);
        } else {
            savedVehicle = vehicleOptional.get();
        }

        ticket.setVehicle(savedVehicle);

        SlotAllotmentStrategyType slotAllotmentStrategyType = parkingLotRepository
                .getParkingLotForGate(gate).getSlotAllotmentStrategyType();

        SlotAssignmentStrategy slotAssignmentStrategy =
                SlotAssignmentStrategyFactory.getSlotForType(
                        slotAllotmentStrategyType
                );

        ticket.setParkingSlot(
                slotAssignmentStrategy.getSlot(gate, vehicleType)
        );

        ticket.setNumber("TICKET-" + ticket.getId()); // or generate a uuid
        return ticketRepository.saveTicket(ticket);
    }
}
