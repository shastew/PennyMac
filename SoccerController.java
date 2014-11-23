/************************************************************
 Name:        SoccerController
 Purpose:     Controller for Soccer requests.
 ************************************************************/
public class SoccerController {

    private String dataLocation;

    public SoccerController(String pDataLocation) {
        this.dataLocation = pDataLocation;
    }

    public void handleRequest(String pItemName) {
	    IPennyMacModel aModel = new SoccerModel().getModel(this.dataLocation);
	    IPennyMacView aView = new ConsoleView();
	    aView.displayDataItem(aModel, pItemName);
    }

    public static void main(String args[]) {
		String dataFileName = args[0];
		System.out.println("Processing data located at " + dataFileName + "...");
		SoccerController aSoccerController = new SoccerController(dataFileName);
        aSoccerController.handleRequest("teamWithMinGoalSpread");
    }
}