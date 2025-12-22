
	
	import java.io.*;
	import java.time.LocalDate;
	import java.time.LocalTime;
	import java.util.*;
	
	/**
	 * 
	 */
	public class ScheduleDetail {
	    public ScheduleDetail() {
	    }
	    public LocalDate Date;
	    public LocalTime timeStart;
	    public LocalTime timeEnd;
	    public Vehicle vehicle;
	    public ScheduleState state;
	    public ScheduleDetail(LocalDate Date, LocalTime timeStart, LocalTime timeEnd, ScheduleState state	) {
	    	this.Date = Date;
	    	this.timeEnd = timeEnd;
	    	this.timeStart = timeStart;
	    	this.state = state;
	    
	    }
	        // TODO implement here
		public LocalDate getDate() {
			return Date;
		}
		public LocalTime getTimeStart() {
			return timeStart;
		}
		public void setTimeStart(LocalTime timeStart) {
			this.timeStart = timeStart;
		}
		public LocalTime getTimeEnd() {
			return timeEnd;
		}
		public void setTimeEnd(LocalTime timeEnd) {
			this.timeEnd = timeEnd;
		}
		public Vehicle getVehicle() {
			return vehicle;
		}
		public void setVehicle(Vehicle vehicle) {
			this.vehicle = vehicle;
		}
		public ScheduleState getState() {
			return state;
		}
		public void setState(ScheduleState state) {
			this.state = state;
		}	
		public ScheduleState isRunning() {
			return state = ScheduleState.RUNNING;
		}
	    }
