
public class UnmatchedException extends Exception {

	public UnmatchedException() { 
        this("The passwords do not match");
	}
	
	public UnmatchedException(String message)
	{
		super(message);
	}
}
