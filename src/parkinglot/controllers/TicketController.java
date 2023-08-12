package parkinglot.controllers;

import parkinglot.dtos.IssueTicketRequestDto;
import parkinglot.dtos.IssueTicketResponseDto;
import parkinglot.dtos.ResponseStatus;
import parkinglot.models.Ticket;
import parkinglot.services.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDto issueTicket(IssueTicketRequestDto request) {
        IssueTicketResponseDto issueTicketResponseDto =
                new IssueTicketResponseDto();

        Ticket ticket;
        try {
            ticket = ticketService.issueTicket(
                    request.getVehicleType(),
                    request.getVehicleNumber(),
                    request.getVehicleOwnerName(),
                    request.getGateId()
            );
        } catch (Exception e) {
            issueTicketResponseDto.setResponseStatus(ResponseStatus.ERROR);
            issueTicketResponseDto.setErrorMessage(e.getMessage());
            return issueTicketResponseDto;
        }

        issueTicketResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        issueTicketResponseDto.setTicket(ticket);

        return issueTicketResponseDto;
    }
}
