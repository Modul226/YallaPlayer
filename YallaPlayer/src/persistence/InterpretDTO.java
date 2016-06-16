/**
 * @author erflo
 */

package persistence;

import javax.xml.bind.annotation.XmlElement;

public class InterpretDTO {
	private int interpretID;
	private String name;

	public InterpretDTO() {
	}

	public InterpretDTO(int interpretID, String name) {
		this.interpretID = interpretID;
		this.name = name;
	}

	public int getInterpretID() {
		return interpretID;
	}

	@XmlElement
	public void setInterpretID(int interpretID) {
		this.interpretID = interpretID;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
