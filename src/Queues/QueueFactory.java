// package Queues;

// import Crews.RunwayDirector;
// import FlightFolder.Flight;
// import FlightFolder.FlightDetails;
// import FlightFolder.IncomingFlight;

// public class QueueFactory {
// private static boolean endofday = false;

// private static BoundedQueue<Flight> FuelQueue = new BoundedQueue<Flight>(8);
// /**
// * push a Flight to the Fuel Queue
// * @param fl Flight
// */
// public static void FuelQueue_Synced_Push(Flight fl){
// synchronized(FuelQueue){
// while(FuelQueue.isFull())
// try {
// FuelQueue.wait();
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// FuelQueue.push(fl);
// FuelQueue.notifyAll();
// }
// }
// /**
// * remove Flight from Fuel Queue
// * @return Flight
// */
// public static Flight FuelQueue_Synced_Pop(){
// synchronized(FuelQueue){
// while(FuelQueue.size() == 0)
// try {
// FuelQueue.wait();
// if(endofday == true)
// return null;
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// FuelQueue.notifyAll();
// return FuelQueue.pop();
// }
// }

// private static Queue<Flight> SecurityQueue = new Queue<Flight>();
// /**
// * push a Flight to the Security Queue
// * @param fl Flight
// */
// public static void SecurityQueue_Synced_Push(Flight fl) {
// synchronized(SecurityQueue){
// SecurityQueue.push(fl);
// SecurityQueue.notifyAll();
// }
// }
// /**
// * remove Flight from Security Queue
// * @return Flight
// */
// public static Flight SecurityQueue_Synced_Pop() {
// synchronized(SecurityQueue){
// while(SecurityQueue.size() == 0)
// try {
// SecurityQueue.wait();
// if(endofday == true)
// return null;
// } catch (InterruptedException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// return SecurityQueue.pop();
// }
// }

// private static Queue<FlightDetails> ManagementQueue = new
// Queue<FlightDetails>();
// /**
// * push a FlightDetails(report) to the Management Queue
// * @param fl FlightDetails
// */
// public static void ManagementQueue_Synced_Push(FlightDetails fl) {
// synchronized(ManagementQueue){
// ManagementQueue.push(fl);
// ManagementQueue.notifyAll();
// }
// }
// /**
// * remove FlightDetails(report) from Management Queue
// * @return FlightDetails
// */
// public static FlightDetails ManagmentQueue_Synced_Pop() {
// synchronized(ManagementQueue){
// while(ManagementQueue.size() == 0)
// try {
// ManagementQueue.wait();
// if(endofday == true)
// return null;
// } catch (InterruptedException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// return ManagementQueue.pop();
// }
// }

// private static Queue<IncomingFlight> LogisticsQueue = new
// Queue<IncomingFlight>();
// /**
// * push a Flight to the Technical Queue
// * @param fl Flight
// */
// public static void LogisticsQueue_Synced_Push(IncomingFlight fl) {
// synchronized(LogisticsQueue){
// LogisticsQueue.push(fl);
// LogisticsQueue.notifyAll();
// }
// }
// /**
// * remove Flight from Technical Queue
// * @return Flight
// */
// public static IncomingFlight LogisticsQueue_Synced_Pop() {
// synchronized(LogisticsQueue){
// while(LogisticsQueue.size() == 0)
// try {
// LogisticsQueue.wait();
// if(endofday == true)
// return null;
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// return LogisticsQueue.pop();
// }
// }

// private static Queue<Flight> TechnicalQueue = new Queue<Flight>();
// /**
// * push a Flight to the Technical Queue
// * @param fl Flight
// */
// public static void TechnicalQueue_Synced_Push(Flight fl) {
// synchronized(TechnicalQueue){
// TechnicalQueue.push(fl);
// TechnicalQueue.notifyAll();
// }
// }
// /**
// * remove Flight from Technical Queue
// * @return Flight
// */
// public static Flight TechnicalQueue_Synced_Pop() {
// synchronized(TechnicalQueue){
// while(TechnicalQueue.size() == 0)
// try {
// TechnicalQueue.wait();
// if(endofday == true)
// return null;
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// return TechnicalQueue.pop();
// }
// }

// private static Queue<Flight> RunwayQueueLanding = new Queue<Flight>();
// private static Queue<Flight> RunwayQueueDeparture = new Queue<Flight>();
// /**
// * Push a Flight into Landing queue
// * @param fl Flight
// */
// public static void RunwayLandingQueue_Synced_Push(Flight fl) {
// synchronized(RunwayQueueLanding){
// RunwayQueueLanding.push(fl);
// RunwayQueueLanding.notifyAll();
// }
// }
// /**
// * remove a flight from Landing queue
// * @param fl Flight
// */
// public static Flight RunwayLandingQueue_Synced_Pop(){
// synchronized(RunwayQueueLanding){
// while(RunwayQueueLanding.size() == 0 && RunwayQueueDeparture.size() == 0 &&
// RunwayDirector.get_serviced_flights() < Flight.get_expected_flights()){
// try {
// RunwayQueueLanding.wait();
// if(RunwayDirector.get_serviced_flights() == Flight.get_expected_flights())
// return null;
// //see if any one of the queues has flights, keep waiting only if both empty
// if(RunwayQueueLanding.size() != 0)
// return RunwayQueueLanding.pop();
// else if(RunwayQueueDeparture.size() != 0)
// return RunwayDepartureQueue_Synced_Pop();
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// }
// if(RunwayQueueLanding.size() != 0)
// return RunwayQueueLanding.pop();
// //if(RunwayQueueDeparture.size() != 0)
// return RunwayDepartureQueue_Synced_Pop();
// //if(RunwayDirector.get_serviced_flights() == Flight.get_expected_flights())
// // return null;
// //return null;
// }
// }
// /**
// * Push a flight into Departure queue
// * @param fl Flight
// */
// public static void RunwayDepartureQueue_Synced_Push(Flight fl) {
// synchronized(RunwayQueueDeparture){
// RunwayQueueDeparture.push(fl);
// RunwayQueueDeparture.notifyAll();
// }
// synchronized(RunwayQueueLanding)
// {
// RunwayQueueLanding.notifyAll();
// }
// }
// /**
// * remove Flight from Departure flights queue
// * @return Flight
// */
// public static Flight RunwayDepartureQueue_Synced_Pop() {
// synchronized(RunwayQueueDeparture){
// while(RunwayQueueDeparture.size() == 0 &&
// RunwayDirector.get_serviced_flights() < Flight.get_expected_flights())
// try {
// RunwayQueueDeparture.wait();
// if(RunwayDirector.get_serviced_flights() == Flight.get_expected_flights())
// return null;
// } catch (InterruptedException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// //if(RunwayDirector.get_serviced_flights() == Flight.get_expected_flights())
// // return null;
// return RunwayQueueDeparture.pop();
// }
// }

// /**
// * Notify RunwayDirectors threads that no more expected flights in queue
// */
// public static void NotifyOtherRunwayDirectors() {
// synchronized(RunwayQueueDeparture)
// {
// RunwayQueueDeparture.notifyAll();
// }
// synchronized(RunwayQueueLanding)
// {
// RunwayQueueLanding.notifyAll();
// }
// }

// /**
// * @return endofday - given state day over/not over
// */
// public static boolean get_endofday()
// {
// return endofday;
// }

// /**
// * Reset a new day
// */
// public static void new_day()
// {
// endofday = false;
// }

// /**
// * Notify all waiting threads that day is over.
// */
// public static void notifyEndOfDay()
// {
// endofday = true;
// synchronized(ManagementQueue)
// {
// ManagementQueue.notifyAll();
// }
// synchronized(TechnicalQueue)
// {
// TechnicalQueue.notifyAll();
// }
// synchronized(LogisticsQueue)
// {
// LogisticsQueue.notifyAll();
// }
// synchronized(SecurityQueue)
// {
// SecurityQueue.notifyAll();
// }
// synchronized(FuelQueue)
// {
// FuelQueue.notifyAll();
// }
// }

// /**
// * Function that pushes the flight to relevant queue by the 'CurrentQueue'
// parameter within flight.
// * @param element - Flight to push to relevant queue.
// */
// public static void PushToQueueByQueueName(Flight element) {
// switch(element.get_Current_Queue())
// {
// case "TechnichalQueue":
// TechnicalQueue_Synced_Push(element);
// break;
// case "LogisticsQueue":
// LogisticsQueue_Synced_Push((IncomingFlight)element);
// break;
// case "RunwayQueueLanding":
// RunwayLandingQueue_Synced_Push(element);
// break;
// case "RunwayQueueDeparture":
// RunwayDepartureQueue_Synced_Push(element);
// break;
// case "FuelQueue":
// FuelQueue_Synced_Push(element);
// break;
// case "SecurityQueue":
// SecurityQueue_Synced_Push(element);
// break;
// case "MangementQueue":
// ManagementQueue_Synced_Push(element.getReport());
// break;
// default:
// break;
// }
// }

// }
