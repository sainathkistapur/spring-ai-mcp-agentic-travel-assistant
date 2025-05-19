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
Here are your full travel arrangements and booking details for your trip on 8th August 2025:

---

## Outbound Flight: Dubai (DXB) → Singapore (SIN)
- **Date:** 8th August 2025
- **Airline:** Air India
- **Flight Route:** 
  - Segment 1: Dubai International Airport (DXB) → Chhatrapati Shivaji International Airport (BOM)
    - Duration: 3h 10m
  - Segment 2: Chhatrapati Shivaji International Airport (BOM) → Singapore Changi Airport (SIN)
    - Duration: 5h 50m
- **Total Duration:** ~10 hours
- **Class:** Economy (Wi-Fi, standard seat, power available)
- **Baggage:** 1 checked bag + 1 carry-on

---

## Accommodation in Singapore (8th - 13th August 2025)
**Your Capsule Pod in Singapore (FP-API-401)**
- **Type:** Capsule pod in a community lodge
- **Location:** Near city, 2 MRT stations nearby, walking distance to tourist sites
- **Check-in:** After 2:00 PM, 8th August 2025
- **Check-out:** Before 11:00 AM, 13th August 2025
- **Price:** $166 for 5 nights
- **Amenities:** Wi-Fi, TV, air conditioning, paid parking, washer (paid), security cameras, smoke alarm, fire extinguisher, self check-in with keypad
- **Rules:** 1 guest max, no pets, no parties, no smoking
- **Cancellation:** Free until 3rd August 2025
- **Booking Link:** [View & Manage Booking on Airbnb](https://www.airbnb.com/rooms/1077066160280158672?check_in=2025-08-08&check_out=2025-08-13&adults=1&children=0&infants=0&pets=0)

---

## Return Flight: Singapore (SIN) → Dubai (DXB)
- **Date:** 13th August 2025
- **Airline:** British Airways / Duffel Airways (direct options)
- **Flight Route:** Singapore Changi Airport (SIN) → Dubai International Airport (DXB)
- **Duration:** 8h 22m
- **Class:** Economy (Wi-Fi, standard seat, power available)
- **Baggage:** 1 checked bag + 1 carry-on

---

### Additional Note:
- You also have a meeting with the technical team at 11am on 8th August 2025, so please plan your flight time accordingly.

Would you like to proceed with booking these flights and the Airbnb, or do you need to see more accommodation or flight options?
```

### cURL
```
curl --location --request GET 'http://localhost:8080/springai/openai/mcp/client/custom/server/basic-chat' \
--header 'conversationId: CID2' \
--header 'Content-Type: text/plain' \
--data-raw 'Am I travelling anywhere on 8th August 2025? If so, make all the necessary arragements. Show me the full detais of all bookings'
```