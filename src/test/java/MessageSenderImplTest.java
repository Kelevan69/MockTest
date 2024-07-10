import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MessageSenderImplTest {

    private GeoService geoService;
    private LocalizationService localizationService;
    private MessageSender messageSender;

    @Before
    public void setup() {
        geoService = mock(GeoService.class);
        localizationService = mock(LocalizationService.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    public void testSendRussianMessageForRussianIp() {
        String ip = "172.0.32.11"; // IP из российского сегмента
        Location location = new Location("Moscow", Country.RUSSIA, null, 0);

        when(geoService.byIp(ip)).thenReturn(location);
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String result = messageSender.send(headers);
        assertEquals("Добро пожаловать", result);

        verify(geoService, times(1)).byIp(ip);
        verify(localizationService, times(1)).locale(Country.RUSSIA);
    }

    @Test
    public void testSendEnglishMessageForAmericanIp() {
        String ip = "96.44.183.149"; // IP из американского сегмента
        Location location = new Location("New York", Country.USA, null, 0);

        when(geoService.byIp(ip)).thenReturn(location);
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String result = messageSender.send(headers);
        assertEquals("Welcome", result);

        verify(geoService, times(1)).byIp(ip);
        verify(localizationService, times(1)).locale(Country.USA);
    }
}
