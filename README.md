# spring-ai-mcp-agentic-travel-assistant
Repository to demonstrate the Spring AI capability to use Model Context Protocol (MCP) to create an AI Agent to act as a travel assistant

This assistant is capable of taking a simple prompt and make all the arrangement for your travel.

## Depends ON
- Duffel MCP Server Running: `https://github.com/sainathkistapur/spring-ai-mcp-servers/tree/master/mcp.server.duffel`
- Calendar MCP Server Running: `https://github.com/sainathkistapur/spring-ai-mcp-servers/tree/master/mcp.server.custom.calendar`

### Example 

`Am I travelling anywhere on 2025-07-07? If so, without asking for my confirmation make complete bookings with the cheapest option. Show me the full detais of all bookings`

The agent will
- Check you calendar for any travel events. (Calendar MCP Server)
- Will identify your travel requirements from the calendar event
- Get the IATA locations for the travel cities from the flight info server (Duffel MCP Server)
- Get the flight information from the flight info server (Duffel MCP Server)
- Makes the flight booking based on the cheapest price.
- Identifies the accommodation requirements from the calendar event
- Makes the accommodation booking with AirBnb (AirBnB MCP Server)
- Give the full details of the entire travel arrangements. 

### cURL
```
curl --location --request GET 'http://localhost:8080/springai/openai/mcp/client/custom/server/basic-chat' \
--header 'conversationId: CID21' \
--header 'Content-Type: text/plain' \
--data-raw 'Am I travelling anywhere on 2025-07-07? If so, without asking for my confirmation make complete bookings with the cheapest option. Show me the full detais of all bookings'
```