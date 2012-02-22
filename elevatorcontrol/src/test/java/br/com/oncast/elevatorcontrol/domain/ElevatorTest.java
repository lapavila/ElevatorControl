package br.com.oncast.elevatorcontrol.domain;
import org.junit.* ;
import static org.junit.Assert.* ;

public class ElevatorTest {
	
	@Test
	public void testGetNextFloorCondition1() {
		Elevator elevator = new Elevator(1);
		elevator.setFloor(3);
		elevator.setDirection(Elevator.UP);
		elevator.addRequestedFloor(Elevator.UP, 5);
		
		assertEquals(4, elevator.getNextFloor().longValue());
		assertEquals(Elevator.UP, elevator.getDirection());
	}
	
	@Test
	public void testGetNextFloorCondition2() {
		Elevator elevator = new Elevator(1);
		elevator.setFloor(7);
		elevator.setDirection(Elevator.DOWN);
		elevator.addRequestedFloor(Elevator.UP, 5);
		
		assertEquals(6, elevator.getNextFloor().longValue());
		assertEquals(Elevator.DOWN, elevator.getDirection());
	}
	
	@Test
	public void testGetNextFloorCondition3() {
		Elevator elevator = new Elevator(1);
		elevator.setFloor(7);
		elevator.setDirection(Elevator.UP);
		elevator.addRequestedFloor(Elevator.UP, 5);
		
		assertEquals(6, elevator.getNextFloor().longValue());
		assertEquals(Elevator.DOWN, elevator.getDirection());
	}
	
	@Test
	public void testGetNextFloorCondition4() {
		Elevator elevator = new Elevator(1);
		elevator.setFloor(3);
		elevator.setDirection(Elevator.DOWN);
		elevator.addRequestedFloor(Elevator.UP, 5);
		
		assertEquals(4, elevator.getNextFloor().longValue());
		assertEquals(Elevator.UP, elevator.getDirection());
	}
	
	@Test
	public void testGetNextFloorCondition5() {
		Elevator elevator = new Elevator(1);
		elevator.setFloor(5);
		elevator.setDirection(Elevator.DOWN);
		elevator.addRequestedFloor(Elevator.UP, 5);
		
		assertEquals(5, elevator.getNextFloor().longValue());
		assertEquals(Elevator.UP, elevator.getDirection());
	}
	
	@Test
	public void testGetNextFloorCondition6() {
		Elevator elevator = new Elevator(1);
		elevator.setFloor(5);
		elevator.setDirection(Elevator.UP);
		elevator.addRequestedFloor(Elevator.UP, 5);
		
		assertEquals(5, elevator.getNextFloor().longValue());
		assertEquals(Elevator.DOWN, elevator.getDirection());	}
	
	@Test
	public void testOpenDoorCondition1() {
		Elevator elevator = new Elevator(1);
		elevator.addRequestedFloor(Elevator.UP, 5);
		elevator.setDirection(Elevator.UP);
		
		assertFalse(elevator.openDoor(3));
		assertTrue(elevator.openDoor(5));
	}
	
	@Test
	public void testOpenDoorCondition2() {
		Elevator elevator = new Elevator(1);
		elevator.setDirection(Elevator.UP);
		elevator.addRequestedFloor(Elevator.UP, 5);
		elevator.addRequestedFloor(Elevator.DOWN, 3);
		
		assertFalse(elevator.openDoor(3));
		assertTrue(elevator.openDoor(5));
	}
	
	@Test
	public void testOpenDoorCondition3() {
		Elevator elevator = new Elevator(1);
		elevator.setDirection(Elevator.DOWN);
		elevator.addRequestedFloor(Elevator.UP, 5);
		elevator.addRequestedFloor(Elevator.DOWN, 3);
		
		assertFalse(elevator.openDoor(5));
		assertTrue(elevator.openDoor(3));
		
	}
	
	@Test
	public void testIsMovingCondition1() {
		Elevator elevator = new Elevator(1);
		elevator.addRequestedFloor(Elevator.UP, 5);
		assertTrue(elevator.isMoving());
	}
	
	@Test
	public void testIsMovingCondition2() {
		Elevator elevator = new Elevator(1);
		elevator.addRequestedFloor(Elevator.DOWN, 5);
		assertTrue(elevator.isMoving());
	}
	
	@Test
	public void testIsMovingCondition3() {
		Elevator elevator = new Elevator(1);
		elevator.addRequestedFloor(Elevator.UP,3);
		elevator.addRequestedFloor(Elevator.DOWN, 5);
		assertTrue(elevator.isMoving());
	}
	
	@Test
	public void testIsMovingCondition4() {
		Elevator elevator = new Elevator(1);
		assertFalse(elevator.isMoving());
	}
	
	@Test
	public void testGetMinRequestFloorCondition1() {
		Elevator elevator = new Elevator(1);
		elevator.addRequestedFloor(Elevator.UP, 5);
		elevator.addRequestedFloor(Elevator.DOWN, 3);
		
		assertEquals(3, elevator.getMinRequestedFlor().longValue());
	}
	
	@Test
	public void testGetMinRequestFloorCondition2() {
		Elevator elevator = new Elevator(1);
		elevator.addRequestedFloor(Elevator.UP, 3);
		elevator.addRequestedFloor(Elevator.DOWN, 5);
		
		assertEquals(3, elevator.getMinRequestedFlor().longValue());
	}
	
	@Test
	public void testGetMinRequestFloorCondition3() {
		Elevator elevator = new Elevator(1);
		elevator.addRequestedFloor(Elevator.UP, 5);
		
		assertEquals(5, elevator.getMinRequestedFlor().longValue());
	}
	
	@Test
	public void testGetMinRequestFloorCondition4() {
		Elevator elevator = new Elevator(1);
		elevator.addRequestedFloor(Elevator.DOWN, 3);
		
		assertEquals(3, elevator.getMinRequestedFlor().longValue());
	}
	
	@Test
	public void testGetMinRequestFloorCondition5() {
		Elevator elevator = new Elevator(1);
		
		assertEquals(Integer.MIN_VALUE, elevator.getMinRequestedFlor().longValue());
	}
	
	@Test
	public void testGetMaxRequestFloorCondition1() {
		Elevator elevator = new Elevator(1);
		elevator.addRequestedFloor(Elevator.UP, 5);
		elevator.addRequestedFloor(Elevator.DOWN, 3);
		
		assertEquals(5, elevator.getMaxRequestedFlor().longValue());
	}
	
	@Test
	public void testGetMaxRequestFloorCondition2() {
		Elevator elevator = new Elevator(1);
		elevator.addRequestedFloor(Elevator.UP, 3);
		elevator.addRequestedFloor(Elevator.DOWN, 5);
		
		assertEquals(5, elevator.getMaxRequestedFlor().longValue());
	}
	
	@Test
	public void testGetMaxRequestFloorCondition3() {
		Elevator elevator = new Elevator(1);
		elevator.addRequestedFloor(Elevator.UP, 5);
		
		assertEquals(5, elevator.getMaxRequestedFlor().longValue());
	}
	
	@Test
	public void testGetMaxRequestFloorCondition4() {
		Elevator elevator = new Elevator(1);
		elevator.addRequestedFloor(Elevator.UP, 5);
		elevator.addRequestedFloor(Elevator.DOWN, 3);
		
		assertEquals(5, elevator.getMaxRequestedFlor().longValue());

		elevator = new Elevator(1);
		elevator.addRequestedFloor(Elevator.UP, 3);
		elevator.addRequestedFloor(Elevator.DOWN, 5);
		
		assertEquals(5, elevator.getMaxRequestedFlor().longValue());
		
		elevator = new Elevator(1);
		elevator.addRequestedFloor(Elevator.UP, 5);
		
		assertEquals(5, elevator.getMaxRequestedFlor().longValue());

		elevator = new Elevator(1);
		elevator.addRequestedFloor(Elevator.DOWN, 3);
		
		assertEquals(3, elevator.getMaxRequestedFlor().longValue());
	}
	
	@Test
	public void testGetMaxRequestFloorCondition5() {
		Elevator elevator = new Elevator(1);
		
		assertEquals(Integer.MAX_VALUE, elevator.getMaxRequestedFlor().longValue());
	}
	
}
