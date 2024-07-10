import org.junit.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.Assert.assertEquals;

public class LocalizationServiceImplTest {

    private final LocalizationService localizationService = new LocalizationServiceImpl();

    @Test
    public void testLocale() {
        String result = localizationService.locale(Country.RUSSIA);
        assertEquals("Добро пожаловать", result);

        result = localizationService.locale(Country.USA);
        assertEquals("Welcome", result);
    }
}
