package elevatorTest.Data;

import lombok.Data;

@Data
public class Call {
	private Integer id;
	private Integer timestamp;
	private Integer start;
	private Integer end;
}
