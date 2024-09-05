import java.time.LocalTime;

public class Timer {
	int hour = 0;
    int minute = 0;

    public Timer(int hour,int minute) {
    	this.hour = hour;
    	this.minute = minute;
    }
    
    public Boolean checkTime() {
    	// Get the current system time
        LocalTime currentTime = LocalTime.now().withSecond(0).withNano(0);;

        // Create a LocalTime object for the specified time
        LocalTime specifiedTime = LocalTime.of(hour, minute);
        try {
            Thread.sleep(3000); // Sleep for 3 seconds (3000 milliseconds)
        } catch (InterruptedException e) {
            // Handle interrupted exception if needed
            e.printStackTrace();
        }
        // Check if the current time equals the specified time
        if (currentTime.equals(specifiedTime)) {
        	System.out.println("It is time yet");
        	try {
                Thread.sleep(60000); // Sleep for 3 seconds (3000 milliseconds)
            } catch (InterruptedException e) {
                // Handle interrupted exception if needed
                e.printStackTrace();
            }
            return true;
        } else {
        	System.out.println("No time yet");
            return false;
        }
    }
}
