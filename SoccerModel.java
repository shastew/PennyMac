/************************************************************
 Name:        SoccerModel
 Purpose:     Retrieves and encapsulates Soccer data.
 ************************************************************/

import java.util.*;

public class SoccerModel implements IPennyMacModel {

    private String minGoalSpread = "999999999";
    private String teamWithMinGoalSpread = "Not Found!";
    private List<String> dataItems;

    protected String getTeamWithMinGoalSpread() {
        return this.teamWithMinGoalSpread;
    }

    protected void parseData(String pLine) {
		String team = pLine.substring(7,23).trim();
		int currentMin = Integer.valueOf(this.minGoalSpread).intValue();
		String s1 = pLine.substring(43,47).trim();
		String s2 = pLine.substring(50,54).trim();
		int goalsFor = Integer.valueOf(s1).intValue();
		int goalsAgainst = Integer.valueOf(s2).intValue();
		if (goalsFor >= goalsAgainst) {
			if ((goalsFor - goalsAgainst) < currentMin) {
				this.minGoalSpread = "" + (goalsFor - goalsAgainst);
				this.teamWithMinGoalSpread = team;
		    }
	    } else {
			if ((goalsAgainst - goalsFor) < currentMin) {
				this.minGoalSpread = "" + (goalsAgainst - goalsFor);
				this.teamWithMinGoalSpread = team;
		    }
	    }
    }

    protected void loadModel(String pDataLocation) {
        DataFileReader aDataFileReader = new DataFileReader(pDataLocation);
        this.dataItems = aDataFileReader.getData();
        //System.out.println("Lines in data file = " + this.dataItems.size());
        for (int i=3; i<this.dataItems.size(); i++) {
			String line = (String)this.dataItems.get(i);
			if (line.startsWith("</pre>")) {
				break;
		    } else if (line.startsWith("   --")) {
				continue;
		    } else {
                parseData(line);
		    }
        }
    }

    public IPennyMacModel getModel(String pDataLocation) {
        this.loadModel(pDataLocation);
        return this;
    }

    public String getDataItem(String pItemName) {
        return this.getTeamWithMinGoalSpread();
    }

    public static void main(String args[]) {
		String dataFileName = args[0];
		System.out.println("Processing data located at " + dataFileName + "...");
		IPennyMacModel aSoccerModel = new SoccerModel();
        aSoccerModel = aSoccerModel.getModel(dataFileName);
        System.out.println("Team with minimum total goal spread is " +
            aSoccerModel.getDataItem("teamWithMinGoalSpread"));
    }
}
