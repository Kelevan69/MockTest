import org.junit.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.Assert.assertEquals;

public class GeoServiceImplTest {

    private final GeoService geoService = new GeoServiceImpl();

    @Test
    public void testByIp() {
        Location location = geoService.byIp("172.0.32.11");
        assertEquals(Country.RUSSIA, location.getCountry());

        location = geoService.byIp("96.44.183.149");
        assertEquals(Country.USA, location.getCountry());
    }
}
