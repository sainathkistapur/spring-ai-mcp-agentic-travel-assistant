# spring-ai-mcp-agentic-travel-assistant
Repository to demonstrate the Spring AI capability to use Model Context Protocol (MCP) to create an AI Agent to act as a travel assistant

This assistant is capable of taking a simple prompt and make all the arrangement for your travel.

## Depends ON
- Duffel MCP Server Running: [mcp.server.duffel](https://github.com/sainathkistapur/spring-ai-mcp-servers/tree/master/mcp.server.duffel)
- Calendar MCP Server Running: [mcp.server.custom.calendar](https://github.com/sainathkistapur/spring-ai-mcp-servers/tree/master/mcp.server.duffel)
- MCP AirBnb server: This is currently configured as NPM package pointing to [mcp-server-airbnb](https://github.com/openbnb-org/mcp-server-airbnb) This is also be created using the Spring AI MCP server.

### Example 
#### Prompt:
```Am I travelling anywhere on 8th August 2025? If so, make all the necessary arragements. Show me the full detais of all bookings```

The agent will
- Check you calendar for any travel events. (Calendar MCP Server)
- Will identify your travel requirements from the calendar event
- Get the IATA locations for the travel cities from the flight info server (Duffel MCP Server)
- Get the flight information from the flight info server (Duffel MCP Server)
- Makes the flight booking based on the cheapest price.
- Identifies the accommodation requirements from the calendar event
- Makes the accommodation booking with AirBnb (AirBnB MCP Server)
- Give the full details of the entire travel arrangements. 

#### Sample Response:
```
You have a trip scheduled on 2025-07-07 from New York to Berlin, with a return after 4 days (on 2025-07-11). Here are the completed bookings using the cheapest available options:

---

### Flight Bookings

#### Outbound: New York (LaGuardia Airport) → Berlin (Brandenburg Airport)
- **Airline**: British Airways
- **Cabin**: Economy
- **Baggage**: 1 checked, 1 carry-on
- **Duration**: 9 hours 5 minutes
- **Departure Airport**: LaGuardia Airport (NYC)
- **Arrival Airport**: Berlin Brandenburg Airport (BER)
- **Date**: 2025-07-07

#### Return: Berlin (Brandenburg Airport) → New York (LaGuardia Airport)
- **Airline**: British Airways
- **Cabin**: Economy
- **Baggage**: 1 checked, 1 carry-on
- **Duration**: 9 hours 5 minutes
- **Departure Airport**: Berlin Brandenburg Airport (BER)
- **Arrival Airport**: LaGuardia Airport (NYC)
- **Date**: 2025-07-11

---

### Accommodation Booking (Cheapest Available Option)

#### Airbnb: Sunny room in Neukölln, on the river (Solo woman, hosted by Maria)
- **Type**: Private room in a condo (room is only for women)
- **Location**: Neukölln, Berlin (Canal view)
- **Check-in**: 2025-07-07
- **Check-out**: 2025-07-11
- **Total Price**: $229 for 4 nights
- **Amenities**:
    - Canal view, essentials, bed linens, room-darkening shades
    - Central heating, lock on bedroom door, carbon monoxide alarm
    - Wifi, dedicated workspace, refrigerator, microwave, dishes, dishwasher, hot water kettle, patio/balcony, free street parking
- **Policies/Rules**:
    - Check-in: 3:00 PM - 10:00 PM
    - Checkout: before 10:00 AM
    - 1 guest maximum, no parties, no smoking (even on balcony)
    - Throw trash away before you leave
    - [Airbnb Listing & Booking Link](https://www.airbnb.com/rooms/22676690?check_in=2025-07-07&check_out=2025-07-11&adults=1&children=0&infants=0&pets=0)

---

**If you have any preferences or need changes, let me know! Otherwise, your bookings are ready for your Berlin trip from July 7th to July 11th, 2025.**
```

### cURL
```
curl --location --request GET 'http://localhost:8080/springai/openai/mcp/client/custom/server/basic-chat' \
--header 'conversationId: CID21' \
--header 'Content-Type: text/plain' \
--data-raw 'Am I travelling anywhere on 2025-07-07? If so, without asking for my confirmation make complete bookings with the cheapest option. Show me the full detais of all bookings'
```