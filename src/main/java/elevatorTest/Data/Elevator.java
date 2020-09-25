package elevatorTest.Data;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Elevator {
	private Integer id;
	private Integer floor;
	private ArrayList<Call> passengers;
	private String status;
}
