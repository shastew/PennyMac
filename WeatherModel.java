/************************************************************
 Name:        WeatherModel
 Purpose:     Retrieves and encapsulates weather data.
 ************************************************************/

import java.util.*;

public class WeatherModel implements IPennyMacModel {

    private String minTempSpread = "99999999";
    private String dayWithMinTempSpread = "0";
    private List<String> dataItems;

    protected String getDayWithMinTempSpread() {
        return dayWithMinTempSpread;
    }

    protected void parseData(String pLine) {
		String dayNum = pLine.substring(0,4).trim();
		int currentMin = Integer.valueOf(this.minTempSpread).intValue();
		String s1 = pLine.substring(4,8).trim();
		String s2 = pLine.substring(9,14).trim();
		//System.out.println("MaxTemp=" + s1 + "; MinTemp=" + s2);
		int maxTemp = Integer.valueOf(s1).intValue();
		int minTemp = Integer.valueOf(s2).intValue();
		if ((maxTemp - minTemp) < currentMin) {
			this.minTempSpread = "" + (maxTemp - minTemp);
			this.dayWithMinTempSpread = dayNum;
	    }
    }

    protected void loadModel(String pDataLocation) {
        DataFileReader aDataFileReader = new DataFileReader(pDataLocation);
        this.dataItems = aDataFileReader.getData();
        for (int i=6; i<this.dataItems.size(); i++) {
			String line = this.dataItems.get(i);
			if (line.trim().startsWith("mo")) {
				break;
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
        return this.getDayWithMinTempSpread();
    }

    public static void main(String args[]) {
		String dataFileName = args[0];
		System.out.println("Processing data located at " + dataFileName + "...");
		IPennyMacModel aWeatherModel = new WeatherModel();
        aWeatherModel = aWeatherModel.getModel(dataFileName);
        System.out.println("Day with minimum temperature spread is " +
            aWeatherModel.getDataItem("dayWithMinTempSpread"));
    }
}


