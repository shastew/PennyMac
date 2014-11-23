public class ConsoleView implements IPennyMacView {

    public void displayDataItem(IPennyMacModel pPennyMacModel, String pItemName) {
        System.out.println(pPennyMacModel.getDataItem(pItemName));
    }

}