import { useState } from "react";
import { GridColumn } from "@vaadin/react-components/GridColumn";
import { Grid } from "@vaadin/react-components/Grid";
import CustomButton from "../components/CustomButton";
import BookingDetails from "Frontend/generated/org/vaadin/marcus/service/BookingDetails";

const statusIcons: { [key: string]: string } = {
  CONFIRMED: "✅",
  COMPLETED: "🏁",
  CANCELLED: "❌",
  AWAITING_CONFIRMATION: "⏳",
  AVAILABLE: "🟢"
};

interface FlightManagementDashboardProps {
  bookings: BookingDetails[];
}

const FlightManagementDashboard: React.FC<FlightManagementDashboardProps> = ({ bookings }) => {
  const [showConfirmed, setShowConfirmed] = useState(false);

  const renderStatus = (booking: BookingDetails) => {
    const status = booking.bookingStatus;
    return statusIcons[status as keyof typeof statusIcons] || status;
  };

  const awaitingConfirmationBookings = bookings.filter(booking => booking.bookingStatus === "AWAITING_CONFIRMATION");
  const availableFlights = bookings.filter(booking => booking.bookingStatus === "AVAILABLE");
  const confirmedBookings = bookings.filter(booking => booking.bookingStatus === "CONFIRMED");

  const renderBookingGrid = (items: BookingDetails[], showNames: boolean = true) => (
    <Grid items={items} className="flex-shrink-0">
      <GridColumn path="bookingNumber" header="#" autoWidth/>
      {showNames && (
        <>
          <GridColumn path="firstName" autoWidth/>
          <GridColumn path="lastName" autoWidth/>
        </>
      )}
      <GridColumn path="date" autoWidth/>
      <GridColumn path="from" autoWidth/>
      <GridColumn path="to" autoWidth/>
      <GridColumn header="Status" autoWidth>
        {({ item }) => renderStatus(item)}
      </GridColumn>
      <GridColumn path="bookingClass" autoWidth/>
    </Grid>
  );

  return (
    <div className="flex flex-col gap-6 p-6 box-border overflow-auto w-full md:w-3/4 bg-gray-50">
      <h2 className="text-3xl font-bold text-blue-700 border-b-2 border-blue-300 pb-2">Flight Management Dashboard</h2>
      <section className="bg-white rounded-lg shadow-md p-4 hover:shadow-lg transition-shadow duration-300">
        <h3 className="text-xl font-semibold mb-3 text-blue-600">Bookings Awaiting Confirmation</h3>
        {renderBookingGrid(awaitingConfirmationBookings)}
      </section>
      <section className="bg-white rounded-lg shadow-md p-4 hover:shadow-lg transition-shadow duration-300">
        <h3 className="text-xl font-semibold mb-3 text-blue-600">Available Flight Options</h3>
        {renderBookingGrid(availableFlights, false)}
      </section>
      <section className="bg-white rounded-lg shadow-md p-4 hover:shadow-lg transition-shadow duration-300">
        <h3 className="text-xl font-semibold mb-3 text-blue-600">Confirmed Bookings</h3>
        <CustomButton onClick={() => setShowConfirmed(!showConfirmed)} className="mb-3">
          {showConfirmed ? 'Hide' : 'Show'} Confirmed Bookings ({confirmedBookings.length})
        </CustomButton>
        {showConfirmed && (
          confirmedBookings.length > 0 
            ? renderBookingGrid(confirmedBookings)
            : <p className="text-gray-600 italic">No confirmed bookings available at this time.</p>
        )}
      </section>
    </div>
  );
};

export default FlightManagementDashboard;