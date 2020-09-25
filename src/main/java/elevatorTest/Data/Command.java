package elevatorTest.Data;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Command {
	private Integer elevator_id;
	private String command;
	private ArrayList<Integer> call_ids;
}
