/**
 * DTO that contains an interpret 
 * @author erflo
 */

package persistence;

import javax.xml.bind.annotation.XmlElement;

public class InterpretDTO {
	private int interpretID;
	private String name;

	/**
	 * 
	 */
	public InterpretDTO() {
	}

	/**
	 * @param interpretID
	 * @param name
	 */
	public InterpretDTO(int interpretID, String name) {
		this.interpretID = interpretID;
		this.name = name;
	}

	/**
	 * @return interpretID
	 */
	public int getInterpretID() {
		return interpretID;
	}

	/**
	 * @param interpretID
	 */
	@XmlElement
	public void setInterpretID(int interpretID) {
		this.interpretID = interpretID;
	}

	/**
	 * @param name
	 */
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}
}
