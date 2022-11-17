package beerPunishment.ui;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * BeerControllerTest test most of the methods in BeerMainLocalAccess, although it misses some branches.
 * This testclass aims to test those missed branches to improve our testcoverage.
 */

public class BeerMainLocalAccessTest {
    private BeerMain bm;
    private BeerMainLocalAccess bmLA;

    @BeforeEach
    public void setup() {
        bm = new BeerMain();
        bmLA = new BeerMainLocalAccess();
    }

    @Test
    public void testThrowsIOException() {
        bmLA.changeLocalFilename(null);
        assertDoesNotThrow(() -> bmLA.getBeermain());
        assertDoesNotThrow(() -> bmLA.addRule(bm, "Test", 1));
        bm.addRule(new Rule("Test", 1));
        assertThrows(IllegalArgumentException.class, () -> bmLA.deleteRule(null, "Test"));
        assertDoesNotThrow(() -> bmLA.addMember(bm, "Test"));
        bm.addMember("Test");
        assertThrows(IllegalArgumentException.class, () -> bmLA.deleteMember(bm, "Test"));
        assertThrows(IllegalArgumentException.class, () -> bmLA.punishMember(bm, "Test", "TestRule", 1));
        assertThrows(IllegalArgumentException.class, () -> bmLA.deletePunishment(bm, "Test", "TestRule", 1));
    }
}
