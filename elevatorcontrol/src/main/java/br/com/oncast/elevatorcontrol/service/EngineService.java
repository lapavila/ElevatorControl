package br.com.oncast.elevatorcontrol.service;

import br.com.oncast.elevatorcontrol.domain.Elevator;

public class EngineService implements Runnable {

	private Elevator elevator;

	public EngineService(Elevator elevator) {
		this.elevator = elevator;
	}

	public void run() {
		boolean executanto = true;
		while(executanto) {
			if (elevator.isMoving()) {
				ElevatorService.getElevatorService().moveElevator(elevator);
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				executanto = false;
			}
		}
	}

}
