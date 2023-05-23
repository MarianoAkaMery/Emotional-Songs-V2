package resources;

public class Functions {

    static public String[] refactorData(String[] DummyData) {

        int Length=0;

        for (int i=0;i<DummyData.length;i++){
            if (DummyData[i]!=null)
            Length++;
        }

        String EmailListOk[]= new String[Length];

        for (int i=0;i<EmailListOk.length;i++){
            EmailListOk[i]=DummyData[i];
        }
    
        return EmailListOk;
    }
    
}
