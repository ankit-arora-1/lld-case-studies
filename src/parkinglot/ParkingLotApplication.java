package parkinglot;

import parkinglot.controllers.TicketController;
import parkinglot.models.Ticket;
import parkinglot.repositories.GateRepository;
import parkinglot.repositories.ParkingLotRepository;
import parkinglot.repositories.TicketRepository;
import parkinglot.repositories.VehicleRepository;
import parkinglot.services.TicketService;

public class ParkingLotApplication {
    public static void main(String[] args) {
        GateRepository gateRepository = new GateRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        TicketRepository ticketRepository = new TicketRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();

        TicketService ticketService = new TicketService(
                gateRepository,
                vehicleRepository,
                parkingLotRepository,
                ticketRepository
        );

        TicketController ticketController = new TicketController(
                ticketService
        );


    }
}
