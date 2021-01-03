package testClasses;

import com.company.PremierLeagueManager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PremierLeagueManagerTest {

    @Test
    public void testSearchClub(){
        PremierLeagueManager premierLeagueManager_1 = new PremierLeagueManager("save.csv");
        assertEquals("Club Founded ", true,premierLeagueManager_1.deleteClub("MTT","Galle"));
        assertEquals("Club not Founded ", false,premierLeagueManager_1.deleteClub("grt","grg"));
    }

    @Test
    public void testDeleteClub(){
        PremierLeagueManager premierLeagueManager_1 = new PremierLeagueManager("save.csv");
        assertEquals("Club Founded ", true,premierLeagueManager_1.deleteClub("QPR","Queens"));
        assertEquals("Club not Founded ", false,premierLeagueManager_1.deleteClub("fff","sfsf"));
    }

}
