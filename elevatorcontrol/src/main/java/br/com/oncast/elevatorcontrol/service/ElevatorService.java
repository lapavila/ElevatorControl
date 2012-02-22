package br.com.oncast.elevatorcontrol.service;

import java.util.Map;
import java.util.TreeMap;

import br.com.oncast.elevatorcontrol.domain.Elevator;

public class ElevatorService {
	private static ElevatorService service = null;
	private Map<Integer, Elevator> elevatorPool = null;
	private Integer maxFloor;
	private Integer minFloor;
	private Integer quantityElevator;
	
	private ElevatorService() {
		this.maxFloor = 12;
		this.minFloor = -2;
		quantityElevator = 1;
		elevatorPool = new TreeMap<Integer, Elevator>();
		for (int i = 1; i <= quantityElevator; i++) {
			elevatorPool.put(i, new Elevator(i));
		}
	}
	
	public Map<Integer, Elevator> getElevatorPool() {
		return this.elevatorPool;
	}
	
	public static ElevatorService getElevatorService() {
		if (service == null) {
			service = new ElevatorService();
		}
		return service;
	}
	
	public void callElevator(Character direction, int floor) {
		floor = floor > maxFloor ? maxFloor : floor < minFloor ? minFloor : floor;
		getNearElevator(direction, floor).addRequestedFloor(direction, floor);
	}
	
	public void moveElevator(Elevator elevator) {
		Integer toFloor = elevator.getNextFloor();
		elevator.setFloor(toFloor);
		System.out.println("Elevator-" + elevator.getId() + " in " + toFloor + " floor" + (elevator.openDoor(toFloor) ? " - opening door" : ""));
	}
	
	public Elevator getNearElevator(Character direction, int floor) {
		return elevatorPool.get(1);
	}
}
