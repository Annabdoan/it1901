package beerPunishment.ui;

import beerPunishment.core.BeerMain;
import org.junit.jupiter.api.BeforeEach;

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
}
