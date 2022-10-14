package co.siten.myvtg.exception;

public class CallApiDausoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object subMb;
	private String serial;
	private String  message;
	
	

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public CallApiDausoException(Object subMb, Exception e) {
		this.subMb = subMb;
		this.message= e.getMessage();
	}

	public Object getSubMb() {
		return subMb;
	}

	public void setSubMb(Object subMb) {
		this.subMb = subMb;
	}

}
