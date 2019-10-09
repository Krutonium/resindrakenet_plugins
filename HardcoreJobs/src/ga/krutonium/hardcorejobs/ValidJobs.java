package ga.krutonium.hardcorejobs;

import com.sun.org.apache.xpath.internal.objects.XString;

import java.util.ArrayList;
import java.util.List;

public class ValidJobs {
    public static List<String> Jobs()
    {
        ArrayList<String> jobs = new ArrayList<String>();
        jobs.add("Farmer");
        jobs.add("Miner");
        jobs.add("Lumberjack");
        jobs.add("Slayer");
        jobs.add("Mage");
        jobs.add("Crafter");
        jobs.add("Builder");
        jobs.add("Trader");
        return jobs;
    }
}