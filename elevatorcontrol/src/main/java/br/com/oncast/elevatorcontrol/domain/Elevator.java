package br.com.oncast.elevatorcontrol.domain;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Elevator {
	
	public static final Character UP = 'u';
	public static final Character DOWN = 'd';
	
	private Integer id;
	private Character direction;
	private Integer floor;
	private Set<Integer> requestedUpFloors;
	private Set<Integer> requestedDownFloors;
	
	public Elevator(int id) {
		setId(id);
		requestedUpFloors = new TreeSet<Integer>();
		requestedDownFloors = new TreeSet<Integer>();
		floor = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Character getDirection() {
		return direction;
	}
	
	public void setDirection(Character direction) {
		this.direction = direction;
	}
	
	public int getFloor() {
		return floor;
	}
	
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	
	public void addRequestedFloor(Character direction, Integer floor) {
		if (getDirection() == null) {
			setDirection(direction);
		}
		if (direction == Elevator.UP) {
			this.requestedUpFloors.add(floor);
		} else {
			this.requestedDownFloors.add(floor);
		}
	}
	
	public Integer getNextFloor() {
		Integer ret = getFloor();
		if ((this.isUp() && getFloor() < getMaxRequestedFlor())) {
			ret = getFloor() + 1;
		} else if ((this.isDown() && getFloor() > getMinRequestedFlor())) {
			ret = getFloor() -1;
		} else if ((this.isUp() && getFloor() > getMaxRequestedFlor())) {
			setDirection(Elevator.DOWN);
			ret = getFloor() - 1;
		} else if ((this.isDown() && getFloor() < getMinRequestedFlor())) {
			setDirection(Elevator.UP);
			ret = getFloor() + 1;
		}
		
		if (ret == getFloor()) {
			if (getDirection() == Elevator.DOWN) {
				setDirection(Elevator.UP);
			} else {
				setDirection(Elevator.DOWN);
			}
		}
		return ret;
	}
	
	public boolean openDoor(Integer floor) {
		if (this.isUp() && requestedUpFloors.contains(floor)) {
			requestedUpFloors.remove(floor);
			return true;
		} else if (this.isDown() && requestedDownFloors.contains(floor)) {
			requestedDownFloors.remove(floor);
			return true;
		}
		return false;
	}
	
	public boolean isUp() {
		return getDirection() == Elevator.UP;
	}
	
	public boolean isDown() {
		return getDirection() == Elevator.DOWN;
	}
	
	public boolean isMoving() {
		if (!this.requestedUpFloors.isEmpty() || !this.requestedDownFloors.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public Integer getMaxRequestedFlor() {
		Integer maxUp = (requestedUpFloors.size() > 0 ? Collections.max(requestedUpFloors) : null);
		Integer maxDown = (requestedDownFloors.size() > 0 ? Collections.max(requestedDownFloors) : null);
		
		if (maxUp == null && maxDown == null) {
			return Integer.MAX_VALUE;
		} else if (maxUp == null) {
			return maxDown;
		} else if (maxDown == null) {
			return maxUp;
		} else if (maxUp.compareTo(maxDown) > 0) {
			return maxUp;
		} else {
			return maxDown;
		}
	}
	
	public Integer getMinRequestedFlor() {
		Integer minUp = (requestedUpFloors.size() > 0 ? Collections.min(requestedUpFloors) : null);
		Integer minDown = (requestedDownFloors.size() > 0 ? Collections.min(requestedDownFloors) : null);
		
		if (minUp == null && minDown == null) {
			return Integer.MIN_VALUE;
		} else if (minUp == null) {
			return minDown;
		} else if (minDown == null) {
			return minUp;
		} else if (minUp.compareTo(minDown) < 0) {
			return minUp;
		} else {
			return minDown;
		}
	}
	
}