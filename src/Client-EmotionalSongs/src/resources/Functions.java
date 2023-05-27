package resources;

/**
 * The Functions class contains utility functions used in the application.
 */
public class Functions {

    /**
     * Refactors the given array of strings by removing null values and returns the refactored array.
     *
     * @param dummyData The array of strings to be refactored
     * @return The refactored array of strings without null values
     */
    public static String[] refactorData(String[] dummyData) {
        int length = 0;

        for (int i = 0; i < dummyData.length; i++) {
            if (dummyData[i] != null) {
                length++;
            }
        }

        String[] emailListOk = new String[length];

        for (int i = 0; i < emailListOk.length; i++) {
            emailListOk[i] = dummyData[i];
        }

        return emailListOk;
    }

}
