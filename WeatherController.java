/************************************************************
 Name:        WeatherController
 Purpose:     Controller for Weather requests.
 ************************************************************/
public class WeatherController {

    private String dataLocation;

    public WeatherController(String pDataLocation) {
        this.dataLocation = pDataLocation;
    }

    public void handleRequest(String pItemName) {
	    IPennyMacModel aModel = new WeatherModel().getModel(this.dataLocation);
	    IPennyMacView aView = new ConsoleView();
	    aView.displayDataItem(aModel, pItemName);
    }

    public static void main(String args[]) {
		String dataFileName = args[0];
		System.out.println("Processing data located at " + dataFileName + "...");
		WeatherController aWeatherController = new WeatherController(dataFileName);
        aWeatherController.handleRequest("dayWithMinTempSpread");
    }
}