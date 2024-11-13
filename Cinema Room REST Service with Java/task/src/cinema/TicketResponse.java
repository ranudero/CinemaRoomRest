package cinema;

import java.util.UUID;

public class TicketResponse {

    private String token;
    private Seat ticket;

    public TicketResponse(Seat ticket) {
        this.token = UUID.randomUUID().toString();
        this.ticket = ticket;
    }

    public String getToken() {
        return token;
    }

    public Seat getTicket() {
        return ticket;
    }
}
