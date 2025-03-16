# Turf Booking Booking Sapi
Bookings System API for the Turf Booking Application

## About the Integration
You can read about it [here](https://github.com/RatheeshRaghavendra/My-Projects/blob/main/Turf-Booking-Service.md)

## End Points

### GET /booking/search-by

Returns a list of Bookings based on the parameter (turf, user) and value specified in the query parameter

### GET /booking/all

Returns a list of all the Bookings

### GET /booking/{bookingId}

Returns a Booking based on the Booking ID

### POST /booking

Creates a Booking

### DELETE /booking/{bookingId}

Deletes a Booking based on the ID

## Object Example

---

### ApiResponse

```json
{
    "statusCode": 200,
    "statusMessage": "OK",
    "payload": {
    
    },
    "apiError": null
}
```

### ApiError

```json
{
	"errorMessage": "No value present",
	"errorDescription": "java.util.NoSuchElementException: No value present",
	"customError": "No Turf with the Turf ID: 5, Present in the DB"
}
```

### Booking

```json
{
    "bookingId": 1,
    "turfId": 1,
    "slotIds": [1,2],
    "userId": 1
}
```
